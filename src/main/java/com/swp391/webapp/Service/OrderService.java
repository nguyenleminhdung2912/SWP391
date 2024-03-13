package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.*;
import com.swp391.webapp.Entity.Enum.OrderStatus;
import com.swp391.webapp.Repository.*;
import com.swp391.webapp.dto.OrderDTO;
import com.swp391.webapp.dto.OrderDetailDTO;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AccountUtils accountUtils;
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private Order_DetailService orderDetailService;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private Order_Detail_Repository order_Detail_Repository;

    // ServiceDTO methods for Order entity

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity findOrderById(int OrderId) {
        return orderRepository.findById(OrderId).orElse(null);
    }

    public OrderEntity saveOrder(OrderEntity Order) {
        return orderRepository.save(Order);
    }

    public void deleteOrder(int OrderId) {
        orderRepository.deleteById(OrderId);
    }

    public OrderEntity makeOrder(OrderDTO order) {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setAccount(accountUtils.getCurrentAccount());
        orderEntity.setPackageEntity(packageRepository.findPackageByPackageID(order.getPackageId()));
        orderEntity.setSchedule(scheduleRepository.findById(order.getScheduleId()).get());
        orderEntity.setStatus(order.getStatus());
        orderEntity.setTotalPrice(BigDecimal.valueOf(order.getTotalPrice()));
        orderEntity.setPhone(order.getPhoneNumber());
        orderEntity.setCustomerName(order.getUsername());
        orderEntity.setVenue(order.getVenue());
        orderEntity.setCustomerEmail(order.getEmail());
        Date date = new Date();
        orderEntity.setCreateAt(date);
        orderEntity.setStatus(OrderStatus.ORDERED);


//        orderEntity.setQuantity();
        orderRepository.save(orderEntity);

        List<OrderDetailDTO> list = order.getOrderDetailDTOList();
        for (int i = 0; i < list.size(); i++) {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setService(serviceRepository.findById(list.get(i).getId()).get());
            orderDetailEntity.setOrder(orderRepository.findById(orderEntity.getOrderID()).get());
        }
        return orderEntity;
    }

    public OrderEntity createOrder(OrderEntity Order, List<OrderDetailDTO> list) {
        OrderEntity orderEntity = orderRepository.save(Order);
        for (int i = 0; i < list.size(); i++) {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setService(serviceRepository.findById(list.get(i).getId()).get());
            orderDetailEntity.setOrder(orderRepository.findById(orderEntity.getOrderID()).get());
            order_Detail_Repository.save(orderDetailEntity);
        }
        return orderEntity;
    }

    public WalletEntity refuseOrder(int orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        orderEntity.setStatus(OrderStatus.REFUSESD);
        orderRepository.save(orderEntity);

        System.out.println("đã refused");

        //Tru tien trong tai khoan admin
        WalletEntity adminWallet = walletService.getWalletById(1).get();
        BigDecimal total = new BigDecimal(adminWallet.getTotalMoney().longValue() - orderEntity.getTotalPrice().longValue());
        adminWallet.setTotalMoney(total);
        walletService.saveWallet(adminWallet);

        //Them tien vao tai khoan guest
        AccountEntity guestAccount = accountService.getAccountById(orderEntity.getAccount().getAccountID()).get();
        WalletEntity guestWallet = walletService.getWalletById(guestAccount.getAccountID()).get();
        total = new BigDecimal(guestWallet.getTotalMoney().longValue() + orderEntity.getTotalPrice().longValue());
        return walletService.saveWallet(guestWallet);
    }

    public OrderEntity acceptOrder(int orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        orderEntity.setStatus(OrderStatus.ACCEPTED);
        orderRepository.save(orderEntity);
        System.out.println("đã accept");

        return orderEntity;
    }

    public WalletEntity doneOrder(int orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        orderEntity.setStatus(OrderStatus.DONE);
        orderRepository.save(orderEntity);

        //Tru tien trong tai khoan admin
        WalletEntity adminWallet = walletService.getWalletById(1).get();
        BigDecimal total = new BigDecimal(adminWallet.getTotalMoney().longValue() - orderEntity.getTotalPrice().longValue());
        adminWallet.setTotalMoney(total);
        walletService.saveWallet(adminWallet);

        //Them tien vao tai khoan host
        AccountEntity hostAccount = accountService.getAccountById(orderEntity.getPackageEntity().getAccount().getAccountID()).get();
        WalletEntity hostWallet = walletService.getWalletById(hostAccount.getAccountID()).get();
        total = new BigDecimal(hostWallet.getTotalMoney().longValue() + orderEntity.getTotalPrice().longValue());
        return walletService.saveWallet(hostWallet);
    }

    public WalletEntity cancelOrder(int orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        orderEntity.setStatus(OrderStatus.CANCELLED);

        //Tru tien trong tai khoan admin
        WalletEntity adminWallet = walletService.getWalletById(1).get();
        BigDecimal total = new BigDecimal(adminWallet.getTotalMoney().longValue() - orderEntity.getTotalPrice().longValue());
        adminWallet.setTotalMoney(total);
        walletService.saveWallet(adminWallet);

        //Them tien vao tai khoan guest
        AccountEntity guestAccount = accountService.getAccountById(orderEntity.getAccount().getAccountID()).get();
        WalletEntity guestWallet = walletService.getWalletById(guestAccount.getAccountID()).get();
        total = new BigDecimal(guestWallet.getTotalMoney().longValue() + orderEntity.getTotalPrice().longValue());
        return walletService.saveWallet(guestWallet);
    }

    public List<OrderEntity> getPendingOrder() {
        return orderRepository.findOrdersByStatus(OrderStatus.ORDERED);
    }

    public List<OrderEntity> getDoneOrder() {
        return orderRepository.findOrdersByStatus(OrderStatus.DONE);
    }
    public List<OrderEntity> getRefusedOrder() {
        return orderRepository.findOrdersByStatus(OrderStatus.REFUSESD);
    }
    public List<OrderEntity> getAcceptedOrder() {
        return orderRepository.findOrdersByStatus(OrderStatus.ACCEPTED);
    }
    public List<OrderEntity> getCancelOrder() {
        return orderRepository.findOrdersByStatus(OrderStatus.CANCELLED);
    }

    public List<OrderEntity> getAllOrdersByHost(int hostId) {
        return orderRepository.findOrdersByHost(hostId);
    }

    public List<OrderEntity> getAllOrdersByGuest() {
        return orderRepository.findOrdersByAccount(accountUtils.getCurrentAccount());
    }

    public List<OrderEntity> getAllOrdersByGuestId(int accountId) {
        AccountEntity account = accountService.getAccountById(accountId).get();
        return orderRepository.findOrdersByAccount(account);
    }


    // Additional service methods if needed
}

package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.*;
import com.swp391.webapp.Entity.Enum.OrderStatus;
import com.swp391.webapp.Entity.Enum.TransactionStatus;
import com.swp391.webapp.ExceptionHandler.AlreadyExistedException;
import com.swp391.webapp.Repository.*;
import com.swp391.webapp.dto.OrderDTO;
import com.swp391.webapp.dto.OrderDetailDTO;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private ScheduleWorkingRepository scheduleWorkingRepository;
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
    @Autowired
    private EmailService emailService;
    @Autowired
    private TransactionService transactionService;

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
        orderEntity.setSchedule(scheduleWorkingRepository.findById(order.getScheduleId()).get());
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

        if (orderEntity.getStatus().equals(OrderStatus.REFUSESD)) {
            System.out.println("đã refused");
        }
        //Tru tien trong tai khoan admin
        WalletEntity adminWallet = walletService.getWalletById(1).get();
        BigDecimal total = new BigDecimal(adminWallet.getTotalMoney().longValue() - orderEntity.getDepositedMoney().longValue());
        adminWallet.setTotalMoney(total);
        transactionService.saveTransaction(new TransactionEntity(orderEntity, adminWallet, new Date(), orderEntity.getDepositedMoney(), TransactionStatus.SENDING));
        walletService.saveWallet(adminWallet);

        //Them tien vao tai khoan guest
        AccountEntity guestAccount = accountService.getAccountById(orderEntity.getAccount().getAccountID()).get();
        WalletEntity guestWallet = walletService.getWalletByAccount(guestAccount);
        total = new BigDecimal(guestWallet.getTotalMoney().longValue() + orderEntity.getDepositedMoney().longValue());
        guestWallet.setTotalMoney(total);
        transactionService.saveTransaction(new TransactionEntity(orderEntity, guestWallet, new Date(), orderEntity.getDepositedMoney(), TransactionStatus.RECEIVE));

        //Gui mail cho khach hang thong bao rang order da bi refuse
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setRecipient(guestAccount.getEmail());
        emailDetail.setName(guestAccount.getName());
        emailDetail.setSubject("We are sorry!");
        emailDetail.setMsgBody("Your order has been refused 😢.");
        emailService.sendGuestHostHasRefusedOrder(emailDetail);

        return walletService.saveWallet(guestWallet);
    }

    public OrderEntity acceptOrder(int orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        orderEntity.setStatus(OrderStatus.ACCEPTED);
        orderRepository.save(orderEntity);

        //Gui mail cho khach hang thong bao rang order da bi refuse
        AccountEntity guestAccount = accountService.getAccountById(orderEntity.getAccount().getAccountID()).get();
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setRecipient(guestAccount.getEmail());
        emailDetail.setName(guestAccount.getName());
        emailDetail.setSubject("Congratulation!");
        emailDetail.setMsgBody("Your order has been accepted 😊.");
        emailService.sendGuestHostHasAcceptOrder(emailDetail);

        return orderEntity;
    }

    public WalletEntity doneOrder(int orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        orderEntity.setStatus(OrderStatus.DONE);
        orderRepository.save(orderEntity);

        //Tinh tien
        BigDecimal adminKeep = new BigDecimal(orderEntity.getDepositedMoney().longValue() * 20 / 100); //20% tong gia tri order
        BigDecimal hostKeep = new BigDecimal(orderEntity.getDepositedMoney().longValue() - adminKeep.longValue()); //80% tong gia tri order

        //Tru tien trong tai khoan admin, giu lai 20%
        WalletEntity adminWallet = walletService.getWalletById(1).get();
        BigDecimal totalInWallet = new BigDecimal(adminWallet.getTotalMoney().longValue() - hostKeep.longValue());
        adminWallet.setTotalMoney(totalInWallet);
        transactionService.saveTransaction(new TransactionEntity(orderEntity, adminWallet, new Date(), orderEntity.getDepositedMoney(), TransactionStatus.SENDING));
        walletService.saveWallet(adminWallet);

        //Them tien vao tai khoan host
        AccountEntity hostAccount = accountService.getAccountById(orderEntity.getPackageEntity().getAccount().getAccountID()).get();
        WalletEntity hostWallet = walletService.getWalletById(hostAccount.getAccountID()).get();
        totalInWallet = new BigDecimal(hostWallet.getTotalMoney().longValue() + hostKeep.longValue());
        hostWallet.setTotalMoney(totalInWallet);
        transactionService.saveTransaction(new TransactionEntity(orderEntity, hostWallet, new Date(), orderEntity.getDepositedMoney(), TransactionStatus.RECEIVE));
        return walletService.saveWallet(hostWallet);
    }

    public WalletEntity cancelOrder(int orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        if (orderEntity.getStatus().equals(OrderStatus.ORDERED)) {

            orderEntity.setStatus(OrderStatus.CANCELLED);
            orderRepository.save(orderEntity);

        } else if (orderEntity.getStatus().equals(OrderStatus.PAID) || orderEntity.getStatus().equals(OrderStatus.ACCEPTED)) {

            orderEntity.setStatus(OrderStatus.CANCELLED);
            orderRepository.save(orderEntity);

//        //Kiem tra con bao nhieu ngay la den bua tiec
            Date d1 = new Date();
            Date d2 = orderEntity.getDate();
            float diffDay = 0;
            float diffMonth = 0;
            float diffYear = 0;

            diffMonth = d2.getMonth() - d1.getMonth();
            diffYear = d2.getYear() - d1.getYear();

            if (diffYear >= 0) {
                if (diffMonth > 0) {
                    diffDay = d1.getDate() - d2.getDate();
                } else if (diffMonth == 0) {
                    diffDay = d2.getDate() - d1.getDate();
                }
            }
            //Tren 3 ngay thi hoan tien 100%
            if (diffDay > 3) {


                WalletEntity adminWallet = walletService.getWalletById(2).get();
                //Tru tien trong tai khoan admin

                BigDecimal total = new BigDecimal(adminWallet.getTotalMoney().longValue() - orderEntity.getDepositedMoney().longValue());
                adminWallet.setTotalMoney(total);
                //Luu transaction cho admin
                transactionService.saveTransaction(new TransactionEntity(orderEntity, adminWallet, new Date(), orderEntity.getDepositedMoney(), TransactionStatus.SENDING));
                walletService.saveWallet(adminWallet);

                //Them tien vao tai khoan guest
                AccountEntity guestAccount = accountService.getAccountById(orderEntity.getAccount().getAccountID()).get();
                WalletEntity guestWallet = walletService.getWalletByAccount(guestAccount);
                total = new BigDecimal(guestWallet.getTotalMoney().longValue() + orderEntity.getDepositedMoney().longValue());
                guestWallet.setTotalMoney(total);
                //Luu transaction cho guest
                transactionService.saveTransaction(new TransactionEntity(orderEntity, guestWallet, new Date(), orderEntity.getDepositedMoney(), TransactionStatus.RECEIVE));

                return walletService.saveWallet(guestWallet);
            }
            //Con 2-3 ngay thi hoan 50%
            else if (diffDay == 2 || diffDay == 3) {
                //Tru tien trong tai khoan admin
                WalletEntity adminWallet = walletService.getWalletById(1).get();
                BigDecimal total = new BigDecimal(adminWallet.getTotalMoney().longValue() - (orderEntity.getDepositedMoney().longValue() * 50 / 100));
                adminWallet.setTotalMoney(total);
                transactionService.saveTransaction(new TransactionEntity(orderEntity, adminWallet, new Date(), orderEntity.getDepositedMoney(), TransactionStatus.SENDING));
                walletService.saveWallet(adminWallet);

                //Them tien vao tai khoan guest
                AccountEntity guestAccount = accountService.getAccountById(orderEntity.getAccount().getAccountID()).get();
                WalletEntity guestWallet = walletService.getWalletById(guestAccount.getAccountID()).get();
                total = new BigDecimal(guestWallet.getTotalMoney().longValue() + (orderEntity.getDepositedMoney().longValue() * 50 / 100));
                guestWallet.setTotalMoney(total);
                transactionService.saveTransaction(new TransactionEntity(orderEntity, guestWallet, new Date(), orderEntity.getDepositedMoney(), TransactionStatus.RECEIVE));

                return walletService.saveWallet(guestWallet);
            }
            //Con duoi 2 ngay khong cho huy tiec
            else if (diffDay < 2) {
                throw new RuntimeException(new AlreadyExistedException("There are only 1 day left until the party, you cannot cancel it!"));
            }
            orderRepository.save(orderEntity);
        }
        return null;
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

    public List<OrderEntity> getAllOrderByHostAndCreateBetweenAndStatusDone(int hostId, Date startDate, Date endDate, OrderStatus orderStatus) {
        List<OrderEntity> orderEntitiesList = orderRepository.findOrdersByHost(hostId);
        List<OrderEntity> returnOrderList = new ArrayList<>();
        for (int i = 0; i < orderEntitiesList.size(); i++) {
            if (orderEntitiesList.get(i).getCreateAt().after(startDate) && orderEntitiesList.get(i).getCreateAt().after(endDate) && orderEntitiesList.get(i).getStatus().equals(orderStatus.DONE)) {
                returnOrderList.add(orderEntitiesList.get(i));
            }
        }
        return returnOrderList;
    }


    // Additional service methods if needed
}

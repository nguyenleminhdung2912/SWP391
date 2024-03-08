package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.OrderEntity;
import com.swp391.webapp.Entity.OrderStatus;
import com.swp391.webapp.Entity.Order_Detail_Entity;
import com.swp391.webapp.Repository.OrderRepository;
import com.swp391.webapp.Repository.PackageRepository;
import com.swp391.webapp.Repository.ScheduleRepository;
import com.swp391.webapp.Repository.ServiceRepository;
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
            Order_Detail_Entity orderDetailEntity = new Order_Detail_Entity();
            orderDetailEntity.setService(serviceRepository.findById(list.get(i).getId()).get());
            orderDetailEntity.setOrder(orderRepository.findById(orderEntity.getOrderID()).get());
        }
        return orderEntity;
    }

    public OrderEntity createOrder(OrderEntity Order, List<OrderDetailDTO> list) {
        OrderEntity orderEntity = orderRepository.save(Order);
        for (int i = 0; i < list.size(); i++) {
            Order_Detail_Entity orderDetailEntity = new Order_Detail_Entity();
            orderDetailEntity.setService(serviceRepository.findById(list.get(i).getId()).get());
            orderDetailEntity.setOrder(orderRepository.findById(orderEntity.getOrderID()).get());
        }
        return orderEntity;
    }

    // Additional service methods if needed
}

package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Entity.Enum.OrderStatus;
import com.swp391.webapp.Entity.OrderEntity;
import com.swp391.webapp.Repository.OrderRepository;
import com.swp391.webapp.Repository.Order_Detail_Repository;
import com.swp391.webapp.dto.AdminDashboardDTO;
import com.swp391.webapp.dto.HostDashboardDTO;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HostDashboardService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Order_Detail_Repository orderDetailRepository;
    @Autowired
    private AccountUtils accountUtils;
    @Autowired
    private AccountService accountService;
    @Autowired
    private OrderService orderService;


    public List<OrderEntity> getHostTotalPlacedOrder() {
        AccountEntity hostAccount = accountUtils.getCurrentAccount();
        List<OrderEntity> tempList = orderRepository.findOrdersByHost(hostAccount.getAccountID());
        List<OrderEntity> orderList = new ArrayList<>();
        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i).getStatus().equals(OrderStatus.DONE)) {
                orderList.add(tempList.get(i));
            }
        }
        return orderList;
    }

    public List<OrderEntity> getHostTotalPlacedOrder(int id) {
        List<OrderEntity> tempList = orderRepository.findOrdersByHost(id);
        List<OrderEntity> orderList = new ArrayList<>();
        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i).getStatus().equals(OrderStatus.DONE)) {
                orderList.add(tempList.get(i));
            }
        }
        return orderList;
    }

    public Set<Integer> getHostTotalOrderedCustomer() {
        AccountEntity hostAccount = accountUtils.getCurrentAccount();
        List<OrderEntity> tempList = orderRepository.findOrdersByHost(hostAccount.getAccountID());
        Set<Integer> orderEntities = findDuplicates(tempList);
        return orderEntities;
    }

    public Set<Integer> getHostTotalOrderedCustomer(int hostId) {
        List<OrderEntity> tempList = orderRepository.findOrdersByHost(hostId);
        Set<Integer> orderEntities = findDuplicates(tempList);
        return orderEntities;
    }

    public Set<Integer> findDuplicates(List<OrderEntity> listContainingDuplicates) {
        final Set<Integer> setToReturn = new HashSet<>();
        final Set<Integer> set1 = new HashSet<>();

        for (OrderEntity order : listContainingDuplicates) {
            if (!set1.add(order.getAccount().getAccountID())) {
                setToReturn.add(order.getAccount().getAccountID());
            }
        }
        return setToReturn;
    }


    //Convert day to compare
    public HostDashboardDTO getHostDashboardDetail() {

        //Total Guest has ordered this Host
        Set<Integer> totalGuestList = getHostTotalOrderedCustomer();
        int totalGuest = totalGuestList.size();

        //Total order placed of this Host
        List<OrderEntity> orderList = getHostTotalPlacedOrder();
        int totalOrder = orderList.size();

        //Total revenue of this Host
        BigDecimal totalRevenue = new BigDecimal(0);
        for (int i = 0; i < orderList.size(); i++) {
            totalRevenue = totalRevenue.add(orderList.get(i).getTotalPrice());
        }

        //Chart making
        //Generate startList
        List<Date> startDateList = returnStartDateList();
        List<Date> endDateList = returnEndDateList();

        //Add order to list according to months
        List<Integer> allMonthlyOrderList = new ArrayList<>();
        List<BigDecimal> allMonthlyRevenueList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {

            //Lay so luong order trong 1 thang
            List<OrderEntity> list = orderRepository.findOrdersByHostAndCreateAtBetweenAndStatus(accountUtils.getCurrentAccount().getAccountID(), startDateList.get(i), endDateList.get(i), OrderStatus.DONE);
            allMonthlyOrderList.add(list.size());

            //Lay revenue trong 1 thang
            BigDecimal monthlyRevenue = new BigDecimal(0);
            for (int j = 0; j < list.size(); j++) {
                monthlyRevenue = monthlyRevenue.add(list.get(j).getTotalPrice());
            }
            allMonthlyRevenueList.add(monthlyRevenue);
        }

        //Return to API-endpoint
        return new HostDashboardDTO(totalGuest, totalOrder, totalRevenue, allMonthlyRevenueList, allMonthlyOrderList);
    }


    public HostDashboardDTO getHostDashboardDetail(int hostId) {
        //Total Guest has ordered this Host
        Set<Integer> totalGuestList = getHostTotalOrderedCustomer();
        int totalGuest = totalGuestList.size();

        //Total order placed of this Host
        List<OrderEntity> orderList = getHostTotalPlacedOrder();
        int totalOrder = orderList.size();

        //Total revenue of this Host
        BigDecimal totalRevenue = new BigDecimal(0);
        for (int i = 0; i < orderList.size(); i++) {
            totalRevenue = totalRevenue.add(orderList.get(i).getTotalPrice());
        }

        //Chart making
        //Generate startList
        List<Date> startDateList = returnStartDateList();
        List<Date> endDateList = returnEndDateList();

        //Add order to list according to months
        List<Integer> allMonthlyOrderList = new ArrayList<>();
        List<BigDecimal> allMonthlyRevenueList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {

            //Lay so luong order trong 1 thang
            List<OrderEntity> list = orderRepository.findOrdersByHostAndCreateAtBetweenAndStatus(hostId, startDateList.get(i), endDateList.get(i), OrderStatus.DONE);
            allMonthlyOrderList.add(list.size());

            //Lay revenue trong 1 thang
            BigDecimal monthlyRevenue = new BigDecimal(0);
            for (int j = 0; j < list.size(); j++) {
                monthlyRevenue = monthlyRevenue.add(list.get(j).getTotalPrice());
            }
            allMonthlyRevenueList.add(monthlyRevenue);
        }

        //Return to API-endpoint
        return new HostDashboardDTO(totalGuest, totalOrder, totalRevenue, allMonthlyRevenueList, allMonthlyOrderList);
    }

    public List<Date> returnStartDateList(){
        List<Date> StartList = new ArrayList<>();
        List<String> startString = Arrays.asList("2024-01-01", "2024-02-01",
                "2024-03-01", "2024-04-01",
                "2024-05-01", "2024-06-01",
                "2024-07-01", "2024-08-01",
                "2024-09-01", "2024-10-01",
                "2024-11-01", "2024-12-01");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 12; i++) {
            Date startDate = format.parse(startString.get(i), new ParsePosition(0));
            StartList.add(startDate);
        }
        return  StartList;
    }

    public List<Date> returnEndDateList(){
        List<Date> endList = new ArrayList<>();
        List<String> endString = Arrays.asList( "2024-01-31", "2024-02-29",
                "2024-03-31", "2024-04-30",
                "2024-05-31", "2024-06-30",
                "2024-07-31", "2024-08-31",
                "2024-09-30", "2024-10-31",
                "2024-11-30", "2024-12-31" );
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 12; i++) {
            Date startDate = format.parse(endString.get(i), new ParsePosition(0));
            endList.add(startDate);
        }
        return  endList;
    }
}

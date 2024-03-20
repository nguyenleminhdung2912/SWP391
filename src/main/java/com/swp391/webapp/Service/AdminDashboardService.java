package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.Enum.OrderStatus;
import com.swp391.webapp.Entity.OrderEntity;
import com.swp391.webapp.Repository.AccountRepository;
import com.swp391.webapp.Repository.OrderRepository;
import com.swp391.webapp.Repository.Order_Detail_Repository;
import com.swp391.webapp.dto.AdminDashboardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AdminDashboardService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Order_Detail_Repository orderDetailRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    public AdminDashboardDTO getDashboardDetail() {
        //Get system's total guest
        int systemTotalGuest = accountRepository.findAccountsByRoleAndIsDeleted("Guest", 0).size();

        //Get system's total host
        int systemTotalHost = accountRepository.findAccountsByRoleAndIsDeleted("Host", 0).size();

        //Get system's total order
        List<OrderEntity> orderList = orderRepository.findOrdersByStatus(OrderStatus.DONE);
        int systemPlacedOrder = orderList.size();

        //Get system's total revenue
        BigDecimal totalRevenue = new BigDecimal(0);
        for (int i = 0; i < orderList.size(); i++) {
            totalRevenue = totalRevenue.add(orderList.get(i).getTotalPrice());
        }

        //Generate startList
        List<Date> startDateList = returnStartDateList();
        List<Date> endDateList = returnEndDateList();

        //Add order to list according to months
        List<Integer> allMonthlyOrderList = new ArrayList<>();
        List<BigDecimal> allMonthlyRevenueList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            //Lay so luong order trong 1 thang
            List<OrderEntity> list = orderRepository.findOrdersByCreateAtBetweenAndStatus(startDateList.get(i), endDateList.get(i), OrderStatus.DONE);
            allMonthlyOrderList.add(list.size());

            //Lay revenue trong 1 thang
            BigDecimal monthlyRevenue = new BigDecimal(0);
            for (int j = 0; j < list.size(); j++) {
                monthlyRevenue = monthlyRevenue.add(list.get(j).getTotalPrice());
            }
            allMonthlyRevenueList.add(monthlyRevenue);
        }

        //Return to API-endpoint
        return new AdminDashboardDTO(systemTotalGuest, systemTotalHost, systemPlacedOrder, totalRevenue, allMonthlyOrderList, allMonthlyRevenueList);
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

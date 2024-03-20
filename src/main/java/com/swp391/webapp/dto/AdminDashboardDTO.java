package com.swp391.webapp.dto;

import com.swp391.webapp.Entity.OrderEntity;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@Setter
@Getter
public class AdminDashboardDTO {
    private int totalGuest;
    private int totalHost;
    private int systemPlacedOrder;
    private BigDecimal systemTotalRevenue;
    private List<String> months;
    private List<Integer> monthlyOrders;
    private List<BigDecimal> monthlyRevenue;

    public AdminDashboardDTO(int totalGuest, int totalHost, int systemPlacedOrder, BigDecimal systemTotalRevenue, List<Integer> monthlyOrders, List<BigDecimal> monthlyRevenue) {
        this.totalGuest = totalGuest;
        this.totalHost = totalHost;
        this.systemPlacedOrder = systemPlacedOrder;
        this.systemTotalRevenue = systemTotalRevenue;
        this.months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        this.monthlyOrders = monthlyOrders;
        this.monthlyRevenue = monthlyRevenue;
    }
}

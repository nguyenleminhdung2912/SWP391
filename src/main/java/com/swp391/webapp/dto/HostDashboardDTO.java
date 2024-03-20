package com.swp391.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostDashboardDTO {
    int totalGuest;
    int totalOrder;
    BigDecimal totalRevenue;
    List<String> months;
    List<BigDecimal> monthlyRevenue;
    List<Integer> monthlyOrder;

    public HostDashboardDTO(int totalGuest, int totalOrder, BigDecimal totalRevenue, List<BigDecimal> monthlyRevenue, List<Integer> monthlyOrder) {
        this.totalGuest = totalGuest;
        this.totalOrder = totalOrder;
        this.totalRevenue = totalRevenue;
        this.months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        this.monthlyRevenue = monthlyRevenue;
        this.monthlyOrder = monthlyOrder;
    }
}

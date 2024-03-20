package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.OrderEntity;
import com.swp391.webapp.Service.HostDashboardService;
import com.swp391.webapp.dto.HostDashboardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/dashboard/host")
public class HostDashboardController {

    @Autowired
    private HostDashboardService hostDashboardService;

    //Total placed order
//    @GetMapping("/total-placed-order")
//    public List<OrderEntity> getHostTotalOrder() {
//        List<OrderEntity> orderList = hostDashboardService.getHostTotalPlacedOrder();
//        return orderList;
//    }
//    @GetMapping("/total-placed-order/{hostId}")
//    public Integer getHostTotalOrder(@PathVariable int hostId) {
//        List<OrderEntity> orderList = hostDashboardService.getHostTotalPlacedOrder(hostId);
//        return orderList.size();
//    }
//
//    //Total customer all time
//
//    @GetMapping("/total-guest-ordered")
//    public Integer getHostTotalOrderedCustomer() {
//        Set<Integer> orderList = hostDashboardService.getHostTotalOrderedCustomer();
//        return orderList.size();
//    }
//    @GetMapping("/total-guest-ordered/{hostId}")
//    public Integer getHostTotalOrderedCustomer(@PathVariable int hostId) {
//        Set<Integer> orderList = hostDashboardService.getHostTotalOrderedCustomer(hostId);
//        return orderList.size();
//    }

    @GetMapping("/detail")
    public HostDashboardDTO getHostDashboardDetail() {
        HostDashboardDTO hostDashboardDetail = hostDashboardService.getHostDashboardDetail();
        return hostDashboardDetail;
    }

    @GetMapping("/detail/{hostId}")
    public HostDashboardDTO getHostDashboardDetail(@PathVariable int hostId) {
        HostDashboardDTO hostDashboardDetail = hostDashboardService.getHostDashboardDetail(hostId);
        return hostDashboardDetail;
    }

    //Total revenue

    //Monthly revenue

}

package com.swp391.webapp.Controller;

import com.swp391.webapp.Service.AdminDashboardService;
import com.swp391.webapp.dto.AdminDashboardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/dashboard/admin")
public class AdminDashboardController {

    @Autowired
    private AdminDashboardService adminDashboardService;

    //All detail
    @GetMapping("/detail")
    public AdminDashboardDTO getAdminDashboardDetail() {
        AdminDashboardDTO adminDashboardDTO = adminDashboardService.getDashboardDetail();
        return adminDashboardDTO;
    }
}

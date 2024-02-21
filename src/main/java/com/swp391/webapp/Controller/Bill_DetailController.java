package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.Bill_Detail_DTO;
import com.swp391.webapp.Service.Bill_DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bill-details")
public class Bill_DetailController {

    @Autowired
    private Bill_DetailService bill_DetailService;

    @GetMapping
    public ResponseEntity<List<Bill_Detail_DTO>> getAllBillDetails() {
        List<Bill_Detail_DTO> billDetails = bill_DetailService.getAllBillDetails();
        return ResponseEntity.ok(billDetails);
    }

    @GetMapping("/{billDetailId}")
    public ResponseEntity<Bill_Detail_DTO> getBillDetailById(@PathVariable int billDetailId) {
        Bill_Detail_DTO billDetail = bill_DetailService.getBillDetailById(billDetailId);
        return ResponseEntity.ok(billDetail);
    }

    @PostMapping
    public ResponseEntity<Bill_Detail_DTO> saveBillDetail(@RequestBody Bill_Detail_DTO billDetail) {
        Bill_Detail_DTO savedBillDetail = bill_DetailService.saveBillDetail(billDetail);
        return ResponseEntity.ok(savedBillDetail);
    }

    @DeleteMapping("/{billDetailId}")
    public ResponseEntity<Void> deleteBillDetail(@PathVariable int billDetailId) {
        bill_DetailService.deleteBillDetail(billDetailId);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints if needed
}

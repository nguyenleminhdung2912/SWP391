package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.BillDTO;
import com.swp391.webapp.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping
    public ResponseEntity<List<BillDTO>> getAllBills() {
        List<BillDTO> bills = billService.getAllBills();
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/{billId}")
    public ResponseEntity<BillDTO> getBillById(@PathVariable int billId) {
        BillDTO bill = billService.getBillById(billId);
        return ResponseEntity.ok(bill);
    }

    @PostMapping
    public ResponseEntity<BillDTO> saveBill(@RequestBody BillDTO bill) {
        BillDTO savedBill = billService.saveBill(bill);
        return ResponseEntity.ok(savedBill);
    }

    @DeleteMapping("/{billId}")
    public ResponseEntity<Void> deleteBill(@PathVariable int billId) {
        billService.deleteBill(billId);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints if needed
}

package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.BillDTO;
import com.swp391.webapp.Repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    // Service methods for Bill entity

    public List<BillDTO> getAllBills() {
        return billRepository.findAll();
    }

    public BillDTO getBillById(int billId) {
        return billRepository.findById(billId).orElse(null);
    }

    public BillDTO saveBill(BillDTO bill) {
        return billRepository.save(bill);
    }

    public void deleteBill(int billId) {
        billRepository.deleteById(billId);
    }

    // Additional service methods if needed
}

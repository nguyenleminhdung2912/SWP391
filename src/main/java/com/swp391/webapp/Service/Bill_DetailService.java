package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.Bill_Detail_DTO;
import com.swp391.webapp.Repository.Bill_Detail_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Bill_DetailService {

    @Autowired
    private Bill_Detail_Repository bill_DetailRepository;

    // Service methods for Bill_Detail entity

    public List<Bill_Detail_DTO> getAllBillDetails() {
        return bill_DetailRepository.findAll();
    }

    public Bill_Detail_DTO getBillDetailById(int billDetailId) {
        return bill_DetailRepository.findById(billDetailId).orElse(null);
    }

    public Bill_Detail_DTO saveBillDetail(Bill_Detail_DTO billDetail) {
        return bill_DetailRepository.save(billDetail);
    }

    public void deleteBillDetail(int billDetailId) {
        bill_DetailRepository.deleteById(billDetailId);
    }

    // Additional service methods if needed
}
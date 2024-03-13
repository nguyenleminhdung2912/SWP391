package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.WalletTransactionEntity;
import com.swp391.webapp.Service.WalletTransactionService;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/transactions")
public class WalletTransactionController {

    @Autowired
    private WalletTransactionService walletTransactionService;
    @Autowired
    private AccountUtils accountUtils;

    @GetMapping("/account/{accountId}")
    public WalletTransactionEntity getWalletTransactionById(@PathVariable int accountId){
        return walletTransactionService.getTransactionByAccountId(accountId);
    }
    @GetMapping("/account/")
    public WalletTransactionEntity getWalletTransactionByAccount(){
        return walletTransactionService.getTransactionByAccount();
    }


}

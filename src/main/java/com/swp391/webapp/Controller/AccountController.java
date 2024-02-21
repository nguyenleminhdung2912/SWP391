package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.AccountDTO;
import com.swp391.webapp.Service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController

public class AccountController {

    AccountService accountService;

    @GetMapping("/getAccount/{id}")
    public Optional<AccountDTO> getAccount(int id){
        Optional<AccountDTO> accountDTO = accountService.getAccountById(id);
        return accountDTO;
    }
}

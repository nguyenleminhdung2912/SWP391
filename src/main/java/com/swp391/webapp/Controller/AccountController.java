package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountEntity>> getAllAccounts() {
        List<AccountEntity> accounts = accountService.getAllAcounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Optional<AccountEntity>> getAccountById(@PathVariable int accountId) {
        Optional<AccountEntity> account = accountService.getAccountById(accountId);
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<AccountEntity> saveAccount(@RequestBody AccountEntity account) {
        AccountEntity savedAccount = accountService.saveAccount(account);
        return ResponseEntity.ok(savedAccount);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable int accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }

}

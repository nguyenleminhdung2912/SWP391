package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.AccountDTO;
import com.swp391.webapp.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountDTO saveAccount(AccountDTO accountDTO) {
        return accountRepository.save(accountDTO);
    }

    public List<AccountDTO> getAllAcounts() {
        return accountRepository.findAll();
    }

    public Optional<AccountDTO> getAccountById(int id) {
        return accountRepository.findById(id);
    }

    public void deleteAccount(int accountId) {
        accountRepository.deleteById(accountId);
    }

}

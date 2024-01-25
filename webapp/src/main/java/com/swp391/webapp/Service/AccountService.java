package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.AccountDTO;
import com.swp391.webapp.Repository.AccountRepository;

import java.util.List;
import java.util.Optional;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountDTO saveAccount(AccountDTO accountDTO) {
        return accountRepository.save(accountDTO);
    }

    public List<AccountDTO> getListUser() {
        return accountRepository.findAll();
    }

    public Optional<AccountDTO> getUserById(int id) {
        return accountRepository.findById(id);
    }

}

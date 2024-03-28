package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Repository.AccountRepository;
import com.swp391.webapp.dto.AccountUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public AccountEntity loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AccountEntity> account = accountRepository.findByEmail(email);
        return account.get();
    }

    public AccountEntity saveAccount(AccountEntity accountEntity) {
        accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
        return accountRepository.save(accountEntity);
    }

    public AccountEntity saveAccountStatus(AccountEntity accountEntity) {
        return accountRepository.save(accountEntity);
    }

    public List<AccountEntity> getAllAcounts() {
        return accountRepository.findAccountsByIsDeleted(0);
    }

    public List<AccountEntity> getAllHost() {
        List<AccountEntity> list = accountRepository.findAccountsByIsDeleted(0);
        List<AccountEntity> listTemp = new ArrayList<AccountEntity>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRole().equals("Host")) {
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }

    public List<AccountEntity> getAllGuest() {
        List<AccountEntity> list = accountRepository.findAccountsByIsDeleted(0);
        List<AccountEntity> listTemp = new ArrayList<AccountEntity>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRole().equals("Guest")) {
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }

    public Optional<AccountEntity> getAccountById(int id) {
        return Optional.of(accountRepository.findById(id).get());
    }

    public void deleteAccount(int accountId) {
        AccountEntity a = accountRepository.findById(accountId).get();
        a.setIsDeleted(1);
        accountRepository.save(a);
    }

    public void refuseAccount(AccountEntity accountEntity) {
        accountEntity.setIsDeleted(1);
        accountRepository.save(accountEntity);
    }

    public AccountEntity updateEachFieldById(int id, Map<String, Objects> fields) {
        Optional<AccountEntity> existingUser = accountRepository.findById(id);
        if (existingUser.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(AccountEntity.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingUser.get(), value);
            });
            return accountRepository.save(existingUser.get());
        }
        return null;
    }

    public AccountEntity updateAccountByID(int id, AccountUpdate accountUpdate) {
        AccountEntity existing = accountRepository.findById(id).get();
        if (!(accountUpdate.getEmail() == null)) {
            existing.setEmail(accountUpdate.getEmail());
        }
        if (!(accountUpdate.getName() == null)) {
            existing.setName(accountUpdate.getName());
        }
        if (!(accountUpdate.getPhone() == null)) {
            existing.setPhone(accountUpdate.getPhone());
        }
        if (!(accountUpdate.getGender() == null)) {
            existing.setGender(accountUpdate.getGender());
        }
        if (!(accountUpdate.getAvatar() == null)) {
            existing.setAvatar(accountUpdate.getAvatar());
        }
        return accountRepository.save(existing);
    }
}

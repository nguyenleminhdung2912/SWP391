package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Repository.AccountRepository;
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
//        AccountDetails accountDetails =  account.map(AccountDetails::new).
//                orElseThrow(() -> new UsernameNotFoundException("User not found" + email));
        return account.get();
    }

    public AccountEntity saveAccount(AccountEntity accountEntity) {
        accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
        return accountRepository.save(accountEntity);
    }

    public List<AccountEntity> getAllAcounts() {
        return accountRepository.findAll();
    }

    public List<AccountEntity> getAllHost() {
        List<AccountEntity> list = accountRepository.findAll();
        List<AccountEntity> listTemp = new ArrayList<AccountEntity>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRole().equals("Host")) {
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }

    public List<AccountEntity> getAllGuest() {
        List<AccountEntity> list = accountRepository.findAll();
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

    public AccountEntity updateAccountByID(int id, AccountEntity accountUpdate) {
        AccountEntity existing = accountRepository.findById(id).get();
        existing.setEmail(accountUpdate.getEmail());
        existing.setPassword(accountUpdate.getPassword());
        existing.setName(accountUpdate.getName());
        existing.setPhone(accountUpdate.getPhone());
        existing.setGender(accountUpdate.getGender());
        existing.setRole(accountUpdate.getRole());
        existing.setAvatar(accountUpdate.getAvatar());
        existing.setStatus(accountUpdate.getStatus());
        return accountRepository.save(existing);
    }
}

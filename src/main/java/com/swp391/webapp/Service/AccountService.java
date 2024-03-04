package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.AccountDTO;
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
    public AccountDTO loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AccountDTO> account = accountRepository.findByEmail(email);
//        AccountDetails accountDetails =  account.map(AccountDetails::new).
//                orElseThrow(() -> new UsernameNotFoundException("User not found" + email));
        return account.get();
    }

    public AccountDTO saveAccount(AccountDTO accountDTO) {
        accountDTO.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        return accountRepository.save(accountDTO);
    }

    public List<AccountDTO> getAllAcounts() {
        return accountRepository.findAll();
    }

    public List<AccountDTO> getAllHost() {
        List<AccountDTO> list = accountRepository.findAll();
        List<AccountDTO> listTemp = new ArrayList<AccountDTO>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRole().equals("Host")) {
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }

    public List<AccountDTO> getAllGuest() {
        List<AccountDTO> list = accountRepository.findAll();
        List<AccountDTO> listTemp = new ArrayList<AccountDTO>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRole().equals("Guest")) {
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }

    public Optional<AccountDTO> getAccountById(int id) {
        return Optional.of(accountRepository.findById(id).get());
    }

    public void deleteAccount(int accountId) {
        accountRepository.deleteById(accountId);
    }

    public AccountDTO updateEachFieldById(int id, Map<String, Objects> fields) {
        Optional<AccountDTO> existingUser = accountRepository.findById(id);
        if (existingUser.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(AccountDTO.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingUser.get(), value);
            });
            return accountRepository.save(existingUser.get());
        }
        return null;
    }

    public AccountDTO updateAccountByID(int id, AccountDTO accountUpdate) {
        AccountDTO existing = accountRepository.findById(id).get();
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

package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.AccountDTO;
import com.swp391.webapp.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<AccountDTO> getAccountById(int id) {
        return Optional.of(accountRepository.findById(id).get());
    }

    public void deleteAccount(int accountId) {
        accountRepository.deleteById(accountId);
    }


}

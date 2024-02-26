package com.swp391.webapp.utils;

import com.swp391.webapp.Entity.AccountDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AccountUtils {

    public AccountDTO getCurrentAccount(){
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (AccountDTO) object;
    }
}

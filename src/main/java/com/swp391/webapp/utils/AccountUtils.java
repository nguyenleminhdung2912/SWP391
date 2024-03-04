package com.swp391.webapp.utils;

import com.swp391.webapp.Entity.AccountEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AccountUtils {

    public AccountEntity getCurrentAccount(){
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (AccountEntity) object;
    }
}

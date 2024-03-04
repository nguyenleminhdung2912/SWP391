package com.swp391.webapp.CustomEndpoint;

import com.swp391.webapp.Entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountAndToken {
    AccountEntity accountEntity;
    String tokens;
}

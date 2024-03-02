package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.AccountDTO;
import com.swp391.webapp.Entity.EmailDetail;
import com.swp391.webapp.ExceptionHandler.NotFoundException;
import com.swp391.webapp.Service.AccountService;
import com.swp391.webapp.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Service
@CrossOrigin("*")
public class MailController {

    @Autowired
    EmailService emailService;
    @Autowired
    AccountService accountService;

//    @GetMapping("/test/{id}")
    public void sendMail(AccountDTO accountDTO){
        try {
            Optional<AccountDTO> account = accountService.getAccountById(accountDTO.getAccountID());
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setRecipient(account.get().getEmail());
            emailDetail.setSubject("test123");
            emailDetail.setMsgBody("Test send vertify mail");
            emailDetail.setName(accountDTO.getName());
            emailService.sendMailTemplate(emailDetail);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String activateAccount(){
        return "Verified";
    }
}

package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Entity.EmailDetail;
import com.swp391.webapp.Service.AccountService;
import com.swp391.webapp.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Service
@CrossOrigin("*")
public class MailController {

    @Autowired
    EmailService emailService;
    @Autowired
    AccountService accountService;

//    @GetMapping("/test/{id}")
    public void sendMail(AccountEntity accountEntity){
        try {
            Optional<AccountEntity> account = accountService.getAccountById(accountEntity.getAccountID());
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setRecipient(account.get().getEmail());
            emailDetail.setSubject("test123");
            emailDetail.setMsgBody("Test send vertify mail");
            emailDetail.setName(accountEntity.getName());
            emailService.sendMailTemplate(emailDetail);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendHostCongrats(AccountEntity accountEntity) {
        try {
            Optional<AccountEntity> account = accountService.getAccountById(accountEntity.getAccountID());
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setRecipient(account.get().getEmail());
            emailDetail.setSubject("Congratulation!");
            emailDetail.setMsgBody("Your account has been verified!");
            emailDetail.setName(accountEntity.getName());
            emailService.sendCongratsHostTemplate(emailDetail);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String activateAccount(){
        return "Verified";
    }
}

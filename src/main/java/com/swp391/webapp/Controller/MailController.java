package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Entity.EmailDetail;
import com.swp391.webapp.Service.AccountService;
import com.swp391.webapp.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

//@Service
@RestController
@CrossOrigin("*")
public class MailController {

    @Autowired
    EmailService emailService;
    @Autowired
    AccountService accountService;

    @GetMapping("/test/sendMail")
    public void sendMail(AccountEntity accountEntity){
        try {
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setRecipient(accountEntity.getEmail());
            emailDetail.setName(accountEntity.getName());
//            emailDetail.setRecipient("nguyenleminhdung2912@gmail.com");
            emailDetail.setSubject("Verify account");
            emailDetail.setMsgBody("Please verify your account");
//            emailDetail.setName("Minh Dung");
            emailService.sendMailTemplate(emailDetail);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/test/sendHostCongrats")
    public void sendHostCongrats(AccountEntity accountEntity) {
        try {
            Optional<AccountEntity> account = accountService.getAccountById(accountEntity.getAccountID());
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setRecipient(account.get().getEmail());
            emailDetail.setName(accountEntity.getName());
//            emailDetail.setRecipient("nguyenleminhdung2912@gmail.com");
            emailDetail.setSubject("Congratulation!");
            emailDetail.setMsgBody("Your account has been verified!");
//            emailDetail.setName(accountEntity.getName());
            emailService.sendCongratsHostTemplate(emailDetail);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/test/sendHostAccountRefused")
    public void sendHostAccountRefused(AccountEntity accountEntity) {
        try {
            Optional<AccountEntity> account = accountService.getAccountById(accountEntity.getAccountID());
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setRecipient(account.get().getEmail());
            emailDetail.setName(accountEntity.getName());
//            emailDetail.setRecipient("nguyenleminhdung2912@gmail.com");
            emailDetail.setSubject("We are sorry!");
            emailDetail.setMsgBody("Your account has been refused!");
//            emailDetail.setName(accountEntity.getName());
            emailService.sendHostAccountIsRefused(emailDetail);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/test/sendHostGuestHasCancelled")
    public void sendHostGuestHasCancelled(AccountEntity accountEntity) {
        try {
            Optional<AccountEntity> account = accountService.getAccountById(accountEntity.getAccountID());
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setRecipient(account.get().getEmail());
            emailDetail.setName(accountEntity.getName());
//            emailDetail.setRecipient("nguyenleminhdung2912@gmail.com");
            emailDetail.setSubject("Congratulation!");
            emailDetail.setMsgBody("Your account has been verified!");
//            emailDetail.setName(accountEntity.getName());
            emailService.sendHostGuestHasCancelled(emailDetail);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/test/sendGuestBookingInformation")
    public void sendGuestBookingInformation(AccountEntity accountEntity) {
        try {
            Optional<AccountEntity> account = accountService.getAccountById(accountEntity.getAccountID());
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setRecipient(account.get().getEmail());
            emailDetail.setName(accountEntity.getName());
//            emailDetail.setRecipient("nguyenleminhdung2912@gmail.com");
            emailDetail.setSubject("Congratulation!");
            emailDetail.setMsgBody("Your account has been verified!");
//            emailDetail.setName(accountEntity.getName());
            emailService.sendGuestBookingInformation(emailDetail);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/test/sendGuestHostHasRefusedOrder")
    public void sendGuestHostHasRefusedOrder(AccountEntity accountEntity) {
        try {
            Optional<AccountEntity> account = accountService.getAccountById(accountEntity.getAccountID());
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setRecipient(account.get().getEmail());
            emailDetail.setName(accountEntity.getName());
//            emailDetail.setRecipient("nguyenleminhdung2912@gmail.com");
            emailDetail.setSubject("Congratulation!");
            emailDetail.setMsgBody("Your account has been verified!");
//            emailDetail.setName(accountEntity.getName());
            emailService.sendGuestHostHasRefusedOrder(emailDetail);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/test/sendGuestHostHasAcceptOrder")
    public void sendGuestHostHasAcceptOrder(AccountEntity accountEntity) {
        try {
            Optional<AccountEntity> account = accountService.getAccountById(accountEntity.getAccountID());
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setRecipient(account.get().getEmail());
            emailDetail.setName(accountEntity.getName());
//            emailDetail.setRecipient("nguyenleminhdung2912@gmail.com");
            emailDetail.setSubject("Congratulation!");
            emailDetail.setMsgBody("Your account has been verified!");
//            emailDetail.setName(accountEntity.getName());
            emailService.sendGuestHostHasAcceptOrder(emailDetail);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String activateAccount(){
        return "Verified";
    }
}

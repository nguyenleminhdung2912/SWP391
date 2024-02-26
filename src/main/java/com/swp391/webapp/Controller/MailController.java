package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.EmailDetail;
import com.swp391.webapp.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    EmailService emailService;

    @GetMapping("test")
    public void sendMail(){
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setRecipient("nguyenleminhdung2912@gmail.com");
        emailDetail.setSubject("test123");
        emailDetail.setMsgBody("Test send vertify mail");
        emailService.sendMailTemplate(emailDetail);
    }
}

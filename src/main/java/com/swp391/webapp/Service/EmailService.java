package com.swp391.webapp.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import com.swp391.webapp.Entity.EmailDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;
    public void sendMailTemplate(EmailDetail emailDetail){
        try{
            Context context = new Context();

            context.setVariable("name", emailDetail.getName());
//            context.setVariable("name", "Minh Dung");

//            String link = "http://localhost:5173/login" + emailDetail.getRecipient();
            String link = "http://localhost:5173/login";

            context.setVariable("link", link);


            String text = templateEngine.process("emailtemplate", context);

            // Creating a simple mail message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // Setting up necessary details
            mimeMessageHelper.setFrom("admin@gmail.com");
            mimeMessageHelper.setTo(emailDetail.getRecipient());
            mimeMessageHelper.setText(text, true);
            mimeMessageHelper.setSubject(emailDetail.getSubject());
            javaMailSender.send(mimeMessage);
        }catch (MessagingException messagingException){
            messagingException.printStackTrace();
        }
    }

    public void sendCongratsHostTemplate(EmailDetail emailDetail){
        try{
            Context context = new Context();

            context.setVariable("name", emailDetail.getName());

            String text = templateEngine.process("emailtemplate2", context);

            // Creating a simple mail message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // Setting up necessary details
            mimeMessageHelper.setFrom("admin@gmail.com");
            mimeMessageHelper.setTo(emailDetail.getRecipient());
            mimeMessageHelper.setText(text, true);
            mimeMessageHelper.setSubject(emailDetail.getSubject());
            javaMailSender.send(mimeMessage);
        }catch (MessagingException messagingException){
            messagingException.printStackTrace();
        }
    }

    public void sendHostGuestHasCancelled(EmailDetail emailDetail) {
        try{
            Context context = new Context();

            context.setVariable("name", emailDetail.getName());
//            context.setVariable("name", "Minh Dung");

//            String link = "http://localhost:5173/login" + emailDetail.getRecipient();
            String link = "http://birthdayblitzhub.online/";

            context.setVariable("link", link);


            String text = templateEngine.process("CustomerCancelled", context);

            // Creating a simple mail message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // Setting up necessary details
            mimeMessageHelper.setFrom("admin@gmail.com");
            mimeMessageHelper.setTo(emailDetail.getRecipient());
            mimeMessageHelper.setText(text, true);
            mimeMessageHelper.setSubject(emailDetail.getSubject());
            javaMailSender.send(mimeMessage);
        }catch (MessagingException messagingException){
            messagingException.printStackTrace();
        }
    }

    public void sendGuestBookingInformation(EmailDetail emailDetail) {
        try{
            Context context = new Context();

            context.setVariable("name", emailDetail.getName());
//            context.setVariable("name", "Minh Dung");

//            String link = "http://localhost:5173/login" + emailDetail.getRecipient();
            String link = "http://birthdayblitzhub.online/";

            context.setVariable("link", link);


            String text = templateEngine.process("BookingSuccessful", context);

            // Creating a simple mail message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // Setting up necessary details
            mimeMessageHelper.setFrom("admin@gmail.com");
            mimeMessageHelper.setTo(emailDetail.getRecipient());
            mimeMessageHelper.setText(text, true);
            mimeMessageHelper.setSubject(emailDetail.getSubject());
            javaMailSender.send(mimeMessage);
        }catch (MessagingException messagingException){
            messagingException.printStackTrace();
        }
    }

    public void sendGuestHostHasRefusedOrder(EmailDetail emailDetail) {
        try{
            Context context = new Context();

            context.setVariable("name", emailDetail.getName());
//            context.setVariable("name", "Minh Dung");

//            String link = "http://localhost:5173/login" + emailDetail.getRecipient();
            String link = "http://birthdayblitzhub.online/";

            context.setVariable("link", link);

            String text = templateEngine.process("RefuseOrder", context);

            // Creating a simple mail message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // Setting up necessary details
            mimeMessageHelper.setFrom("admin@gmail.com");
            mimeMessageHelper.setTo(emailDetail.getRecipient());
            mimeMessageHelper.setText(text, true);
            mimeMessageHelper.setSubject(emailDetail.getSubject());
            javaMailSender.send(mimeMessage);
        }catch (MessagingException messagingException){
            messagingException.printStackTrace();
        }
    }

    public void sendGuestHostHasAcceptOrder(EmailDetail emailDetail) {
        try{
            Context context = new Context();

            context.setVariable("name", emailDetail.getName());
//            context.setVariable("name", "Minh Dung");

//            String link = "http://localhost:5173/login" + emailDetail.getRecipient();
            String link = "http://birthdayblitzhub.online/";

            context.setVariable("link", link);

            String text = templateEngine.process("OrderAccepted", context);

            // Creating a simple mail message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // Setting up necessary details
            mimeMessageHelper.setFrom("admin@gmail.com");
            mimeMessageHelper.setTo(emailDetail.getRecipient());
            mimeMessageHelper.setText(text, true);
            mimeMessageHelper.setSubject(emailDetail.getSubject());
            javaMailSender.send(mimeMessage);
        }catch (MessagingException messagingException){
            messagingException.printStackTrace();
        }
    }
}

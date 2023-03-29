package com.spring.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

/**
 * Created by oguzhanaslan on 07.09.2020.
 */

@Service
public class MailService {

    @Setter
    @Autowired
    UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    public long sendMail(String userEmail,String password){
        System.out.println("Mail service");
        long code = (long) Math.floor(Math.random() * 899999L) + 100000L;
        String url = "http://localhost:8090/verification?email="+userEmail+"&code="+code+"&password="+password;
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMsg = "<p><h3>Üyeliğinizi tamamlamak   için Lütfen Linke Tıklayınız:</h3><p><a href='"+url+"'>"+"link"+"</a>";
        try {
            helper.setText(htmlMsg, true); // Use this or above line.
            helper.setTo(userEmail);
            helper.setSubject("Üyelik Tamamlama");
            helper.setFrom("cificidefteri@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(mimeMessage);
        return code;



    }
    public Boolean resetpassword(String email){


        String url;
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 4) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        url = "http://localhost:8090/resetpassword?email="+email+"&token="+saltStr;
        url.toLowerCase();
        saltStr.toLowerCase();
        System.out.println(url);
        if(userService.insertpwcode(email,saltStr))
            System.out.println("KOD YERLEŞTİRME BAŞARILI");
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMsg = "<p><h3>Ekrandaki kodu uygulamaya giriniz: "+salt+"</h3><p>";
        try {
            helper.setText(htmlMsg, true);
            helper.setTo(email);
            helper.setSubject("Parolanızı Sıfırlama");
            helper.setFrom("cificidefteri@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(mimeMessage);



        return true;
    }






}

package com.kgc.easybuy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SendEmail {

    @Autowired
    private JavaMailSender javaMailSender;

    public int sendEmail(String userName){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 发件人
        simpleMailMessage.setFrom(userName);
        // 收件人
        simpleMailMessage.setTo(userName);
        // 邮件主题
        simpleMailMessage.setSubject("邮箱验证码");
        Random random = new Random();
        int code = 1000 + random.nextInt(9000);        // 邮件内容
        simpleMailMessage.setText(String.valueOf(code));
        javaMailSender.send(simpleMailMessage);
        return code;
    }
}

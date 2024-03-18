package com.kgc.easybuy.config;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Value;
=======
>>>>>>> 478cdf7045a7b655be12098bdee6592429f68323
=======
>>>>>>> 478cdf7045a7b655be12098bdee6592429f68323
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SendEmail {

    @Autowired
    private JavaMailSender javaMailSender;
<<<<<<< HEAD
<<<<<<< HEAD
    @Value("${spring.mail.username}")
    private String from;
=======
>>>>>>> 478cdf7045a7b655be12098bdee6592429f68323
=======
>>>>>>> 478cdf7045a7b655be12098bdee6592429f68323

    public int sendEmail(String userName){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 发件人
<<<<<<< HEAD
<<<<<<< HEAD
        simpleMailMessage.setFrom(from);
=======
        simpleMailMessage.setFrom(userName);
>>>>>>> 478cdf7045a7b655be12098bdee6592429f68323
=======
        simpleMailMessage.setFrom(userName);
>>>>>>> 478cdf7045a7b655be12098bdee6592429f68323
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

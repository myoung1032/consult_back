package sustech.learn.consult.config.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import sustech.learn.consult.config.EmailSender;

@Service
public class EmailSenderImpl implements EmailSender {
    private JavaMailSender mailSender;

    public EmailSenderImpl(@Autowired JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
    }

    @Override
    public void send(String email, String subject, String htmlText) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //mailMessage.setFrom("1587912667@qq.com");
        mailMessage.setFrom("learningc@sustech.edu.cn");
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(htmlText);
        mailSender.send(mailMessage);
    }
}

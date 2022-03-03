package sustech.learn.consult.config;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailConfig {
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
       // mailSender.setHost("smtp.qq.com");
        mailSender.setPort(465);
        mailSender.setHost("smtp.exmail.qq.com");
       // mailSender.setUsername("1587912667@qq.com");

        mailSender.setUsername("learningc@sustech.edu.cn");
        mailSender.setPassword("2021Gogo");
      //  mailSender.setPassword("ecpnkkefeakkbadh");


      //  mailSender.setUsername("learningc@sustech.edu.cn");
      //  mailSender.setPassword("2020Go!");

        Properties props = mailSender.getJavaMailProperties();
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.setProperty("mail.smtps.host", "smtp.exmail.qq.com");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
//        props.setProperty("mail.smtp.port", "465");xx
//        props.setProperty("mail.smtp.socketFactory.port", "465");xx

        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
//        props.setProperty("mail.debug", "true");

        return mailSender;
    }
}

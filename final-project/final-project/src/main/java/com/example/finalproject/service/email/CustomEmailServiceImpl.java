package com.example.finalproject.service.email;

import com.example.finalproject.properties.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class CustomEmailServiceImpl implements CustomEmailService {
    private final JavaMailSender emailSender;
    private final MailProperties mailProperties;

    public CustomEmailServiceImpl(JavaMailSender emailSender, MailProperties mailProperties) {
        this.emailSender = emailSender;
        this.mailProperties = mailProperties;
    }

    @Override
    public boolean sendCodeEmail(String email, String code) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
        String htmlMessage = "<h3>To confirm registration, please follow the link</h3>"
                + "<a href = \"http://localhost:4200/confirm/?code=" + code + "\"> Click !!!";
        message.setContent(htmlMessage, "text/html");
        helper.setFrom("<" + mailProperties.getLogin() + ">");
        helper.setTo(email + "<" + email + ">");
        helper.setSubject("Confirming registration");
        this.emailSender.send(message);
        return true;
    }

}

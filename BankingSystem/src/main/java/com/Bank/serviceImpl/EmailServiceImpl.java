package com.Bank.serviceImpl;

import com.Bank.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public String sendEmail(String email, String subject, String body) {

        if (!StringUtils.hasText(email) || !StringUtils.hasText(subject) || !StringUtils.hasText(body)) {
            return "Invalid input: Email, subject, and body must not be empty";
        }
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("arup71062@gmail.com");
            mailSender.send(message);
            logger.info("Mail sent successfully to {}", email);
            return "Email sent successfully to " + email;
        } catch (Exception e) {
            logger.error("Failed to send email to {}", email, e);
            return "Failed to send email";
        }

    }
}

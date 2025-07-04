package com.mycompany.DynamicMailSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.task.TaskExecutionProperties.Simple;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        // Implementation for sending email
        // This method will use the JavaMailSender to send an email
        // Example:
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}

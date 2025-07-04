package com.mycompany.DynamicMailSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void sendGroupEmail(String group, List<String> emails, String subject, String message, String sentBy) {
        for (String email : emails) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject(subject);
            mailMessage.setText(message);
            mailSender.send(mailMessage);
        }
        // Log the action in email_actions table
        jdbcTemplate.update(
            "INSERT INTO email_actions (recipient_group, subject, message, sent_by) VALUES (?, ?, ?, ?)",
            group, subject, message, sentBy
        );
    }
}
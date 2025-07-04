package com.mycompany.DynamicMailSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.DynamicMailSystem.service.EmailService;

import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return """
        <!DOCTYPE html>
        <html>
        <head>
            <title>Dynamic Mail System</title>
            <style>
                body { font-family: Arial, sans-serif; margin: 40px; }
                button { margin: 10px; padding: 15px 30px; font-size: 16px; }
            </style>
        </head>
        <body>
            <h2>Send Email</h2>
            <button onclick="location.href='/send-email/employees'">Send to Employees</button>
            <button onclick="location.href='/send-email/staff'">Send to Staff</button>
            <button onclick="location.href='/send-email/executives'">Send to Executives</button>
            <button onclick="location.href='/send-email/all'">Send to All</button>
            <br/><br/>
            <button onclick="location.href='/register'">Register User</button>
        </body>
        </html>
        """;
    }

    @GetMapping("/send-email/employees")
    @ResponseBody
    public String sendToEmployees() {
        String subject = "Hello Employees";
        String message = "This is a message for employees.";
        String sentBy = "admin";
        List<String> emails = jdbcTemplate.queryForList("SELECT email FROM employees", String.class);
        emailService.sendGroupEmail("employees", emails, subject, message, sentBy);
        return "Emails sent to Employees!";
    }

    @GetMapping("/send-email/staff")
    @ResponseBody
    public String sendToStaff() {
        String subject = "Hello Staff";
        String message = "This is a message for staff.";
        String sentBy = "admin";
        List<String> emails = jdbcTemplate.queryForList("SELECT email FROM staff", String.class);
        emailService.sendGroupEmail("staff", emails, subject, message, sentBy);
        return "Emails sent to Staff!";
    }

    @GetMapping("/send-email/executives")
    @ResponseBody
    public String sendToExecutives() {
        String subject = "Hello Executives";
        String message = "This is a message for executives.";
        String sentBy = "admin";
        List<String> emails = jdbcTemplate.queryForList("SELECT email FROM executives", String.class);
        emailService.sendGroupEmail("executives", emails, subject, message, sentBy);
        return "Emails sent to Executives!";
    }

    @GetMapping("/send-email/all")
    @ResponseBody
    public String sendToAll() {
        String subject = "Hello All";
        String message = "This is a message for everyone.";
        String sentBy = "admin";
        List<String> emails = jdbcTemplate.queryForList(
            "SELECT email FROM employees UNION SELECT email FROM staff UNION SELECT email FROM executives", String.class);
        emailService.sendGroupEmail("all", emails, subject, message, sentBy);
        return "Emails sent to All!";
    }
}
package com.mycompany.DynamicMailSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.mycompany.DynamicMailSystem.service.EmailService;

@Controller
public class ContactController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/contact")
    @ResponseBody
    public String contactForm() {
        return """
        <!DOCTYPE html>
        <html>
        <head>
            <title>Contact Company</title>
            <style>
                body { font-family: Arial, sans-serif; margin: 40px; }
                form { max-width: 400px; margin: auto; }
                label { display: block; margin-top: 15px; }
                input, textarea { width: 100%; padding: 8px; margin-top: 5px; }
                button { margin-top: 20px; padding: 10px 20px; font-size: 16px; }
            </style>
        </head>
        <body>
            <h2>Contact Company</h2>
            <form method="post" action="/contact">
                <label for="fromEmail">Your Email:</label>
                <input type="email" id="fromEmail" name="fromEmail" required />

                <label for="subject">Subject:</label>
                <input type="text" id="subject" name="subject" required />

                <label for="message">Message:</label>
                <textarea id="message" name="message" rows="5" required></textarea>

                <button type="submit">Send</button>
            </form>
        </body>
        </html>
        """;
    }

    @PostMapping("/contact")
    @ResponseBody
    public String handleContact(
            @RequestParam String fromEmail,
            @RequestParam String subject,
            @RequestParam String message) {

        // Replace with your company's email address
        String companyEmail = "faizs7412@gmail.com";
        String fullMessage = "From: " + fromEmail + "\n\n" + message;

        emailService.sendGroupEmail("company", java.util.List.of(companyEmail), subject, fullMessage, fromEmail);

        return "<h2>Your message has been sent to the company!</h2><a href='/'>Go Home </a>";
    }
}
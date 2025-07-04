package com.mycompany.DynamicMailSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/register")
    @ResponseBody
    public String registerPage() {
        return """
        <!DOCTYPE html>
        <html>
        <head>
            <title>User Registration</title>
            <style>
                body { font-family: Arial, sans-serif; margin: 40px; }
                form { max-width: 400px; margin: auto; }
                label { display: block; margin-top: 15px; }
                input, select { width: 100%; padding: 8px; margin-top: 5px; }
                button { margin-top: 20px; padding: 10px 20px; font-size: 16px; }
            </style>
        </head>
        <body>
            <h2>User Registration</h2>
            <form method="post" action="/register">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required />

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required />

                <label for="type">User Type:</label>
                <select id="type" name="type" required>
                    <option value="">Select user type</option>
                    <option value="employee">Employee</option>
                    <option value="staff">Staff</option>
                    <option value="executive">Executive</option>
                    <option value="customer">Customer</option>
                </select>

                <button type="submit">Register</button>
            </form>
        </body>
        </html>
        """;
    }

    @PostMapping("/register")
    @ResponseBody
    public String registerUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String type) {

        switch (type) {
            case "employee" -> jdbcTemplate.update(
                    "INSERT INTO employees (name, email, department) VALUES (?, ?, ?)",
                    name, email, "General"
            );
            case "staff" -> jdbcTemplate.update(
                    "INSERT INTO staff (name, email, role) VALUES (?, ?, ?)",
                    name, email, "General"
            );
            case "executive" -> jdbcTemplate.update(
                    "INSERT INTO executives (name, email, position) VALUES (?, ?, ?)",
                    name, email, "General"
            );
            case "customer" -> jdbcTemplate.update(
                    "INSERT INTO customers (name, email) VALUES (?, ?)",
                    name, email
            );
        }

        return "<h2>Registration successful!</h2><a href='/'>Go Home</a>";
    }
}
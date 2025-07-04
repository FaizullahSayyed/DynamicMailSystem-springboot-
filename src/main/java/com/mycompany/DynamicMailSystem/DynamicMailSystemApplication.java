package com.mycompany.DynamicMailSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.mycompany.DynamicMailSystem.service.EmailSenderService;

@SpringBootApplication
public class DynamicMailSystemApplication {
	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(DynamicMailSystemApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void sendEmail() {
		// Example usage of the email sender service
		emailSenderService.sendEmail("faizatphonepe@gmail.com","Test Email", "This is a test email from DynamicMailSystem.");
		System.out.println("Email sent successfully!");
	}
}


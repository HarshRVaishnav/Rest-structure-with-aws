package com.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.EmailRequest;
import com.example.service.EmailService;

@RestController
public class EmailController {
	
	@Autowired
	private EmailService emailService;	
	
	
	@PostMapping("/sendEmail")
    public void sendEmail(@ModelAttribute EmailRequest request) {
        emailService.sendEmail(request.getTo(), request.getSubject(), request.getBody());
    }

}
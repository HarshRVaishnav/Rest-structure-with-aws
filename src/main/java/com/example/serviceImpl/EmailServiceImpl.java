package com.example.serviceImpl;

import org.springframework.stereotype.Service;

import com.example.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@Service
public class EmailServiceImpl implements EmailService{

		@Autowired
		private AmazonSimpleEmailService amazonSimpleEmailService;

		@Override
		public void sendEmail(String to, String subject, String body) {
			Destination destination = new Destination().withToAddresses(to);
	        Content subjectContent = new Content().withData(subject);
	        Content bodyContent = new Content().withData(body);
	        Body emailBody = new Body().withHtml(bodyContent);
	        Message message = new Message(subjectContent, emailBody); 
	        SendEmailRequest request = new SendEmailRequest()
	                .withSource("pawanbhujade28@gmail.com")
	                .withDestination(destination)
	                .withMessage(message);
	        amazonSimpleEmailService.sendEmail(request);

		}
}
package com.example.fileUpload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;


public class AmazonConfig {
	
	@Value("${AWS_ACCESS_KEY_ID}")
	private String AWS_ACCESS_KEY_ID;

	@Value("${AWS_SECRET_ACCESS_KEY}")
	private String AWS_SECRET_ACCESS_KEY;
	
	
	
	
   
}
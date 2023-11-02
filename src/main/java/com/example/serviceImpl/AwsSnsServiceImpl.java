package com.example.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.iotdata.model.PublishRequest;
import com.amazonaws.services.sns.AmazonSNS;
import com.example.service.AwsSnsService;

@Service
public class AwsSnsServiceImpl implements AwsSnsService {

	
	@Autowired
    private AmazonSNS amazonSNSClient;

    @Value("${aws.sns.topic}")
    private String snsTopicArn;
	
	
	@Override
	public void publishMessage(String message) {
//		 PublishRequest publishRequest = new PublishRequest();
//	        amazonSNSClient.publishBatch(publishRequest);
//		
	}
}

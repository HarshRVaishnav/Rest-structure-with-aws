package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Configuration
public class AwsSnsConfig {

    @Value("${aws.region}")
    private String awsRegion;

    @Bean
    public AmazonSNS amazonSNSClient() {
        return AmazonSNSClientBuilder.standard()
                .withRegion(Regions.fromName(awsRegion))
                .build();
    }
}

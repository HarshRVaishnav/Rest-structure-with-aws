package com.example.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class AmazonS3Service {
	
	private AmazonS3 amazonS3;
    private String bucketName;
    
    public AmazonS3Service(@Value("${access_key}") String accessKeyId, 
                           @Value("${secretkey}") String secretAccessKey, 
                           @Value("${region}") String region, 
                           @Value("${bucketName}") String bucketName) {
        AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        amazonS3 = AmazonS3ClientBuilder.standard().withRegion(region).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
        this.bucketName = bucketName;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String key = "product-images/" + UUID.randomUUID().toString() + "/" + fileName;
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        amazonS3.putObject(bucketName, key, file.getInputStream(), metadata);
        return key;
    }

}

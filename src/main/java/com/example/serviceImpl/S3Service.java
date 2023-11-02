package com.example.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.example.service.Fileservice;

@Service
public class S3Service implements Fileservice{

	@Autowired
	private   AmazonS3 amazonS3;
	
	@Value("${bucketName}")
	private String bucketname;
	
	@Override
	public String saveFile(MultipartFile file) {
		String originalfileName=file.getOriginalFilename();
	
		try{
		File file2=convertMultiPartToFile(file);
		
	PutObjectResult objectResult=	amazonS3.putObject(bucketname,originalfileName,file2);
	
	return objectResult.getContentMd5();
		}
		catch (Exception e) {
		throw new RuntimeException(e);
		}
	
	}

	@Override
	public byte[] downloadFile(String filename) {
		
		return null;
	}

	@Override
	public String deletefile(String fileName) {
		return null;
	}

	
	  private File convertMultiPartToFile(MultipartFile file ) throws IOException
	    {
	        File convFile = new File( file.getOriginalFilename() );
	        FileOutputStream   fos = new FileOutputStream( convFile );
	        fos.write( file.getBytes() );
	        fos.close();
	        return convFile;
	    }
	
}

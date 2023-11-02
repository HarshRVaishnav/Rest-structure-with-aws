package com.example.entity;

import java.io.File;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Entity
public class ProductImage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productImageId;

	private Integer productCode;

	private String fileName;

	private long fileSize;

	private String fileType;

}

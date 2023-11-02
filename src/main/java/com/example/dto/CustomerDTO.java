package com.example.dto;

import lombok.Data;

@Data
public class CustomerDTO {
	
	private Integer customerNumber;

    
    private String customerFirstName;

    private String customerLastName;

    private Long phone;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private Integer postalCode;

    private String country;

}

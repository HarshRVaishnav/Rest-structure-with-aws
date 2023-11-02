package com.example.dto;

import java.util.List;

import com.example.entity.OrderDetails;

import lombok.Data;

@Data
public class BulkRequestDTO {
	
	private List<OrderDetails> details;

	

	   
}

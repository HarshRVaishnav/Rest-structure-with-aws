package com.example.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.entity.Customer;
import com.example.entity.OrderDetails;
import com.example.entity.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class OrderDTO {

	private Integer orderNumber;

	private LocalDate orderDate;
	
	private Date shippedDate;

	private Status status=Status.ORDERED;

	private String comments;
   
	@JsonIgnore
	private Customer customer;
	private Integer customerNumber;
	

	
	private List<OrderDetailDTO> orderDetails;
	
	public List<OrderDetailDTO> getOrderDetails() {
		if (orderDetails == null) {
			orderDetails = new ArrayList<OrderDetailDTO>();
		}
		return orderDetails;
	}

   
}

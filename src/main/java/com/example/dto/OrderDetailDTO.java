package com.example.dto;

import lombok.Data;

@Data
public class OrderDetailDTO {
	private Integer orderId;
	
	private Integer orderNumber;

	private Integer productCode;

	private Integer quantityOrdered;

	private Double priceEach;
	
	

	public OrderDetailDTO(Integer orderNumber, Integer productCode, Integer quantityOrdered, Double priceEach) {
		super();
		this.orderNumber = orderNumber;
		this.productCode = productCode;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
	}

	public OrderDetailDTO() {
		// TODO Auto-generated constructor stub
	}




	

}

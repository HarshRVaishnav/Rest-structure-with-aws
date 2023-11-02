package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entity.OrderDetails;
import com.example.entity.Product;

public interface IOrderDetailService {

	

	void saveAll(List<OrderDetails> orderDetailsList);

	
	public void saveAllInBatch(List<OrderDetails> orderDetailsList);
	
	
	
	
}

package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Order;
import com.example.entity.OrderDetails;
import com.example.entity.Product;
import com.example.repository.IOrderDetailsRepo;
import com.example.service.IOrderDetailService;
import com.example.service.IOrderService;
import com.example.service.IProductService;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailsController {
	
	

	@Autowired
	private IOrderDetailService iOrderdetailService;
	
	
	
	
}  

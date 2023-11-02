package com.example.controller;


import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.ErrorDetails;
import com.example.dto.OrderDTO;
import com.example.dto.SuccessDetails;
import com.example.entity.Order;
import com.example.service.IOrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private IOrderService iOrderService;

	@Autowired
	private ModelMapper modelMapper;
		
	@PostMapping
	@Transactional
	public ResponseEntity<?> placeOrder(@Valid @RequestBody OrderDTO orderDTO) {
		try { 
			
		
		Order order = modelMapper.map(orderDTO, Order.class);
		Order order2 = iOrderService.createOrder(order);
		OrderDTO order3 = modelMapper.map(order2, OrderDTO.class);
		
		return SuccessDetails.success("Order place successfully", order3, HttpStatus.CREATED);
		} catch (Exception e) {
			
			return ErrorDetails.error("Order Not Place",  "RESTAPI_ERR_02");
		}
	}
	

	@PostMapping("/p")
	@Transactional
	public ResponseEntity<?> placeOrder3(@Valid @RequestBody OrderDTO orderDTO) {
		try {
			
		
		Order order = modelMapper.map(orderDTO, Order.class);
		Order order2 = iOrderService.createOrder3(order);
		OrderDTO order3 = modelMapper.map(order2, OrderDTO.class);
		
		return SuccessDetails.success("Order place successfully", order3, HttpStatus.CREATED);
		} catch (Exception e) {
			
			return ErrorDetails.error("Order Not Place",  "RESTAPI_ERR_02");
		}
	}
	   
	
	
	
	@PostMapping("/post")
	@Transactional
	public ResponseEntity<?> placeOrder2(@Valid @RequestBody OrderDTO orderDTO) {
		Order order = modelMapper.map(orderDTO, Order.class);
		Order order2 = iOrderService.createOrder2(order);
		OrderDTO order3 = modelMapper.map(order2, OrderDTO.class);
		return SuccessDetails.success("Order Fetch Successfully", order3,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllOrder() {
		List<Order> list = iOrderService.getAllOrder();

		return SuccessDetails.success("Order Fetch Successfully", list,HttpStatus.OK);
	}
	
	@GetMapping("/{orderNumber}")
	public ResponseEntity<?> getOrder(@PathVariable Integer orderNumber, @RequestBody Order order)
	{
		Order order2=iOrderService.findOrder(orderNumber);
		return SuccessDetails.success("Order Fetch Successfully", order2,HttpStatus.OK);
	}
	
	

}
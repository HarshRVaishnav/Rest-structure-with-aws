package com.example.service;

import java.util.List;
import com.example.entity.Order;
import com.example.entity.OrderDetails;

public interface IOrderService {

	//public Order placeOrder(Order order);

	public List<Order> getAllOrder();
	
	public Order getOrderByID(Integer orderNumber);
  
	public Order findOrder(Integer orderNumber);
	
	public Order createOrder(Order orderDto);

	public Order createOrder2(Order order);

	 public Order createOrder3(Order order);
	
	//public List<OrderDetails> saveOrderDetails(List<OrderDetails> details);
	
	//public Order addOrder(Order order);
}

package com.example.batchInsert;

import com.example.entity.Order;
import com.example.entity.OrderDetails;

public class TestObjectHelper {
	
	public static Order createOrder(int nameidentifier)
	
	{
		Order order=new Order();
		order.setCustomerNumber(order.getCustomerNumber());
		
		return order;
	}
public static OrderDetails createOrderdetail(int nameidentifier)
	
	{
		OrderDetails orderDetails=new OrderDetails();
		orderDetails.setOrderNumber(orderDetails.getOrderNumber());
		orderDetails.setProductCode(orderDetails.getProductCode());
		orderDetails.setPriceEach(orderDetails.getPriceEach());
		orderDetails.setQuantityOrdered(orderDetails.getQuantityOrdered());
		
		return orderDetails;
	}
	

}

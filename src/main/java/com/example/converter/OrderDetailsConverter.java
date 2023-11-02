package com.example.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.dto.OrderDetailDTO;
import com.example.entity.OrderDetails;

@Component
public class OrderDetailsConverter {
	
	public OrderDetailDTO entityToDto(OrderDetails details)
	{
		OrderDetailDTO detailDTO=new OrderDetailDTO();
		
		detailDTO.setOrderNumber(details.getOrderNumber());
		detailDTO.setPriceEach(details.getPriceEach());
		detailDTO.setQuantityOrdered(details.getQuantityOrdered());
		detailDTO.setProductCode(details.getProductCode());
		return detailDTO;
	}

	public List<OrderDetailDTO> entityToDto(List<OrderDetails> orderDetails) {
        return orderDetails.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }
	
}

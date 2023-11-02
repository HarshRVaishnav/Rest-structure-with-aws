package com.example.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.OrderDetails;
import com.example.entity.Product;
import com.example.repository.IOrderDetailsRepo;
import com.example.repository.IOrderRepo;
import com.example.service.ICustomerService;
import com.example.service.IOrderDetailService;
import com.example.service.IOrderService;
import com.example.service.IProductService;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderRepo orderRepo;

	@Autowired
	private IOrderDetailsRepo detailsRepo;


	@Autowired
	private IProductService iProductService;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ICustomerService customerService;

	@Autowired
	private IOrderDetailService detailService;

	@Override
	@Transactional
	public Order createOrder(Order order) {
		order.setComments(order.getComments());
		orderRepo.save(order);
		List<OrderDetails> orderDetailsDtoList = order.getOrderDetails();

		List<Integer> pid = orderDetailsDtoList.stream().map(OrderDetails::getProductCode).collect(Collectors.toList());

		List<Product> products = iProductService.getProductList(pid);

		Map<Integer, Product> productMap = products.stream()
				.collect(Collectors.toMap(Product::getProductCode, product -> product));
		List<OrderDetails> orderDetailsList = new ArrayList<>();
		for (OrderDetails orderDetailsDto : orderDetailsDtoList) {
			Product product = productMap.get(orderDetailsDto.getProductCode());

			if (product.getQuantityInStock() < orderDetailsDto.getQuantityOrdered()) {
				throw new IllegalArgumentException("Product is out of stock");
			}
			iProductService.updateProductQuantityInStock(product.getProductCode(),orderDetailsDto.getQuantityOrdered());
			OrderDetails orderDetails = modelMapper.map(orderDetailsDto, OrderDetails.class);
			orderDetails.setProduct(product);
			orderDetails.setQuantityOrdered(orderDetailsDto.getQuantityOrdered());
			orderDetails.setPriceEach(product.getPrice());
			orderDetails.setOrderNumber(order.getOrderNumber());
			orderDetailsList.add(orderDetails);
		}
		detailService.saveAll(orderDetailsList);
		//orderRepo.save(order);
		return order;
	}

	@Override
	public Order createOrder2(Order order) {
		order.setComments(order.getComments());
		Customer customer = customerService.getCustomerById(order.getCustomerNumber());
		order.setCustomerNumber(customer.getCustomerNumber());
		order.setCustomer(customer);
		orderRepo.save(order);
		List<OrderDetails> orderDetailsDtoList = order.getOrderDetails();

		List<Integer> pid = orderDetailsDtoList.stream().map(OrderDetails::getProductCode).collect(Collectors.toList());

		List<Product> products = iProductService.getProductList(pid);

		Map<Integer, Product> productMap = products.stream()
				.collect(Collectors.toMap(Product::getProductCode, product -> product));

		List<OrderDetails> orderDetailsList = orderDetailsDtoList.stream().map(orderDetailsDto -> {
			Product product = productMap.get(orderDetailsDto.getProductCode());

			if (product.getQuantityInStock() < orderDetailsDto.getQuantityOrdered()) {
				throw new IllegalArgumentException("Product is out of stock");
			}
			iProductService.updateProductQuantityInStock(product.getProductCode(),
					orderDetailsDto.getQuantityOrdered());
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setOrderNumber(order.getOrderNumber());
			orderDetails.setQuantityOrdered(orderDetailsDto.getQuantityOrdered());
			orderDetails.setPriceEach(product.getPrice());
			orderDetails.setPriceEach(product.getPrice());
			return orderDetails;
		}).collect(Collectors.toList());

		// Save order
		detailsRepo.saveAll(orderDetailsList);
		return order;
	}
	
	
	@Override
    @Transactional
    public Order createOrder3(Order order) {
        order.setComments(order.getComments());
        
        
        orderRepo.save(order);
        List<OrderDetails> orderDetailsDtoList = order.getOrderDetails();

        List<Integer> pid = orderDetailsDtoList.stream().map(OrderDetails::getProductCode).collect(Collectors.toList());

        List<Product> products = iProductService.getProductList(pid);

        Map<Integer, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductCode, product -> product));

        List<OrderDetails> orderDetailsList = orderDetailsDtoList.stream().map(orderDetailsDto -> {
            Product product = productMap.get(orderDetailsDto.getProductCode());

            if (product.getQuantityInStock() < orderDetailsDto.getQuantityOrdered()) {
                throw new IllegalArgumentException("Product is out of stock");
            }
            product.setQuantityInStock(product.getQuantityInStock() - orderDetailsDto.getQuantityOrdered());
            OrderDetails orderDetails = modelMapper.map(orderDetailsDto, OrderDetails.class);
            orderDetails.setOrderNumber(order.getOrderNumber());
            // orderDetails.setOrder(order);
            // orderDetails.setProduct(product);
            orderDetails.setProductCode(product.getProductCode());
            orderDetails.setQuantityOrdered(orderDetailsDto.getQuantityOrdered());
            orderDetails.setPriceEach(product.getPrice());
            return orderDetails;
        }).collect(Collectors.toList());

        // detailsRepo.saveAll(orderDetailsList);
        detailService.saveAll(orderDetailsList);

        return order;
    }

	@Override
	public List<Order> getAllOrder() {
		return orderRepo.findAll();
	}

	@Override
	public Order getOrderByID(Integer orderNumber) {
		return orderRepo.findById(orderNumber).orElseThrow();
	}

	@Override
	public Order findOrder(Integer orderNumber) {
		return orderRepo.findById(orderNumber).orElseThrow();
	}
}

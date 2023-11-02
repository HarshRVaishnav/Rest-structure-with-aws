package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entity.Customer;

public interface ICustomerService {

	public List<Customer> getCustomerList();

	

	
	public Customer registerCustomer(Customer customerNumber);

	public Customer updateCustomerDetail(Integer customerNumber, Customer customer);

	public Customer getCustomerById(Integer customerNumber);

	public String deleteCustomer(Integer customerNumber);

	
	
	

}

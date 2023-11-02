package com.example.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Customer;
import com.example.exception.CustomerNotFoundException;
import com.example.repository.ICustomerRepo;
import com.example.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired

	private ICustomerRepo iCustomerRepo;

	@Override
	public List<Customer> getCustomerList() {
		return iCustomerRepo.findAll();
	}

	@Override
	public Customer updateCustomerDetail(Integer customerNumber, Customer c) {

		Optional<Customer> customer1 = iCustomerRepo.findById(customerNumber);

		if (customer1.isPresent()) {
			Customer customer = customer1.get();
			customer.setCustomerFirstName(c.getCustomerFirstName());
			customer.setCustomerLastName(c.getCustomerLastName());
			customer.setAddressLine1(c.getAddressLine1());
			customer.setAddressLine2(c.getAddressLine2());
			customer.setCity(c.getCity());
			customer.setCountry(c.getCountry());
			customer.setPostalCode(c.getPostalCode());
			customer.setState(c.getState());
			customer.setPhone(c.getPhone());

			return iCustomerRepo.save(customer);

		} else {

			throw new CustomerNotFoundException("Id does Not Exist");
		}

	}

	@Override
	public Customer getCustomerById(Integer customerNumber) {


		return iCustomerRepo.findById(customerNumber).orElseThrow(() -> new CustomerNotFoundException("Customer cannot be created: " + customerNumber));
		
	}

	@Override
	public Customer registerCustomer(Customer customerNumber) {

		return iCustomerRepo.save(customerNumber);
	}

	@Override
	public String deleteCustomer(Integer customerNumber) {
		try {
		if (customerNumber != null) {
	            iCustomerRepo.deleteById(customerNumber);
	          
		}
		  return "One Customer deleted " + customerNumber;
	        } catch (RuntimeException e) 
			
			{
	            throw new CustomerNotFoundException("Id does Not Exist");
	        }
		
	}	
}

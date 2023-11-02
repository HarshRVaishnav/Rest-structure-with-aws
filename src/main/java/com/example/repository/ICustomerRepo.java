package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Customer;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Integer> {

	Customer findBycustomerFirstName(String customerFirstName);

	Customer findBycustomerLastName(String customerLastName);
}

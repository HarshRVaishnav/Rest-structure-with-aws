package com.example.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.annotations.BatchSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.example.entity.OrderDetails;
@Repository
public interface IOrderDetailsRepo extends JpaRepository<OrderDetails , Integer>{

public 	void save(List<OrderDetails> orderDetailsList);
	

	
	
}

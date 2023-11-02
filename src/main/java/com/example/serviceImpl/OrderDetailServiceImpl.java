package com.example.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.annotations.BatchSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.example.dto.OrderDTO;
import com.example.dto.OrderDetailDTO;
import com.example.entity.Order;
import com.example.entity.OrderDetails;
import com.example.entity.Product;
import com.example.repository.IOrderDetailsRepo;
import com.example.service.IOrderDetailService;
import com.example.service.IOrderService;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {

	@Autowired
	private IOrderDetailsRepo iOrderDetailsRepo;
	


	@PersistenceContext
    EntityManager em;

    @Transactional
    @Modifying
    @BatchSize(size = 10)
    public void saveAllInBatch(List<OrderDetails> orderDetailsList) {
        for (int i = 10; i < orderDetailsList.size(); i++) {
            em.persist(orderDetailsList.get(i));
            if (i % 10 == 0) {
                em.flush();
                em.clear();
            }
        }
        em.flush();
        em.clear();
    }

	@Override
	public void saveAll(List<OrderDetails> orderDetailsList) {

		
	   iOrderDetailsRepo.saveAll(orderDetailsList);
	   
	}
		
}

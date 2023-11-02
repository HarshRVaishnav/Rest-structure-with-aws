package com.example.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderNumber;

	// @NotNull(message = "Order date cannot be null")
	private LocalDate orderDate;

	private LocalDate shippedDate;

	@NotNull(message = "Status cannot be null")
	private Status status;

	@Size(max = 500, message = "Comments cannot be more than 500 characters")
	private String comments;

	private Integer customerNumber;

	//@JsonIgnore
	@ManyToOne(cascade ={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.DETACH})
	@JoinColumn(name = "fk_customerNumber")
	@JsonBackReference
	private Customer customer;

	
	@OneToMany(mappedBy="order", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
	private List<OrderDetails> orderDetails;
//
//	  @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
//	  private List<Order> orders ;

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	/*
	 * public void addOrderDetails(Integer orderNumber,Integer productCode,Integer
	 * quantityOrdered,Double priceEach) { OrderDetails newOrderDetails = new
	 * OrderDetails(); newOrderDetails.setOrderNumber(orderNumber);
	 * newOrderDetails.setProductCode(productCode);
	 * newOrderDetails.setQuantityOrdered(quantityOrdered);
	 * newOrderDetails.setPriceEach(priceEach); newOrderDetails.setOrder(this); if
	 * (orderDetails == null) { orderDetails = new ArrayList<OrderDetails>(); }
	 * orderDetails.add(newOrderDetails); }
	 */

}

package com.example.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.customValidation.PostalCode;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_tbl")
  
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerNumber")
	private Integer customerNumber;

	@Size(min = 3, max = 15, message = "Customer First Name Must be between 3 to 15")
	@NotBlank(message = "First name cannot be blank")
	@Pattern(regexp = "^[A-Za-z]*$", message = "Invalid Input")
	private String customerFirstName;

	@Size(min = 3, max = 15, message = "Customer Last Name Must be between 3 to 15")
	@NotBlank(message = "Last name cannot be blank")
	@Pattern(regexp = "^[A-Za-z]*$", message = "Invalid Input")
	private String customerLastName;

	@Size(min = 10, max = 10, message = "Mobile Number Must Be 10 Digit")
	@Pattern(regexp = "^[0-9]{10}", message = "Invalid Mobile Number")
	private String phone;

	@NotBlank(message = "Address line 1 cannot be blank")
	private String addressLine1;

	@Size(max = 50, message = "Address line 2 cannot be more than 50 characters")
	private String addressLine2;

	@NotBlank(message = "City cannot be blank")
	@Pattern(regexp = "^[A-Z a-z]*$", message = "Invalid Input")
	private String city;

	@NotBlank(message = "State cannot be blank")
	@Pattern(regexp = "^[A-Z a-z]*$", message = "Invalid Input")
	private String state;

	@PostalCode
	private Integer postalCode;

	@NotBlank(message = "Country cannot be blank")
	//@Pattern(regexp = "^[A-Z a-z]*$", message = "Invalid Input")
	private String country;

	
	  @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
	  private List<Order> orders ;
	 
}

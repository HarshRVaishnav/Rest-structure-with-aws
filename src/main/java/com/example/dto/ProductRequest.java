package com.example.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductRequest {
	@NotBlank(message = "Product name cannot be blank")
    private String productName;

    private String productDescription;

    @Min(value = 0, message = "Quantity in stock must be at least 0")
    private Integer quantityInStock;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "100.00", message = "Price must be at least 100.00")
    private Double price;
	

}

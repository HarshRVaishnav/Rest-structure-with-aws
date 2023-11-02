package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Product;
import com.example.entity.ProductImage;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepo extends JpaRepository<Product, Integer> {

	// public List<Product> findByProductName(String productName);

	public List<Product> findAllByOrderByProductNameAsc();

	// public List<Product> findAllByOrderByProductNameDesc();

	public List<Product> findByProductNameContainsAllIgnoreCase(String name);

	public List<Product> findByProductCodeIn(List<Integer> collect);

	@Query("SELECT p FROM Product p WHERE p.productCode IN :productCodes")
	List<Product> findByProductCodes(@Param("productCodes") List<Integer> productCodes);

	List<Product> findByProductCodeInAndQuantityInStockGreaterThan(List<Integer> productCodes, int quantityInStock);

	public void save(ProductImage productImage);

	public void deleteFilesByProductName(String productName);
}

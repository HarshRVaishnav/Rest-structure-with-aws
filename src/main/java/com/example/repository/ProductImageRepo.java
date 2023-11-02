package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.ProductImage;

public interface ProductImageRepo extends JpaRepository<ProductImage, Integer>{

	Optional<ProductImage> findByProductCode(Integer productCode);

}

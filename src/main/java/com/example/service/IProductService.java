package com.example.service;


import com.example.entity.Product;
import com.example.entity.ProductImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

 public interface IProductService {

		public List<Product> createProductList(List<Product> product);

	    public Product updateProduct(Integer productCode, Product product);

	    public Product getOneProduct(Integer productCode);

	    public List<Product> getAllProducts();

	    public Product createProduct(Product product) ;

	    public String deleteProductById(Integer productCode);

	    public String deleteAllProducts();

	    public void createProductsList();
	    
	    public Product getProductById(Integer productCode);
	    
	    public Product partialUpdate(Integer productCode, Map<String, Object> updates);

	    public List<Product> getAllSortedByProduct();

	    public List<Product> searchByProductName(String name);
	   
	    public Product incrementDecrementProductPrice(Integer productCode, String value, Double amount);

	    public Product incrementDecrementProductQuantityInStock(Integer productCode, String value, Integer quantity);

	    public Product updateProductQuantityInStock(Integer productCode,  Integer quantity);
	    
	    public List<Product> getProductList(List<Integer> productCode);

		public void updateProducts(List<Product> products);

		public void updateProductQuantityInStock(List<Product> products);

		public Product uploadImage(Product product, MultipartFile file) throws IOException;
	
		public Product uploadImages(Product product) throws IOException ;
		
		public String deleteFilesByProductName(String productName);

		public String deleteFilesByProductCode(Integer productCode);
		
}
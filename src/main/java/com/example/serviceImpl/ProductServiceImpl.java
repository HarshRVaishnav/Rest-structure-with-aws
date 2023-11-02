package com.example.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.example.entity.Product;
import com.example.entity.ProductImage;
import com.example.exception.ProductNotFoundException;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.IProductRepo;
import com.example.repository.ProductImageRepo;
import com.example.service.IProductService;
import com.github.javafaker.Faker;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepo iProductRepo;

	@Autowired
	private ProductImageRepo imageRepo;

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${bucketName}")
	private String bucketname;

	@Override
	public Product uploadImage(Product product, MultipartFile file) throws IOException {
		Integer productCode = product.getProductCode();
		Product product2 = getOneProduct(productCode);
		if (!file.getContentType().equals("application/pdf")) {
			throw new IllegalArgumentException("Only PDF files are allowed.");
		}

		String fileName = product2.getProductName() + ".pdf";

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.addUserMetadata("productCode", String.valueOf(productCode));
		metadata.setContentLength(file.getSize());
		metadata.addUserMetadata("productName", product2.getProductName());
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketname, fileName, file.getInputStream(), metadata);
		amazonS3.putObject(putObjectRequest);

		

		return iProductRepo.save(product);
	}

	
	@Override
	public Product uploadImages(Product product) throws IOException {
	    List<MultipartFile> files=product.getFiles();
	    Integer productCode = product.getProductCode();
	    Product product2 = getOneProduct(productCode);

	    int i=1;
	    for (MultipartFile file : files) {
	        String fileName = product2.getProductName() + "/" + product2.getProductName() + "_" + i;
	        i++;
	        ObjectMetadata metadata = new ObjectMetadata();
	        metadata.addUserMetadata("productCode", String.valueOf(productCode));
	        metadata.addUserMetadata("productName", product2.getProductName());
	        metadata.setContentLength(file.getSize());

	        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketname, fileName, file.getInputStream(),
	                metadata);
	        amazonS3.putObject(putObjectRequest);

	    }
	    return iProductRepo.save(product);
	}

	@Override
	public String deleteFilesByProductName(String productName) {
		ListObjectsV2Request request = new ListObjectsV2Request().withBucketName(bucketname);
		ListObjectsV2Result result;
		  int count = 0;
		result = amazonS3.listObjectsV2(request);

		for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
			String key = objectSummary.getKey();
			ObjectMetadata metadata = amazonS3.getObjectMetadata(bucketname, key);
			Map<String, String> userMetadata = metadata.getUserMetadata();

			if (userMetadata != null && userMetadata.containsKey("productName")&& userMetadata.get("productName").equals(productName)) {
				amazonS3.deleteObject(bucketname, key);
				 count++;
			}  }
        if (count == 0) {
			throw new ProductNotFoundException(productName + " not found");
		}
		
        return "Deleted  product image(s) successfully.";
	}

	@Override
	public String deleteFilesByProductCode(Integer productCode) {
		
		 ListObjectsV2Request request = new ListObjectsV2Request().withBucketName(bucketname);
		    ListObjectsV2Result result;
		    int count = 0;
		        result = amazonS3.listObjectsV2(request);

		        for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {    
		            String key = objectSummary.getKey();
		            ObjectMetadata metadata = amazonS3.getObjectMetadata(bucketname, key);
		           Map<String, String> userMetadata = metadata.getUserMetadata();
		        
		          
		           if (userMetadata != null && userMetadata.containsKey("productCode") && userMetadata.get("productCode").equals(productCode.toString())) {
		                amazonS3.deleteObject(bucketname, key);
		           count++;
		        }
	
		        } 
		           if (count == 0) {
		   			throw new ProductNotFoundException(productCode + " not found");
		   		}
		   		
		           return "Deleted  product images successfully.";
		
	}

	@Override
	public Product createProduct(Product product) {
		try {

			MultipartFile file = product.getFile();
			if (!file.getContentType().equals("application/pdf")) {
				throw new IllegalArgumentException("Only PDF files are allowed.");
			}

			String fileName = product.getProductName() + ".pdf";
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(file.getSize());

			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketname, fileName, file.getInputStream(),metadata);
			amazonS3.putObject(putObjectRequest);

			ProductImage productImage = new ProductImage();
			productImage.setFileName(fileName);
			productImage.setFileSize(file.getSize());
			productImage.setFileType(file.getContentType());

		} catch (Exception e) {
			throw new IllegalArgumentException("Product Not Created");

		}
		return iProductRepo.save(product);
	}
	

	@Override
	public List<Product> createProductList(List<Product> product) {
		return iProductRepo.saveAll(product);
	}

	@Override
	public Product updateProduct(Integer productCode, Product product) {
		Product existingProduct = getOneProduct(productCode);
		if (existingProduct != null) {
			existingProduct.setProductName(product.getProductName());
			existingProduct.setProductDescription(product.getProductDescription());
			existingProduct.setQuantityInStock(product.getQuantityInStock());
			existingProduct.setPrice(product.getPrice());
			return iProductRepo.save(existingProduct);
		} else {
			throw new ResourceNotFoundException("product cannot be created ");
		}
	}

	@Override
	public Product partialUpdate(Integer productCode, Map<String, Object> updates) {
		Product product = iProductRepo.findById(productCode)
				.orElseThrow(() -> new ResourceNotFoundException("Product cannot be created: " + productCode));
		ReflectionUtils.doWithFields(Product.class, field -> {
			String fieldName = field.getName();
			if (updates.containsKey(fieldName)) {
				field.setAccessible(true);
				Object newValue = updates.get(fieldName);
				field.set(product, newValue);
			}
		});
		return iProductRepo.save(product);
	}

	@Override
	public String deleteProductById(Integer productCode) {
		if (productCode != null) {
			iProductRepo.deleteById(productCode);
			return "One product deleted " + productCode;
		} else {
			throw new IllegalArgumentException("id not exist");
		}
	}

	@Override
	public String deleteAllProducts() {
		iProductRepo.deleteAll();
		return "All products are deleted ";
	}

	@Override
	public Product getOneProduct(Integer productCode) {
		return iProductRepo.findById(productCode)
				.orElseThrow(() -> new ResourceNotFoundException("product not exist with " + productCode));

	}

	@Override
	public List<Product> getAllProducts() {
		return iProductRepo.findAll();
	}

	@Override
	public List<Product> getAllSortedByProduct() {
		return iProductRepo.findAllByOrderByProductNameAsc();
	}

	@Override
	public List<Product> searchByProductName(String name) {
		List<Product> products = null;
		if (name != null && (name.trim().length() > 0)) {
			return iProductRepo.findByProductNameContainsAllIgnoreCase(name);
		} else {
			return iProductRepo.findAllByOrderByProductNameAsc(); // return getAllSortedByProduct();
		}
	}

	@Override
	public Product incrementDecrementProductQuantityInStock(Integer productCode, String value, Integer quantity) {
		Product existingProduct = getOneProduct(productCode);
		Integer originalQuantityInStock = existingProduct.getQuantityInStock();
		if ((existingProduct != null) && (value.equalsIgnoreCase("increment")) && (1 <= quantity)) {
			Integer increaseProductPriceQuantity = existingProduct.getQuantityInStock() + quantity;
			// Integer newQuantity = Quantity;
			// //existingProduct.setQuantityInStock(newQuantity); //to set new Quantity
			existingProduct.setQuantityInStock(increaseProductPriceQuantity);
			return iProductRepo.save(existingProduct);
		}
		if ((existingProduct != null) && (value.equalsIgnoreCase("decrement"))
				&& (originalQuantityInStock >= quantity)) {
			Integer decreaseProductPriceQuantity = existingProduct.getQuantityInStock() - quantity;
			// Integer newQuantity = Quantity;
			// //existingProduct.setQuantityInStock(newQuantity); //to set new Quantity
			existingProduct.setQuantityInStock(decreaseProductPriceQuantity);
			return iProductRepo.save(existingProduct);
		} else {
			throw new ResourceNotFoundException(
					"for increment quantity cannot be less than zero & for decrement quantity "
							+ "cannot be greater than original quantity");
		}
	}

	@Override
	public Product incrementDecrementProductPrice(Integer productCode, String value, Double amount) {
		Product existingProduct = getOneProduct(productCode);
		Double originalPrice = existingProduct.getPrice();
		if ((existingProduct != null) && (value.equalsIgnoreCase("increment")) && (1 <= amount)) {
			Double increaseProductPriceAmount = existingProduct.getPrice() + amount;
			// Double newPrice = amount; //existingProduct.setPrice(newPrice); //to set new
			// price
			existingProduct.setPrice(increaseProductPriceAmount);
			return iProductRepo.save(existingProduct);
		}
		if ((existingProduct != null) && (value.equalsIgnoreCase("decrement")) && (originalPrice >= amount)) {
			Double decreaseProductPriceAmount = existingProduct.getPrice() - amount;
			// Double newPrice = amount; //existingProduct.setPrice(newPrice); //to set new
			// price
			existingProduct.setPrice(decreaseProductPriceAmount);
			return iProductRepo.save(existingProduct);
		} else {
			throw new ResourceNotFoundException(
					"for increment amount cannot be less than zero & for decrement amount cannot be "
							+ "greater than original price");
		}
	}

	@Override
	public Product updateProductQuantityInStock(Integer productCode, Integer quantity) {
		Product existingProduct = getOneProduct(productCode);
		Integer originalQuantityInStock = existingProduct.getQuantityInStock();
		if ((existingProduct.getQuantityInStock() >= quantity) && (existingProduct != null) && (1 <= quantity)) {
			Integer updateProductQuantity = existingProduct.getQuantityInStock() - quantity;
			existingProduct.setQuantityInStock(updateProductQuantity);
			return iProductRepo.save(existingProduct);
		} else {
			throw new IllegalArgumentException(
					"for increment quantity cannot be less than zero & for decrement quantity "
							+ "cannot be greater than original quantity");
		}
	}

	public List<Product> updateProductList(List<Product> productCode) {

		return iProductRepo.saveAll(productCode);
	}

	@Override
	public Product getProductById(Integer productCode) {
		return iProductRepo.findById(productCode)
				.orElseThrow(() -> new ProductNotFoundException("product not exist with : " + productCode));
	}

	@Override
	public void createProductsList() {
		List<Product> products = new ArrayList<>();
		for (int i = 1; i < 10000; i++) {
			Faker faker = new Faker(new Locale("en-IND"));
			Product product = new Product();
			product.setProductName(faker.commerce().productName());
			product.setProductDescription(faker.lorem().sentence());
			product.setQuantityInStock(faker.number().numberBetween(1, 100));
			product.setPrice(faker.number().randomDouble(2, 102, 10000));
			products.add(product);
		}
		iProductRepo.saveAll(products);
	}

	@Override
	public List<Product> getProductList(List<Integer> productCode) {
		return iProductRepo.findByProductCodeIn(productCode);
	}

	@Override
	public void updateProducts(List<Product> products) {
		iProductRepo.saveAll(products);

	}

	@Override
	public void updateProductQuantityInStock(List<Product> products) {

		iProductRepo.saveAll(products);
	}

}
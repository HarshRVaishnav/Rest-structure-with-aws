
  package com.example.runner;
  
  import com.example.entity.Product; import
  com.example.repository.IProductRepo; import com.github.javafaker.Faker;
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.boot.CommandLineRunner; import
  org.springframework.core.annotation.Order; import
  org.springframework.stereotype.Component;
  
  import java.util.Locale;
  
  @Component
  
  @Order(5) public class ProductRunner implements CommandLineRunner {
  
  @Autowired private IProductRepo productRepo;
  
  @Override public void run(String... args) throws Exception {
  
  
  if (productRepo.count() == 0) { for (int i = 1; i < 100; i++) { Faker faker =
  new Faker(new Locale("en-IND")); Product product = new Product();
  product.setProductName(faker.commerce().productName());
  product.setProductDescription(faker.lorem().sentence());
  product.setQuantityInStock(faker.number().numberBetween(1, 100));
  product.setPrice(faker.number().randomDouble(2, 1000, 10000));
  productRepo.save(product); } }
  
  } }
 
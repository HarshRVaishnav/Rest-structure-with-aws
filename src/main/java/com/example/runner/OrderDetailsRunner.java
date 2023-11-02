
  package com.example.runner;
  
  import com.example.entity.Order; import com.example.entity.OrderDetails;
  import com.example.entity.Product; import
  com.example.repository.IOrderDetailsRepo; import com.github.javafaker.Faker;
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.boot.CommandLineRunner; import
  org.springframework.stereotype.Component;
  
  import java.util.Locale;
  
  @Component
  
  @org.springframework.core.annotation.Order(20) public class
  OrderDetailsRunner implements CommandLineRunner {
  
  @Autowired private IOrderDetailsRepo orderDetailsRepo;
  
  @Override public void run(String... args) throws Exception {
  
  
  if (orderDetailsRepo.count() == 0) { for (int i = 1; i < 100; i++) { Faker
  faker = new Faker(new Locale("en-IND")); Order order = new Order();
  OrderDetails orderDetails = new OrderDetails();
  //orderDetails.setOrderNumber(order.getOrderNumber());
  //orderDetails.setQuantityOrdered(faker.number().numberBetween(1, 9));
 // Product product = new Product();
  //orderDetails.setProductCode(product.getProductCode()); //
  //orderDetails.setProductCode(faker.number().randomDigit()); //
  orderDetails.setPriceEach(faker.number().randomDouble(2,100,1000));
  orderDetailsRepo.save(orderDetails); } }
  
  } }
 
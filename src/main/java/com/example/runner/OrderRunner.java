
  package com.example.runner;
  
  import com.example.entity.Customer; import com.example.entity.Order; import
  com.example.entity.Status; import com.example.repository.IOrderRepo; import
  com.github.javafaker.Faker; import
  org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.boot.CommandLineRunner; import
  org.springframework.stereotype.Component;
  
  import java.util.Locale; import java.util.concurrent.TimeUnit;
  
  @org.springframework.core.annotation.Order(10)
  
  @Component public class OrderRunner implements CommandLineRunner {
  
  @Autowired private IOrderRepo orderRepo;
  
  @Override public void run(String... args) throws Exception {
  
  
  if (orderRepo.count() == 0) {
  
  for (int i = 1; i < 100; i++) { Faker faker = new Faker(new
  Locale("en-IND")); Order order = new Order();
  //order.setOrderDate(faker.date().future(1, TimeUnit.DAYS));
  order.setComments(faker.lorem().sentence()); Status curentstatus =
  Status.ORDERED; order.setStatus(curentstatus); //
  //order.setShippedDate(faker.date().future(2, TimeUnit.DAYS));
  
  orderRepo.save(order); } }
  
  } }
 
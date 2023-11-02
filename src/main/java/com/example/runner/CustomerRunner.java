
  package com.example.runner;
  
  import com.example.entity.Customer; import
  com.example.repository.ICustomerRepo; import com.github.javafaker.Faker;
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.boot.CommandLineRunner; import
  org.springframework.core.annotation.Order; import
  org.springframework.stereotype.Component;
  
  import java.util.Locale;
  
  @Component
  
  @Order(2) public class CustomerRunner implements CommandLineRunner {
  
  @Autowired private ICustomerRepo customerRepo;
  
  @Override public void run(String... args) throws Exception {
  
  if (customerRepo.count() == 0) { for (int i = 1; i < 100; i++) { Faker faker
  = new Faker(new Locale("en-IND")); Customer customer = new Customer();
  
  customer.setCustomerFirstName(faker.name().firstName());
  customer.setCustomerLastName(faker.name().lastName()); // //
 // customer.setPhone(faker.number().digit());
  customer.setAddressLine1(faker.address().streetAddress());
  customer.setAddressLine2(faker.address().secondaryAddress());
  customer.setCity(faker.address().city());
  customer.setState(faker.address().state());
  customer.setPostalCode(faker.number().numberBetween(100000, 999999));
  customer.setCountry(faker.address().country());
  
  customerRepo.save(customer); } }
  
  }
  
  }
 
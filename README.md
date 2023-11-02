Rest-Api Structure Development
Simple Rest-api application with SpringBoot + Docker

Prerequisites to run application
java 11, database: mysql,psql or Docker

Steps to run sample
    # Run SpringBoot application
    mvn spring-boot:run 
    
    # Start docker containers 
    docker-compose up -d
Application contains 3 modules:
1) customer
2) product
3) order
    1) orderDetails

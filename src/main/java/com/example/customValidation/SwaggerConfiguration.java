package com.example.customValidation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dk.brics.automaton.RegExp;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
@Configuration

public class SwaggerConfiguration {
//    @Bean
//    public Docket api() { 
//    	return new Docket(DocumentationType.SWAGGER_2).groupName("Document").select()
//   	         .apis(RequestHandlerSelectors.basePackage("com.example")).build();    }
//    
    
   
    
    
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.tutorialspoint.swaggerdemo")).build();

		//
	}
}

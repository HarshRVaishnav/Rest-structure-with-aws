package com.example.dto;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ErrorDetails {

	 private boolean success;
	    private String message;
	    private String error_code;
	    //private List<String> error_codes;
	    private static HttpStatus httpStatus;

	    public ErrorDetails(String message, List<String> errors) {
	        this.success = false;
	        this.message = message;
	    }

	    public ErrorDetails(String message, String error_code,HttpStatus httpStatus) {
	        this.success = false;
	        this.message = message;
	        this.error_code = error_code;
	    }


	    public ErrorDetails(String message, String error_code) {
	        this.success = false;
	        this.message = message;
	        this.error_code = error_code;
	        //error_codes = Arrays.asList(error_code);
	    }

	    /*public ErrorDetails(HttpStatus status, String message, List<String> error_codes) {
	        super();
	        this.message = message;
	        this.error_codes = error_codes;
	    }*/

	    public static ResponseEntity<ErrorDetails> error(String message, String error_code,HttpStatus httpStatus) {
	        return new ResponseEntity<>(new ErrorDetails(message,error_code,httpStatus), httpStatus);
	    }
	       public static ResponseEntity<ErrorDetails> error(String message, String error_code) {
	        return new ResponseEntity<>(new ErrorDetails(message,error_code), HttpStatus.BAD_REQUEST);
	    }

	    public void addResult(String field, String defaultMessage) {
	    }
	}



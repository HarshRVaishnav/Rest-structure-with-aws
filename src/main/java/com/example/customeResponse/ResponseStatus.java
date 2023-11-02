package com.example.customeResponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ResponseStatus {
	
	 public static final Map<String, HttpStatus> STATUS_CODE = new HashMap<String, HttpStatus>() {{
	        put("SUCCESS", HttpStatus.OK);
	        put("CREATED", HttpStatus.CREATED);
	        put("SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
	        put("FIELD_ERROR", HttpStatus.BAD_REQUEST);
	        put("VALUE_ERROR", HttpStatus.UNPROCESSABLE_ENTITY);
	        put("KEY_ERROR", HttpStatus.UNPROCESSABLE_ENTITY);
	        put("EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR);
	        put("RUNTIME_EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR);
	        put("METHOD_ARGUMENT_NOT_VALID_ERROR", HttpStatus.BAD_REQUEST);
	        put("SERVER_REQUEST_TIMEOUT", HttpStatus.INTERNAL_SERVER_ERROR);
	        put("ACCESS_FORBIDDEN_ERROR", HttpStatus.FORBIDDEN);
	        put("SERVICE_UNAVAILABLE", HttpStatus.SERVICE_UNAVAILABLE);
	        put("VALIDATION_ERROR", HttpStatus.BAD_REQUEST);
	        put("DATA_NOT_CREATED", HttpStatus.INTERNAL_SERVER_ERROR);
	        put("DATA_NOT_FOUND", HttpStatus.NOT_FOUND);
	        put("REQUIRED_FIELD", HttpStatus.UNPROCESSABLE_ENTITY);
	        put("UPDATE_FAILED", HttpStatus.NOT_MODIFIED);
	        put("DELETE_FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
	        put("QUERY_EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR);
	        put("request_failed", HttpStatus.BAD_REQUEST);
	        put("MODEL_NOT_FOUND", HttpStatus.NOT_FOUND);
	        put("JSON_PARSE_ERROR", HttpStatus.UNPROCESSABLE_ENTITY);
	        put("SERIALIZER_ERROR", HttpStatus.UNPROCESSABLE_ENTITY);
	        put("PAGE_NOT_FOUND", HttpStatus.NOT_FOUND);
	        put("OUT_OF_STOCK", HttpStatus.BAD_REQUEST);
	        put("PRODUCT_NOT_FOUND_ERROR", HttpStatus.NOT_FOUND);
	        put("CUSTOMER_NOT_FOUND_ERROR", HttpStatus.NOT_FOUND);
	    }};
	
	

}

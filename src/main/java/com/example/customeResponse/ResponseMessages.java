package com.example.customeResponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ResponseMessages {
	
	
    public static final Map<String, String> MESSAGE = new HashMap<String, String>() {{

        put("SERVER_ERROR", "Something Went Wrong");
        put("FIELD_ERROR", "Field Error");
        put("VALUE_ERROR", "Invalid Request");
        put("KEY_ERROR", "key Not Found");
        put("EXCEPTION", "Something Went Wrong");
        put("RUNTIME_EXCEPTION", "Something Went Wrong");
        put("METHOD_ARGUMENT_NOT_VALID_ERROR", "method argument not valid");
        put("SERVER_REQUEST_TIMEOUT", "request timeout");
        put("ACCESS_FORBIDDEN_ERROR", "Access forbidden");
        put("SERVICE_UNAVAILABLE", "service unavailable");
        put("VALIDATION_ERROR", "validation error");
        put("SUCCESS", "Success Response");
        put("CREATED", "Data Created");
        put("DATA_NOT_CREATED", "Data Not Created");
        put("DATA_NOT_FOUND", "Data Not Found");
        put("REQUIRED_FIELD", "Required Field");
        put("UPDATE_FAILED", "Update Failed");
        put("DELETE_FAILED", "Delete Failed");
        put("QUERY_EXCEPTION", "Database Or Query Exception");
        put("REQUEST_FAILED", "Json Request Failed");
        put("MODEL_NOT_FOUND", "Data Not Found");
        put("JSON_PARSE_ERROR", "Json Parse Error");
        put("SERIALIZER_ERROR", "Serializer Error");
        put("PAGE_NOT_FOUND", "Page Not Found");
        put("OUT_OF_STOCK", "Product is Out of Stock");
        put("PRODUCT_NOT_FOUND_ERROR", "Product is Not Found");
        put("CUSTOMER_NOT_FOUND_ERROR", "Customer  Not Found");
    }};

}

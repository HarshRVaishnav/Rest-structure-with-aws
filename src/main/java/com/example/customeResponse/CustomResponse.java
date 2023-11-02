package com.example.customeResponse;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.customeResponse.ResponseStatus;


public class CustomResponse {
	  
	//private static HttpStatus httpStatus;

    public static ResponseEntity<Object> response(String code, Object data) {

        // Create the response data object
        Map<String, Object> responseData = new HashMap<String, Object>();
        responseData.put("success", true);

        // Handle error code
        String errorCode = ErrorCodes.RESPONSE_CODE.get(code);
        if (errorCode != null) {
            responseData.put("success", false);
            responseData.put("error_code", errorCode);
            
        }

        // Handle error message
        String errorMessage = ResponseMessages.MESSAGE.get(code);
        if (errorMessage != null) {
            //responseData.put("message", Collections.singletonList(errorMessage));
            responseData.put("message", errorMessage);
        }

        // Handle data
        if (data != null) {
            responseData.put("data", data);
        }
        HttpStatus httpStatus = ResponseStatus.STATUS_CODE.get(code);
        return new ResponseEntity<>(responseData,httpStatus);
    }

}

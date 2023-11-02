package com.example.customeResponse;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodes {
	
	public static final Map<String, String> RESPONSE_CODE = new HashMap<String, String>() {{

        put("DATA_NOT_CREATED", "RA_FW_ERR_480");
        put("DATA_NOT_FOUND", "RA_FW_ERR_481");
        put("REQUIRED_FIELD", "RA_FW_ERR_482");
        put("REQUEST_FAILED", "RA_FW_ERR_483");
        put("JSON_PARSE_ERROR", "RA_FW_ERR_484");
        put("SERIALIZER_ERROR", "RA_FW_ERR_485");
        put("FIELD_ERROR", "RA_FW_ERR_486");
        put("VALIDATION_ERROR", "RA_SYS_ERR_487");
        put("QUERY_EXCEPTION", "RA_FW_ERR_488");
        put("MODEL_NOT_FOUND", "RA_FW_ERR_489");
        put("PAGE_NOT_FOUND", "RA_FW_ERR_490");
        put("SERVER_ERROR", "RA_SYS_ERR_500");
        put("EXCEPTION", "RA_SYS_ERR_500");
        put("RUNTIME_EXCEPTION", "RA_SYS_ERR_500");
        put("VALUE_ERROR", "RA_SYS_ERR_502");
        put("KEY_ERROR", "RA_SYS_ERR_503");
        put("SERVER_REQUEST_TIMEOUT", "RA_SYS_ERR_504");
        put("ACCESS_FORBIDDEN_ERROR", "RA_SYS_ERR_505");
        put("SERVICE_UNAVAILABLE", "RA_SYS_ERR_506");
        put("ATTRIBUTE_ERROR", "RA_SYS_ERR_507");
        put("METHOD_ARGUMENT_NOT_VALID_ERROR", "RA_SYS_ERR_511");
        put("METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION", "RA_SYS_ERR_510");
        put("UPDATE_FAILED", "RA_CUST_ERR_602");
        put("DELETE_FAILED", "RA_CUST_ERR_603");
        put("OUT_OF_STOCK", "RA_CUST_ERR_605");       
    }};
}

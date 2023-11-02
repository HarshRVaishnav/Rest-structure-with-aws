package com.example.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SuccessDetails {

    private boolean success;
    private String message;
    private Object data;
    private static HttpStatus httpStatus;

    public SuccessDetails(String message, Object data, HttpStatus httpStatus) {
        this.success = true;
        this.message = message;
        this.data = data;
    }

    public SuccessDetails(String message, Object data) {
        this.success = true;
        this.message = message;
        this.data = data;
    }
    public static ResponseEntity<SuccessDetails> success(String message, Object data, HttpStatus httpStatus) {
        return new ResponseEntity<>(new SuccessDetails(message, data, httpStatus), httpStatus);
    }

    public static ResponseEntity<SuccessDetails> success(String message, Object data) {
        return new ResponseEntity<>(new SuccessDetails(message, data), HttpStatus.OK);
    }
}





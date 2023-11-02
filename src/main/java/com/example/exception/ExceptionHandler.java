package com.example.exception;

import com.example.dto.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException( NoHandlerFoundException noHandlerFoundException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "No handler found for " + noHandlerFoundException.getHttpMethod() + " " + noHandlerFoundException.getRequestURL();
        // ErrorDetails apiError = new ErrorDetails(ex.getLocalizedMessage(), error);
        ErrorDetails apiError = new ErrorDetails(error, "RA_SYS_ERR_404");
        log.error("handleNoHandlerFoundException : {} ",noHandlerFoundException.getMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException, WebRequest request) {
        String error = methodArgumentTypeMismatchException.getName() + " should be of type " + methodArgumentTypeMismatchException.getRequiredType().getName();
        //ErrorDetails apiError = new ErrorDetails(error,ex.getLocalizedMessage(),404);
        ErrorDetails apiError = new ErrorDetails(error, "RA_SYS_ERR_511");
        log.error("handleMethodArgumentTypeMismatch : {} ",methodArgumentTypeMismatchException.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException methodArgumentNotValidException,
             HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errorsMap = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach((error) -> {
            errorsMap.put(error.getField(), error.getDefaultMessage());
        });
        ErrorDetails errorDetails = new ErrorDetails(String.valueOf(errorsMap), "RA_SYS_ERR_400");
        log.error("handleMethodArgumentNotValid : {} ",methodArgumentNotValidException.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleProductNotFoundException(CustomerNotFoundException productNotFoundException) {
        ErrorDetails errorDetails = new ErrorDetails(productNotFoundException.getMessage(), "RA_SYS_ERR_500");
        log.error("handleProductNotFoundException : {} ",productNotFoundException.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        ErrorDetails errorResponse = new ErrorDetails(illegalArgumentException.getMessage(), "RA_SYS_ERR_500");
        log.error("handleIllegalArgumentException:{}", illegalArgumentException.getMessage());
        return new ResponseEntity<ErrorDetails>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorDetails> handleRuntimeException(RuntimeException runtimeException) {
        ErrorDetails errorDetails = new ErrorDetails(runtimeException.getMessage(), "RA_SYS_ERR_500");
        log.error("handleRuntimeException :{} ",runtimeException.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), "RA_SYS_ERR_500");
        log.error("handleException : {} ",exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.yogesh.electronicStore.exception;

import com.yogesh.electronicStore.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourcenotFoundException(ResourceNotFoundException ex)
    {
        String message = ex.getMessage();

        logger.info("Exception handler invoke");

        ApiResponse apiResponse = new ApiResponse(message, false);

        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodargsNotValidException(MethodArgumentNotValidException ex)
    {
        Map<String, String> resp = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error)->{

            String field = ((FieldError)error).getField();

            String defaultMessage = error.getDefaultMessage();

            resp.put(field, defaultMessage);
        });

        return new ResponseEntity<Map<String, String>>(resp,HttpStatus.BAD_REQUEST);
    }
}

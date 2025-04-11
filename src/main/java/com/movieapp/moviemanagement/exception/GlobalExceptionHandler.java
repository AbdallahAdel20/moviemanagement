package com.movieapp.moviemanagement.exception;


import com.movieapp.moviemanagement.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {

    // define logger
    Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleAllExceptions(Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                Collections.singletonList(ex.getMessage())
        );
        logger.info("Error details: " + ex.getMessage());
        ApiResponse<Object> response = new ApiResponse<>(
                false,
                errorDetails,
                "An error occurred"
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

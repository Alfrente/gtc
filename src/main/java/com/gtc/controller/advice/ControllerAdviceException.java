package com.gtc.controller.advice;

import com.gtc.exception.ExceptionGtc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdviceException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> HandleValidationExceptions(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(e -> {
            errors.put("ERROR", HttpStatus.BAD_REQUEST.getReasonPhrase());
            errors.put("MENSAJE", e.getDefaultMessage());
            errors.put("FECHA", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss a.m")));
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ExceptionGtc.class)
    public ResponseEntity<Map<String, String>> exception(ExceptionGtc gtc) {
        Map<String, String> error = new HashMap<>();

        error.put("ERROR", HttpStatus.BAD_REQUEST.getReasonPhrase());
        error.put("MENSAJE", gtc.getMessage());
        error.put("FECHA", gtc.getFecha().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss a.m")));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    

}

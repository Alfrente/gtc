package com.gtc.controller.advice;

import com.gtc.exception.ExceptionGtc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdviceException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> HandleValidationExceptions(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(e -> {
            errors.put("Mensaje", e.getDefaultMessage());
            errors.put("Campo", ((FieldError) e).getField());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ExceptionGtc.class)
    public ResponseEntity<Map<String, String>> exception(ExceptionGtc gtc) {
        Map<String, String> error = new HashMap<>();

        error.put("ERROR", gtc.getMessage());
        error.put("FECHA", gtc.getFecha().toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    

}

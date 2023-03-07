package com.gtc.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExceptionGtc extends RuntimeException {
   // Cree esta para ir mas rapido
    private LocalDateTime fecha;

    public ExceptionGtc(String message) {
        super(message);
        this.fecha=LocalDateTime.now();
    }
}

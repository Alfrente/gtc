package com.gtc.util;

import com.gtc.exception.ExceptionGtc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MetodosEstatico {

    private MetodosEstatico() {
    }

    public static void validarFecha(String fechaString) {
        try {
            LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            throw new ExceptionGtc("La fecha ingresada es incorrecta");
        }
    }

    public static Long longValue(String valor) {
        return Long.valueOf(valor);
    }

    public static Double doubleValue(String valor) {
        return Double.parseDouble(valor);
    }

    public static Integer integerValue(String valor) {
        return Integer.parseInt(valor);
    }
}

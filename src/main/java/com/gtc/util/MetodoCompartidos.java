package com.gtc.util;

import com.gtc.exception.ExceptionGtc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MetodoCompartidos {

    private MetodoCompartidos() {
    }

    public static void validarFecha(String fechaString) {
        try {
            LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            throw new ExceptionGtc("La fecha ingresada es incorrecta");
        }
    }

    public static Long validarId(String idString) {
        try {
            return stringALong(idString);
        } catch (NumberFormatException e) {
            throw new ExceptionGtc("El valor ingresado en el id es invalido");
        }
    }

    public static Long stringALong(String valor) {
            return Long.parseLong(valor);
    }

    public static Double stringADouble(String valor) {
            return Double.parseDouble(valor);
    }

    public static Integer stringAInteger(String valor) {
            return Integer.parseInt(valor);
    }
}

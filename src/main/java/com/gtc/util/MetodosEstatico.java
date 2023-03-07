package com.gtc.util;

import lombok.extern.slf4j.Slf4j;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class MetodosEstatico {

    private MetodosEstatico() {
    }

    public static boolean validarFecha(String fechaString) {
        try {
            var feachaArray = fechaString.split("-");
            int ano = Integer.parseInt(feachaArray[0]);
            int mes = Integer.parseInt(feachaArray[1]);
            int dia = Integer.parseInt(feachaArray[2]);
            //LocalDate.of(ano, mes, dia);
            LocalDate.parse(fechaString);
            return false;
        } catch (DateTimeException e) {
            log.error(e.getClass().getName());
            return true;
        }
    }
}

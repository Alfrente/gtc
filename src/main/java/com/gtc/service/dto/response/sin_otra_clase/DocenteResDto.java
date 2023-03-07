package com.gtc.service.dto.response.sin_otra_clase;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DocenteResDto {

    private Long numeroDocumento;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String asigDictadas;
    private String gradoEscolaridad;
    private String email;
    private Long fijo;
    private Long celular;
}

package com.gtc.service.dto.response.sin_otra_clase;

import lombok.Data;

import java.time.LocalDate;


@Data
public class EstudianteResDto {
    private Long numeroDocumento;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String email;
    private Long fijo;
    private Long celular;
}

package com.gtc.service.dto.response.model;

import java.time.LocalDate;

public record EstudianteResDto(Long numeroDocumento, String nombres, String apellidos,
                               LocalDate fechaNacimiento, String direccion, String email,
                               Long fijo, Long celular) {

}

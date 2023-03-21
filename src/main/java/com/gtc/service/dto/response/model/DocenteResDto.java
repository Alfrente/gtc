package com.gtc.service.dto.response.model;

import java.time.LocalDate;

public record DocenteResDto(Long numeroDocumento, String nombres, String apellidos,
                            LocalDate fechaNacimiento, String asigDictadas,
                            String gradoEscolaridad, String email, Long fijo, Long celular) {

}

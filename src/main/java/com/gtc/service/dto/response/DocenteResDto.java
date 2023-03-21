package com.gtc.service.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gtc.service.dto.response.model.DescripcionResDto;

import java.time.LocalDate;
import java.util.List;

public record DocenteResDto(Long numeroDocumento, String nombres, String apellidos,
                            LocalDate fechaNacimiento, String asigDictadas, String gradoEscolaridad,
                            String email, Long fijo, Long celular, TipoDocumentoResDto tipoDocumentoResDto,
                            GradoResDto gradoResDto,
                            @JsonProperty("Asignatura") List<DescripcionResDto> asignaturaOutDtoList) {

}

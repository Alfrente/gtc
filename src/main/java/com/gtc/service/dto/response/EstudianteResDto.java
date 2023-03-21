package com.gtc.service.dto.response;

import com.gtc.service.dto.response.model.DescripcionResDto;

import java.time.LocalDate;
import java.util.List;

public record EstudianteResDto(Long numeroDocumento, String nombres, String apellidos,
                               LocalDate fechaNacimiento, String direccion, String email,
                               Long fijo, Long celular, TipoDocumentoResDto tipoDocumentoResDto,
                               GradoResDto gradoResDto, DescripcionResDto daneResDto,
                               List<DescripcionResDto> asignaturaResDtoList) {

}

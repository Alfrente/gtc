package com.gtc.service.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gtc.service.dto.response.sin_otra_clase.DescripcionResDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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

    private TipoDocumentoResDto tipoDocumentoResDto;
    private GradoResDto gradoResDto;
    @JsonProperty("Asignatura")
    private List<DescripcionResDto> asignaturaOutDtoList;
}

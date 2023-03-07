package com.gtc.service.dto.response;

import com.gtc.service.dto.response.sin_otra_clase.DescripcionResDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


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

    private TipoDocumentoResDto tipoDocumentoResDto;
    private GradoResDto gradoResDto;
    private DescripcionResDto daneResDto;
    private List<DescripcionResDto> asignaturaResDtoList;
}

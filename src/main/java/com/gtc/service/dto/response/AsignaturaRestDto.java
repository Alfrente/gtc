package com.gtc.service.dto.response;

import com.gtc.service.dto.response.sin_otra_clase.DocenteResDto;
import com.gtc.service.dto.response.sin_otra_clase.EstudianteResDto;
import lombok.Data;

import java.util.List;

@Data
public class AsignaturaRestDto {
    private String descripcion;
    private DocenteResDto docente;
    private List<EstudianteResDto> estudianteResDtos;
}

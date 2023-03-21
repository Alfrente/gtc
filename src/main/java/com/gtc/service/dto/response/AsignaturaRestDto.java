package com.gtc.service.dto.response;

import com.gtc.service.dto.response.model.DocenteResDto;
import com.gtc.service.dto.response.model.EstudianteResDto;

import java.util.List;

public record AsignaturaRestDto(String descripcion, DocenteResDto docente,
                                List<EstudianteResDto> estudianteResDtos) {

}

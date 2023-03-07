package com.gtc.service.dto.request;

import lombok.Data;

@Data
public class EstudianteAsignaturaInpDto {
    private String idEstudiante;
    private String idAsignatura;
    private String nota;
    private String periodo;
}

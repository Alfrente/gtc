package com.gtc.service.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class EstudianteAsignaturaFkDto implements Serializable {
    private Long idEstudiante;
    private Long idAsignatura;
}

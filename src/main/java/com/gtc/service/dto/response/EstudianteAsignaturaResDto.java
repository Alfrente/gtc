package com.gtc.service.dto.response;

import lombok.Data;

@Data
public class EstudianteAsignaturaResDto {
    private EstudianteAsignaturaFkDto estudianteAsignaturaFkDto;
    private Double nota;
    private Integer periodo;
}

package com.gtc.service.dto.request;

public record EstudianteAsignaturaInpDto(
        String idEstudiante,
        String idAsignatura,
        String nota,
        String periodo) {
}

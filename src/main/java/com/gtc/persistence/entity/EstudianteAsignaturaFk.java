package com.gtc.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EstudianteAsignaturaFk implements Serializable {
    private Long idEstudiante;
    private Long idAsignatura;
}

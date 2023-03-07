package com.gtc.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "EstudianteAsignatura")
@Data
public class EstudianteAsignatura {
    //@GeneratedValue(generator = "")
    @EmbeddedId
    private EstudianteAsignaturaFk estudianteAsignaturaFk;
    private Double nota;
    private Integer periodo;

    @ManyToOne
    @JoinColumn(name = "idEstudiante", insertable = false, updatable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "idAsignatura", insertable = false, updatable = false)
    private Asignatura asignatura;
}

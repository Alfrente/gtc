package com.gtc.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "EstudianteAsignatura")
public class EstudianteAsignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idEstudiante;
    private Long idAsignatura;
    private Double nota;
    private Integer periodo;

    @ManyToOne
    @JoinColumn(name = "idEstudiante", insertable = false, updatable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "idAsignatura", insertable = false, updatable = false)
    private Asignatura asignatura;
}

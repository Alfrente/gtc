package com.gtc.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Asignatura")
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Long idDocente;

    @ManyToOne
    @JoinColumn(name = "idDocente", updatable = false, insertable = false)
    private Docente docente;

    @ManyToMany(mappedBy = "asignaturas")
    private List<Estudiante> estudiantes;
}

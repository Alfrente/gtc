package com.gtc.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Asignatura")
@Data
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

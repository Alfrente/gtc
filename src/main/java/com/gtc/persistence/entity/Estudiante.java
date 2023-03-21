package com.gtc.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Estudiante")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long numeroDocumento;
    @Column(nullable = false)
    private String nombres;
    @Column(nullable = false)
    private String apellidos;
    @Column(nullable = false)
    private LocalDate fechaNacimiento;
    private String direccion;
    @Column(nullable = false)
    private String email;
    private Long fijo;
    @Column(nullable = false)
    private Long celular;
    @Column(nullable = false)
    private Long idGrado;
    @Column(nullable = false)
    private Long idTipoDocumento;
    @Column(nullable = false)
    private Long idDane;

    @ManyToOne
    @JoinColumn(name = "idTipoDocumento", insertable = false, updatable = false)
    private TipoDocumento tipoDocumento;

    @ManyToOne
    @JoinColumn(name = "idGrado", insertable = false, updatable = false)
    private Grado grado;

    @ManyToOne
    @JoinColumn(name = "idDane", insertable = false, updatable = false)
    private Dane dane;

    @ManyToMany
    @JoinTable(name = "EstudianteAsignatura", joinColumns = @JoinColumn(name = "idEstudiante"),
            inverseJoinColumns = @JoinColumn(name = "idAsignatura")
    )
    private List<Asignatura> asignaturas;

    @OneToMany
    @JoinColumn(name = "idEstudiante", insertable = false, updatable = false)
    private List<EstudianteAsignatura> estudianteAsignaturas;
}

package com.gtc.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Docente")
@Data
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numeroDocumento;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String asigDictadas;
    private String gradoEscolaridad;
    private String email;
    private Long fijo;
    private Long celular;
    private Long idTipoDocumento;
    private Long idGradoResponsable;

    @ManyToOne
    @JoinColumn(name = "idTipoDocumento", updatable = false, insertable = false)
    private TipoDocumento tipoDocumento;
    @ManyToOne
    @JoinColumn(name = "idGradoResponsable", updatable = false, insertable = false)
    private Grado grado;
    @OneToMany(mappedBy = "docente")
    private List<Asignatura> asignaturaList;
}

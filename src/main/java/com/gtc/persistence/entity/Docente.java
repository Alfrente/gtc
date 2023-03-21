package com.gtc.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Docente")
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

    @Override
    public String toString() {
        return "Docente{" +
                "id=" + id +
                ", numeroDocumento=" + numeroDocumento +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", asigDictadas='" + asigDictadas + '\'' +
                ", gradoEscolaridad='" + gradoEscolaridad + '\'' +
                ", email='" + email + '\'' +
                ", fijo=" + fijo +
                ", celular=" + celular +
                ", idTipoDocumento=" + idTipoDocumento +
                ", idGradoResponsable=" + idGradoResponsable +
                '}';
    }
}

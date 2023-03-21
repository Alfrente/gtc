package com.gtc.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Dane")
public class Dane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Long idTipoDane;

    @ManyToOne
    @JoinColumn(name = "idTipoDane", updatable = false, insertable = false)
    private TipoDane tipoDane;
}

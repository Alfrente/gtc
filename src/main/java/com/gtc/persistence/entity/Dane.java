package com.gtc.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Dane")
@Data
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

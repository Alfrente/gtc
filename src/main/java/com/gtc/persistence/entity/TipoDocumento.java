package com.gtc.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TipoDocumento")
@Data
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
}

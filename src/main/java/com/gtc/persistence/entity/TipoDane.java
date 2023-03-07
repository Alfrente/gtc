package com.gtc.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TipoDane")
@Data
public class TipoDane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
}

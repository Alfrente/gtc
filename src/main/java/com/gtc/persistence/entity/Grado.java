package com.gtc.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Grado")
@Data
public class Grado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
}

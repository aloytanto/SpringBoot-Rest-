package com.aloysius.rest.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Data
public class Biodata {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // AutoIncrement
    private Long id;

    @Column(name = "nama")
    private String nama;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "pekerjaan")
    private String pekerjaan;
}

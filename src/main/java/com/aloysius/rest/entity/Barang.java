package com.aloysius.rest.entity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Barang {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // AutoIncrement
    private Long id;

    @Column(name = "item")
    private String item;

    @Column(name = "jumlah")
    private String jumlah;

}

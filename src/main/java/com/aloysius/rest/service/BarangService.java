package com.aloysius.rest.service;

import com.aloysius.rest.entity.Barang;
import com.aloysius.rest.entity.Biodata;

import java.util.List;

public interface BarangService {
    List<Barang> findAll();

    Barang findById(Long id);

    Barang update(Barang barang);

    Barang create(Barang barang);

    void delete(Long id);

    List<Barang> listAll();
}

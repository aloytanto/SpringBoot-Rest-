package com.aloysius.rest.service;

import com.aloysius.rest.entity.Biodata;


import java.util.List;

public interface BiodataService {

    List<Biodata> findAll();

    Biodata findById(Long id);

    Biodata update(Biodata biodata);

    Biodata create(Biodata biodata);

    void delete(Long id);

    List<Biodata> listAll();

}

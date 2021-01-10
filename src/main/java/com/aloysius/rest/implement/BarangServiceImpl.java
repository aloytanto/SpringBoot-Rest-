package com.aloysius.rest.implement;

import com.aloysius.rest.entity.Barang;
import com.aloysius.rest.entity.Biodata;
import com.aloysius.rest.repository.BarangRepository;
import com.aloysius.rest.repository.BiodataRepository;
import com.aloysius.rest.service.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarangServiceImpl implements BarangService {
    @Autowired
    BarangRepository barangRepository;

    @Override
    public List<Barang> findAll() {
        return barangRepository.findAll();
    }

    @Override
    public Barang findById(Long id) {
        return barangRepository.findById(id).get();
    }

    @Override
    public Barang update(Barang barang) {
        return barangRepository.save(barang);
    }

    @Override
    public Barang create(Barang barang) {
        return barangRepository.save(barang);
    }


    @Override
    public void delete(Long id) {
        barangRepository.deleteById(id);
    }

    @Override
    public List<Barang> listAll() {
        return barangRepository.findAll(Sort.by("item").descending());
    }


}

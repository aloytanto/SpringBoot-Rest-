package com.aloysius.rest.implement;

import com.aloysius.rest.entity.Biodata;
import com.aloysius.rest.entity.Customer;
import com.aloysius.rest.repository.BiodataRepository;
import com.aloysius.rest.repository.CustomerRepository;
import com.aloysius.rest.service.BiodataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BiodataServiceImpl implements BiodataService {
    @Autowired
    BiodataRepository biodataRepository;

    @Override
    public List<Biodata> findAll() {
        return biodataRepository.findAll();
    }

 @Override
    public Biodata findById(Long id) {
        return biodataRepository.findById(id).get();
    }

@Override
    public Biodata update(Biodata biodata) {
        return biodataRepository.save(biodata);
    }

    @Override
    public Biodata create(Biodata biodata) {
        return biodataRepository.save(biodata);
    }


    @Override
    public void delete(Long id) {
        biodataRepository.deleteById(id);
    }

    @Override
    public List<Biodata> listAll() {
        return biodataRepository.findAll(Sort.by("nama").ascending());
    }

}

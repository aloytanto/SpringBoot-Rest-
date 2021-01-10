package com.aloysius.rest.repository;

import com.aloysius.rest.entity.Biodata;
import com.aloysius.rest.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BiodataRepository extends JpaRepository<Biodata,Long> {

}

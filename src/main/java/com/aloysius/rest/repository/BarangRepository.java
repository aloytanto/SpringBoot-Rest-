package com.aloysius.rest.repository;

import com.aloysius.rest.entity.Barang;
import com.aloysius.rest.entity.Biodata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarangRepository extends JpaRepository<Barang,Long> {
}

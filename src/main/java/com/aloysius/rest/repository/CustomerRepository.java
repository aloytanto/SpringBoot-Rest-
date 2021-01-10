package com.aloysius.rest.repository;

import com.aloysius.rest.entity.Biodata;
import com.aloysius.rest.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {


}

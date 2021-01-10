package com.aloysius.rest;

import com.aloysius.rest.entity.Barang;
import com.aloysius.rest.entity.Biodata;
import com.aloysius.rest.entity.Customer;
import com.aloysius.rest.repository.BarangRepository;
import com.aloysius.rest.repository.BiodataRepository;
import com.aloysius.rest.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestApplicationTests {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BiodataRepository biodataRepository;

    @Autowired
    BarangRepository barangRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testCreateCustomer(){

        Customer customer = new Customer();
        customer.setName("Alex");
        customer.setPhone("0812345678");
        customer.setEmail("aloy@gmail.com");

        customerRepository.save(customer);

    }

    @Test
    public void testCreateBiodata(){
        Biodata biodata = new Biodata();
       biodata.setNama("gunawam");
        biodata.setAlamat("kopen");
        biodata.setPekerjaan("buruh");

        biodataRepository.save(biodata);

    }

    @Test
    public void testCreateBarang(){
        Barang barang = new Barang();
        barang.setItem("sepatu");
        barang.setJumlah("4");


        barangRepository.save(barang);

    }

}

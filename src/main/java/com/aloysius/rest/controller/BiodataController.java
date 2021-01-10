package com.aloysius.rest.controller;

import com.aloysius.rest.Util.Response;
import com.aloysius.rest.entity.Biodata;
import com.aloysius.rest.entity.Customer;
import com.aloysius.rest.exception.ResourceNotFoundException;
import com.aloysius.rest.service.BiodataService;
import com.aloysius.rest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "biodata")
public class BiodataController {
@Autowired
    BiodataService BiodataService;


    @PostMapping
    ResponseEntity<Response> create (@RequestBody @Validated Biodata biodata)
    {

        Response response = new Response();
        response.setService("Create Biodata");
        response.setMessage("Biodata data has been created");

        response.setData(BiodataService.create(biodata));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }

    // Update biodata
    @PutMapping(path = "/{id}")
    ResponseEntity<Response> update (@PathVariable("id") Long id, @RequestBody @Validated Biodata biodata) /*Mengambil Request data dari Body dan melakukan Proses Validasi, diseleksi berdasarkan id*/
    {

        Response response = new Response();
        response.setService("Update Biodata");
        response.setMessage("Biodata data has been updated");

        Biodata currentBiodata = BiodataService.findById(id);

        currentBiodata.setNama(biodata.getNama());
        currentBiodata.setAlamat(biodata.getAlamat());
        currentBiodata.setPekerjaan(biodata.getPekerjaan());

        response.setData(BiodataService.update(currentBiodata));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }

    // Get customer by Id
    @GetMapping(path = "/{id}")
    ResponseEntity getById(@PathVariable("id") Long id)/*Mengambil Request data dari Berdasarkan id*/
    {

        System.out.println("Get function ID = "+id);

        Response response = new Response();
        response.setService("Get biodata by Id");
        response.setMessage("Data biodata has been fetched");

        response.setData(BiodataService.findById(id));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    // Get all customers
    @GetMapping
    ResponseEntity<Response> findAll()
    {

        Response response = new Response();
        response.setService("Get all data biodata");
        response.setMessage("All data biodata(s) has been fetched");

        response.setData(BiodataService.findAll());

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);


    }


    // Delete Customer
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Response> deleteById (@PathVariable ("id")Long id)
    {

        System.out.println("Delete user. ID = "+id);

        Response response = new Response();
        response.setService("Delete customer data");
        response.setMessage("Customer data has been deleted");
        response.setData(BiodataService.findById(id));

        BiodataService.delete(id);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);


    }
    // export CSV
    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        try{
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Biodata> listUsers = BiodataService.listAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"User ID", "nama", "alamat", "pekerjaan"};
        String[] nameMapping = {"id", "nama", "alamat", "pekerjaan"};

        csvWriter.writeHeader(csvHeader);

        for (Biodata user : listUsers) {
            csvWriter.write(user, nameMapping);
        }

        csvWriter.close();

    }catch (ResourceNotFoundException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Foo Not Found", exc);

        }
    }
}

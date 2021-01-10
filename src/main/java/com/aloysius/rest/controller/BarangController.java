package com.aloysius.rest.controller;


import com.aloysius.rest.Util.Response;
import com.aloysius.rest.entity.Barang;
import com.aloysius.rest.entity.Biodata;
import com.aloysius.rest.exception.ResourceNotFoundException;
import com.aloysius.rest.service.BarangService;
import com.aloysius.rest.service.BiodataService;
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
@RequestMapping(value = "barang")
public class BarangController {

    @Autowired
    BarangService barangService;

    @PostMapping
    ResponseEntity<Response> create (@RequestBody @Validated Barang barang)
    {
try {
    Response response = new Response();
    response.setService("Create Barang");
    response.setMessage("Barang data has been created");

    response.setData(barangService.create(barang));

    return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);

}catch (ResourceNotFoundException exc) {
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Foo Not Found", exc);

       }


    }
    @PutMapping(path = "/{id}")
    ResponseEntity<Response> update (@PathVariable("id") Long id, @RequestBody @Validated Barang barang) /*Mengambil Request data dari Body dan melakukan Proses Validasi, diseleksi berdasarkan id*/
    {
try {
    Response response = new Response();
    response.setService("Update Barang");
    response.setMessage("Barang data has been updated");

    Barang currentBarang = barangService.findById(id);


    currentBarang.setItem(barang.getItem());
    currentBarang.setJumlah(barang.getJumlah());
    response.setData(barangService.update(currentBarang));

    return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
}

catch (ResourceNotFoundException exc) {
    throw new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Foo Not Found", exc);

}
    }


    @GetMapping(path = "/{id}")
    ResponseEntity getById(@PathVariable("id") Long id)/*Mengambil Request data dari Berdasarkan id*/
    {

        System.out.println("Get function ID = "+id);

        Response response = new Response();
        response.setService("Get biodata by Id");
        response.setMessage("Data biodata has been fetched");

        response.setData(barangService.findById(id));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping
    ResponseEntity<Response> findAll()
    {
try {
    Response response = new Response();
    response.setService("Get all data customer");
    response.setMessage("All data customer(s) has been fetched");

    response.setData(barangService.findAll());

    return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
}catch (ResourceNotFoundException exc) {
    throw new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Foo Not Found", exc);

}

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Response> deleteById (@PathVariable ("id")Long id)
    {

        System.out.println("Delete user. ID = "+id);

        Response response = new Response();
        response.setService("Delete barang data");
        response.setMessage("Barang data has been deleted");
        response.setData(barangService.findById(id));
        ;

        barangService.delete(id);


        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);


    }
    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {

     try {
         response.setContentType("text/csv");
         DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
         String currentDateTime = dateFormatter.format(new Date());

         String headerKey = "Content-Disposition";
         String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
         response.setHeader(headerKey, headerValue);

         List<Barang> listUsers = barangService.listAll();

         ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
         String[] csvHeader = {"User ID", "item", "jumlah"};
         String[] nameMapping = {"id", "item", "jumlah"};

         csvWriter.writeHeader(csvHeader);

         for (Barang user : listUsers) {
             csvWriter.write(user, nameMapping);
         }

         csvWriter.close();
     }catch (ResourceNotFoundException exc) {
         throw new ResponseStatusException(
                 HttpStatus.NOT_FOUND, "Foo Not Found", exc);

     }


    }







}

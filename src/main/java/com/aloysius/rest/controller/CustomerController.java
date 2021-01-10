package com.aloysius.rest.controller;

import com.aloysius.rest.Util.Response;
import com.aloysius.rest.entity.Customer;
import com.aloysius.rest.exception.ResourceNotFoundException;
import com.aloysius.rest.repository.CustomerRepository;
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
@RequestMapping(value = "customer")


public class CustomerController {


    @Autowired
    CustomerService customerService;


    // Create customer
    @PostMapping
    ResponseEntity<Response> create (@RequestBody @Validated Customer customer)
    {

        Response response = new Response();
        response.setService("Create customer");
        response.setMessage("Customer data has been created");

        response.setData(customerService.create(customer));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }

    // Update customer
    @PutMapping(path = "/{id}")
    ResponseEntity<Response> update (@PathVariable ("id") Long id, @RequestBody @Validated Customer customer) /*Mengambil Request data dari Body dan melakukan Proses Validasi, diseleksi berdasarkan id*/
    {

        Response response = new Response();
        response.setService("Update Customer");
        response.setMessage("Customer data has been updated");

        Customer currentCustomer = customerService.findById(id);

        currentCustomer.setName(customer.getName());
        currentCustomer.setPhone(customer.getPhone());
        currentCustomer.setEmail(customer.getEmail());

        response.setData(customerService.update(currentCustomer));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }

    // Get customer by Id
    @GetMapping(path = "/{id}")
    ResponseEntity getById (@PathVariable("id") Long id)/*Mengambil Request data dari Berdasarkan id*/
    {

        System.out.println("Get function ID = "+id);

        Response response = new Response();
        response.setService("Get customer by Id");
        response.setMessage("Data customer has been fetched");

        response.setData(customerService.findById(id));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    // Get all customers
    @GetMapping
    ResponseEntity<Response> findAll ()
    {

        Response response = new Response();
        response.setService("Get all data customer");
        response.setMessage("All data customer(s) has been fetched");

        response.setData(customerService.findAll());

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
        response.setData(customerService.findById(id));

        customerService.delete(id);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);


    }
    // export CSV
    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {

        try {
            response.setContentType("text/csv");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
            response.setHeader(headerKey, headerValue);

            List<Customer> listUsers = customerService.listAll();

            ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
            String[] csvHeader = {"User ID", "name", "phone", "email"};
            String[] nameMapping = {"id", "name", "phone", "email"};

            csvWriter.writeHeader(csvHeader);

            for (Customer user : listUsers) {
                csvWriter.write(user, nameMapping);
            }

            csvWriter.close();
        }catch (ResourceNotFoundException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Foo Not Found", exc);

        }


    }

    }

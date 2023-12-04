package com.nagarro.customer.service.controller;

import com.nagarro.customer.service.models.Customer;
import com.nagarro.customer.service.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class Controller {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer customer1 = customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer1);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getSingleCustomer(@PathVariable String customerId){
        Customer customer = customerService.getCustomer(customerId);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable String customerId){
        Customer customer = customerService.deleteCustomer(customerId);
        return ResponseEntity.ok(customer);
    }
}

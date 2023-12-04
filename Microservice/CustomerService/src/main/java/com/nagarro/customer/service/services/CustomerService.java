package com.nagarro.customer.service.services;

import com.nagarro.customer.service.models.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer saveCustomer(Customer customer);

    Customer getCustomer(String customerId);

    Customer updateCustomer(Customer customer);

    Customer deleteCustomer(String customerId);
}

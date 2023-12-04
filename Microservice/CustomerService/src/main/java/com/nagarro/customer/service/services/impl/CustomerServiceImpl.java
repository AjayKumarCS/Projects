package com.nagarro.customer.service.services.impl;

import com.nagarro.customer.service.dao.CustomerDao;
import com.nagarro.customer.service.exceptions.ResourceNotFoundException;
import com.nagarro.customer.service.models.Customer;
import com.nagarro.customer.service.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public Customer getCustomer(String customerId) {
        return customerDao.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer with id " + customerId + " is not available on server!!"));
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public Customer deleteCustomer(String customerId) {
        try {
            Customer customer = getCustomer(customerId);
            customerDao.deleteById(customerId);
            return customer;
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Deletion Failed");
        }

    }
}

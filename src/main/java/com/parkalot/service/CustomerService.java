package com.parkalot.service;

import com.parkalot.model.Customer;
import com.parkalot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // REGISTER USER
    public Customer register(Customer customer) {

        Optional<Customer> existing = customerRepository.findByEmail(customer.getEmail());

        if (existing.isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        return customerRepository.save(customer);
    }

    // LOGIN USER
    public Customer login(String email, String password) {

        Optional<Customer> customer = customerRepository.findByEmail(email);

        if (customer.isPresent() && customer.get().getPassword().equals(password)) {
            return customer.get();
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }
}
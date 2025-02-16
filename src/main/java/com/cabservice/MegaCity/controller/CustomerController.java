package com.cabservice.MegaCity.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cabservice.MegaCity.model.Customer;
import com.cabservice.MegaCity.service.CustomerService;


@RestController
 @CrossOrigin(origins = "*")

public class CustomerController {
    
     @Autowired
    private CustomerService customerService;

     @Autowired
        private PasswordEncoder passwordEncoder;

    /**
     * Retrieves all customers.
     * @return A list of all customers.
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    /**
     * Retrieves a customer by their ID.
     * @param id The ID of the customer to retrieve.
     * @return The customer if found, otherwise returns 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Optional<Customer> customer = customerService.findById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new customer.
     * @param customer The customer details received in the request body.
     * @return The created customer with HTTP status 201 (Created).
     */
    @PostMapping("/auth/createuser")
public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    // Encode the password before saving
    customer.setPassword(passwordEncoder.encode(customer.getPassword()));
    
    Customer createdCustomer = customerService.create(customer);
    
    return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
}

    /**
     * Updates an existing customer.
     * @param id The ID of the customer to update.
     * @param customer The updated customer details.
     * @return The updated customer details with HTTP status 200 (OK).
     */
    // @PutMapping("/{id}")
    // public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
    //     Customer updatedCustomer = customerService.update(id, customer);
    //     return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    // }

    /**
     * Deletes a customer by their ID.
     * @param id The ID of the customer to delete.
     * @return A 204 No Content response if the deletion is successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
}
}
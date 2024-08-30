package org.omni.customer.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @PostMapping(value = "/")
    public void createCustomer() {
//        TO DO
    }

    @GetMapping(value = "/{id}")
    public void findCustomerById() {
//        TO DO
    }

    @PutMapping(value = "/{id}")
    public void updateCustomer() {
//        TO DO
    }

    @DeleteMapping(value = "delete")
    public void deleteCustomer() {
//        TO DO
    }

    @GetMapping(value = "/")
    public void findrAllCustomer() {
//        TO DO
    }

    @GetMapping(value = "/search")
    public void searchCustomer(){
//        TO DO
    }
}

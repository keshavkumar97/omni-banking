package org.omni.bank.customer.controller;

import org.omni.bank.customer.dto.CustomerDto;
import org.omni.bank.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto createdCustomer =
                customerService.createCustomer(customerDto);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

//    @GetMapping(value = "/{id}")
//    public void findCustomerById() {
////        TODO
//    }
//
//    @PutMapping(value = "/{id}")
//    public void updateCustomer() {
////        TODO
//    }
//
//    @DeleteMapping(value = "delete")
//    public void deleteCustomer() {
////        TODO
//    }
//
//    @GetMapping(value = "/")
//    public void findrAllCustomer() {
////        TODO
//    }
//
//    @GetMapping(value = "/search")
//    public void searchCustomer() {
////        TODO
//    }
}

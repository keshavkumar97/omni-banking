package org.omni.bank.customer.controller;

import jakarta.validation.Valid;
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

    /**
     * X-User-Id and X-User-Role are forwarded by the API Gateway after JWT validation.
     * No Spring Security filter needed here — the gateway is the enforcement point.
     */
    @PostMapping(value = "/")
    public ResponseEntity<CustomerDto> createCustomer(
            @Valid @RequestBody CustomerDto customerDto,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        CustomerDto createdCustomer = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping(value = "/")
    public ResponseEntity<CustomerDto> findCustomerById(@RequestParam("email") String email) {
        CustomerDto customer = customerService.fetchCustomer(email);
        return ResponseEntity.ok(customer);
    }
}

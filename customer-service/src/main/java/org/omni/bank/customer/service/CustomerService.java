package org.omni.bank.customer.service;

import org.omni.bank.customer.dto.CustomerDto;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto fetchCustomer(String email);
}

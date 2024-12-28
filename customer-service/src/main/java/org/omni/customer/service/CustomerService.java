package org.omni.customer.service;

import org.omni.customer.dto.CustomerDto;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);
}

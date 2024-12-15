package org.omni.customer.util;

import org.omni.customer.dto.CustomerDto;
import org.omni.customer.model.Customer;

public class CustomerMapperUtil {
    private CustomerMapperUtil() {

    }

    public static Customer toEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail().toLowerCase());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());
        customer.setDob(customerDto.getDob());
        return customer;
    }

    public static CustomerDto toDTO(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail().toLowerCase());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setAddress(customer.getAddress());
        dto.setDob(customer.getDob());
        return dto;
    }
}

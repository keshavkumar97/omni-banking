package org.omni.customer.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.omni.customer.dto.CustomerDto;
import org.omni.customer.exception.DuplicateEntryException;
import org.omni.customer.model.Customer;
import org.omni.customer.repository.CustomerRepo;
import org.omni.customer.service.CustomerService;
import org.omni.customer.util.CustomerMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo repo;

    CustomerServiceImpl(@Autowired CustomerRepo repo) {
        this.repo = repo;
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        if (repo.existsByEmail(customerDto.getEmail().toLowerCase()))
            throw new DuplicateEntryException("Email is already registered " +
                    "with a Customer");
        log.info("Onboard Customer. Saving Customer data");
        Customer customer = CustomerMapperUtil.toEntity(customerDto);
        return CustomerMapperUtil.toDTO(repo.save(customer));
    }

}

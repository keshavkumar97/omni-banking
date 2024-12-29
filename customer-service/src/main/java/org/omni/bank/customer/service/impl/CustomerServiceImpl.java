package org.omni.bank.customer.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.omni.bank.customer.dto.CustomerDto;
import org.omni.bank.customer.exception.DuplicateEntryException;
import org.omni.bank.customer.repository.CustomerRepo;
import org.omni.bank.customer.model.Customer;
import org.omni.bank.customer.service.CustomerService;
import org.omni.bank.customer.util.CustomerMapperUtil;
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

package org.omni.customer.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.omni.customer.dto.CustomerDto;
import org.omni.customer.exception.DuplicateEntryException;
import org.omni.customer.model.Customer;
import org.omni.customer.repository.CustomerRepo;
import org.omni.customer.util.CustomerMapperUtil;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    private static CustomerDto customerDto;
    private static Customer customerEntity;
    @InjectMocks
    CustomerServiceImpl customerService;
    @Mock
    CustomerRepo customerRepo;

    @BeforeAll
    static void initialize() {
        customerDto = new CustomerDto(0, "John",
                "Abraham", "john@email.com",
                "9876543210", "Steve Road, John Costal line, Arizona",
                LocalDate.of(1997, 7, 12));
        customerEntity = CustomerMapperUtil.toEntity(customerDto);
    }

    @Test
    void testCreateCustomer() {
        Customer customerSaved = new Customer(1L, "John",
                "Abraham", "john@email.com",
                "9876543210", "Steve Road, John Costal line, Arizona",
                LocalDate.of(1997, 7, 12), null, null);

//        Mock repository behavior
        Mockito.when(customerRepo.existsByEmail(customerDto.getEmail().toLowerCase())).thenReturn(false);
        Mockito.when(customerRepo.save(customerEntity)).thenReturn
                (customerSaved);
//        this also works perfectly
//        Mockito.when(customerRepo.save(any(Customer.class))).thenReturn(customerSaved);
//        Act
        CustomerDto result = customerService.createCustomer(customerDto);

        assertNotNull(result);
        assertEquals(1L, result.getCustomerId());
        assertEquals("John", result.getFirstName());
        assertEquals("Abraham", result.getLastName());
        assertEquals("john@email.com", result.getEmail());
        assertEquals("9876543210", result.getPhoneNumber());

        // Verify repository interaction
        Mockito.verify(customerRepo, Mockito.atMostOnce()).existsByEmail(customerDto.getEmail().toLowerCase());
        Mockito.verify(customerRepo, Mockito.times(1)).save(any(Customer.class));

    }

    @Test
    void testDuplicateEntryException() {
        Mockito.when(customerRepo.existsByEmail(customerDto.getEmail().toLowerCase())).thenReturn(true);
        DuplicateEntryException duplicateEntryException =
                assertThrows(DuplicateEntryException.class,
                        () -> customerService.createCustomer(customerDto));

//        assertEquals(DuplicateEntryException.class,
//                duplicateEntryException.getClass());// this assert is
//        // duplicate as assertthrows already checks for the exception
        assertEquals("Email is already registered with a Customer", duplicateEntryException.getMessage());

        Mockito.verify(customerRepo, Mockito.times(1)).existsByEmail(customerDto.getEmail().toLowerCase());
        Mockito.verify(customerRepo, Mockito.never()).save(Mockito.any(Customer.class));
    }
}
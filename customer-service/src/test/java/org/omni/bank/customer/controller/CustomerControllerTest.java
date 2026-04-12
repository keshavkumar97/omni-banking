package org.omni.bank.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.omni.bank.customer.dto.CustomerDto;
import org.omni.bank.customer.service.impl.CustomerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    private static ObjectMapper objectMapper;
    private static CustomerDto customerDto;
    private MockMvc mockMvc;

    @Mock
    private CustomerServiceImpl customerService;
    @InjectMocks
    private CustomerController customerController;

    @BeforeAll
    static void initialize() {
        customerDto = new CustomerDto(0, "John",
                "Abraham", "john@email.com",
                "9876543210", "Steve Road, John Costal line, Arizona",
                LocalDate.of(1997, 7, 12));
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testCreateCustomer() { //testing core logic of controller
        Mockito.when(customerService.createCustomer(any(CustomerDto.class))).thenReturn(customerDto);
        ResponseEntity<CustomerDto> responseEntity =
                customerController.createCustomer(customerDto, "test-user-id", "CUSTOMER");

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody(), "Response body " +
                "should not be null");
        Assertions.assertEquals("John", responseEntity.getBody().getFirstName());

//  optional as service is being tested separately. so checking is correct interaction from controller is optional
        verify(customerService, Mockito.times(1)).createCustomer(any(CustomerDto.class));
    }

    @Test
    void testCreateCustomerWithMockMvc() throws Exception {
        Mockito.when(customerService.createCustomer(any(CustomerDto.class))).thenReturn(customerDto);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                MockMvcRequestBuilders.post("/customers/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto));
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"));
    }


    @Test
    void findCustomerById() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void findrAllCustomer() {
    }

    @Test
    void searchCustomer() {
    }
}
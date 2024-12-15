package org.omni.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private long customerId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    private LocalDate dob;
}

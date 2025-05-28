package org.omni.bank.customer.repository;

import org.omni.bank.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    // checks whether user is available with the specified email
    boolean existsByEmail(String email);

    Optional<Customer> findByEmail(String email);
}

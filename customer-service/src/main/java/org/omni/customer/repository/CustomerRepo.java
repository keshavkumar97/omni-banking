package org.omni.customer.repository;

import org.omni.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    // checks whether user is available with the specified email
    boolean existsByEmail(String email);
}

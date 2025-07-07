package org.omni.bank.auth.repositories;

import org.omni.bank.auth.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    Optional<Users> findByUserName(String userName);
}
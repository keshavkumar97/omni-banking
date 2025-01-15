package org.omni.bank.auth.repositories;

import org.omni.bank.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
}

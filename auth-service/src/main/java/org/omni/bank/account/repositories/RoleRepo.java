package org.omni.bank.account.repositories;

import org.omni.bank.account.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
}

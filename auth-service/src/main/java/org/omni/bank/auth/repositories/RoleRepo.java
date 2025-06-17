//package org.omni.bank.auth.repositories;
//
//import org.omni.bank.auth.model.Role;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//
//@Repository
//public interface RoleRepo extends JpaRepository<Role, Long> {
//    @Query(value = "select r.roleCode from Role r where " +
//            "r.roleName = :roleName")
//    String findRoleCodeByName(@Param("roleName") String roleName);
//}

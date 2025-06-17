//package org.omni.bank.auth.service.impl;
//
//import io.micrometer.common.util.StringUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.omni.bank.auth.exception.DuplicateEntryException;
//import org.omni.bank.auth.model.Role;
//import org.omni.bank.auth.repositories.RoleRepo;
//import org.omni.bank.auth.service.RoleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Slf4j
//public class RoleServiceImpl implements RoleService {
//    private final RoleRepo repo;
//
//    public RoleServiceImpl(@Autowired RoleRepo repo) {
//        this.repo = repo;
//    }
//
////    @Override
////    @Transactional
////    public Role createRole(String roleName, String description,
////                           String roleCode) {
////        if (StringUtils.isBlank(roleName))
////            throw new IllegalArgumentException("Role name cannot be null");
////        Role role = null;
////        if (repo.findRoleCodeByName(roleName) != null) {
////            log.error("Role already present");
////            throw new DuplicateEntryException(HttpStatus.BAD_REQUEST, "Role already " +
////                    "Present");
////        }
////        try {
////            role = repo.save(new Role(roleName, description, roleCode));
////            if(role == null)
////                throw new Exception("can't save");
////        } catch (DataIntegrityViolationException dIVEx) {
////            log.error("Error occured while trying to save data");
////            throw new DataIntegrityViolationException("Invalid data", dIVEx);
////        }catch ( Exception e){
////            log.error("Error occured while trying to save data");
////        }
////        return role;
////    }
//}

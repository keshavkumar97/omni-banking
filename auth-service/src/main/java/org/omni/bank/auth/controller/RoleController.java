package org.omni.bank.auth.controller;

import org.omni.bank.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(@Autowired RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/")
    public ResponseEntity<String> createRole(@RequestParam(name = "roleName") String roleName,
                                             @RequestParam(name =
                                                     "description") String description,
                                             @RequestParam(name = "roleCode") String roleCode) {
        roleService.createRole(roleName, description, roleCode);
        return ResponseEntity.status(HttpStatus.CREATED).body("Role created");
    }

//    @GetMapping("/")
//    public void getAllRole(){
//
//    }
}

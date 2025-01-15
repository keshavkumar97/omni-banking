package org.omni.bank.account.controller;

import org.omni.bank.account.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(@Autowired RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/")
    public ResponseEntity<String> createRole(@RequestParam(name = "roleName") String roleName,
                                             @RequestParam(name = "description") String description) {
        roleService.createRole(roleName, description);
        return ResponseEntity.status(HttpStatus.CREATED).body("Role created");
    }

//    @GetMapping("/")
//    public void getAllRole(){
//
//    }
}

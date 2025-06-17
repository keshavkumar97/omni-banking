package org.omni.bank.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
//    private final RoleService roleService;

    public RoleController() {

    }
// not needed in real world as role creation is done manually
//    @PostMapping("/")
//    public ResponseEntity<String> createRole(@RequestParam(name = "roleName") String roleName,
//                                             @RequestParam(name =
//                                                     "description") String description,
//                                             @RequestParam(name = "roleCode") String roleCode) {
//        roleService.createRole(roleName, description, roleCode);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Role created");
//    }

//    @GetMapping("/")
//    public void getAllRole(){
//
//    }
}

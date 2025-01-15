package org.omni.bank.account.service;

import org.omni.bank.account.model.Role;

public interface RoleService {
    Role createRole(String roleName, String description);
}

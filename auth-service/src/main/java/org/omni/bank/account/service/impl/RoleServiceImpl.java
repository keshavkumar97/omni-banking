package org.omni.bank.account.service.impl;

import io.micrometer.common.util.StringUtils;
import org.omni.bank.account.model.Role;
import org.omni.bank.account.repositories.RoleRepo;
import org.omni.bank.account.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepo repo;

    public RoleServiceImpl(@Autowired RoleRepo repo) {
        this.repo = repo;
    }

    @Override
    public Role createRole(String roleName, String description) {
        if (StringUtils.isBlank(roleName))
            throw new IllegalArgumentException("Role name cannot be null");

        return repo.save(new Role(roleName, description));
    }
}

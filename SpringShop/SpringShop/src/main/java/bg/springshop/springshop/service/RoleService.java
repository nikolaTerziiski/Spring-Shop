package bg.springshop.springshop.service;

import bg.springshop.springshop.model.entity.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> findAll();
}

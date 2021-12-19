package bg.springshop.springshop.service.impl;
import bg.springshop.springshop.repository.RoleRepository;
import bg.springshop.springshop.service.RoleService;
import bg.springshop.springshop.model.entity.Role;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> findAll() {
        return this.roleRepository.findAll().stream().collect(Collectors.toSet());
    }
}

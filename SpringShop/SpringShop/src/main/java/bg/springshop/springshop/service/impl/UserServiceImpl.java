package bg.springshop.springshop.service.impl;

import bg.springshop.springshop.model.binding.UserRegistrationBindingModel;
import bg.springshop.springshop.model.entity.Role;
import bg.springshop.springshop.model.entity.User;
import bg.springshop.springshop.model.entity.enums.RoleEnum;
import bg.springshop.springshop.repository.UserRepository;
import bg.springshop.springshop.service.RoleService;
import bg.springshop.springshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository,
                           RoleService roleService,
                           PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserRegistrationBindingModel userRegistrationBindingModel) {
        User user = modelMapper.map(userRegistrationBindingModel, User.class);
        user.setPassword(passwordEncoder.encode(userRegistrationBindingModel.getPassword()));

        Set<Role> roles = new HashSet<>();

        if(this.userRepository.countUsers() == 0){
            user.setRoles(roleService.findAll());
        }else{
            user.setRoles(( roleService.findAll().stream().filter(e -> e.getName() == RoleEnum.USER).collect(Collectors.toSet())));
        }

        userRepository.save(user);
    }

    @Override
    public boolean userExistsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public boolean userExistsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }
}

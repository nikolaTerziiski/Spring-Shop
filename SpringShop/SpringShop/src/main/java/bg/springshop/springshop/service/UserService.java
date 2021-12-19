package bg.springshop.springshop.service;

import bg.springshop.springshop.model.binding.UserRegistrationBindingModel;
import bg.springshop.springshop.model.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    void registerUser(UserRegistrationBindingModel userRegistrationBindingModel);

    boolean userExistsByUsername(String username);

    boolean userExistsByEmail(String email);
}

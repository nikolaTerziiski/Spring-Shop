package bg.springshop.springshop.service;

import bg.springshop.springshop.model.binding.UserRegistrationBindingModel;
import bg.springshop.springshop.model.entity.User;


public interface UserService {
    void registerUser(UserRegistrationBindingModel userRegistrationBindingModel);

    boolean userExistsByUsername(String username);

    boolean userExistsByEmail(String email);

    User getCurrentLoggedInUser();
}

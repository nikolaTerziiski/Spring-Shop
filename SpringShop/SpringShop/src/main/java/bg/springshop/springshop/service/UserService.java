package bg.springshop.springshop.service;

import bg.springshop.springshop.model.binding.UserRegistrationBindingModel;
import bg.springshop.springshop.model.entity.Order;
import bg.springshop.springshop.model.entity.ShoppingCart;
import bg.springshop.springshop.model.entity.User;
import bg.springshop.springshop.model.view.ProductCartViewModel;

import java.util.List;


public interface UserService {
    void registerUser(UserRegistrationBindingModel userRegistrationBindingModel);

    boolean userExistsByUsername(String username);

    boolean userExistsByEmail(String email);

    User getCurrentLoggedInUser();

    void addToCart(Long productId);

    List<ProductCartViewModel> generateProductsInCart();

    void removeFromCart(Long id);

}

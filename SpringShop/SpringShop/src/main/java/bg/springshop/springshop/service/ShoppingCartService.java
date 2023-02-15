package bg.springshop.springshop.service;

import bg.springshop.springshop.model.entity.Product;
import bg.springshop.springshop.model.entity.ShoppingCart;
import bg.springshop.springshop.model.view.ProductCartViewModel;
import bg.springshop.springshop.model.view.ShoppingCartProductViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ShoppingCartService {
    void registerShoppingCart(ShoppingCart shoppingCart);

    void addProduct(ShoppingCart shoppingCart, Product product);

    void deleteElement(ShoppingCart cart, Product product);
}

package bg.springshop.springshop.service.impl;

import bg.springshop.springshop.model.entity.Product;
import bg.springshop.springshop.model.entity.ShoppingCart;
import bg.springshop.springshop.model.view.ProductCartViewModel;
import bg.springshop.springshop.model.view.ShoppingCartProductViewModel;
import bg.springshop.springshop.repository.ProductRepository;
import bg.springshop.springshop.repository.ShoppingCartRepository;
import bg.springshop.springshop.service.ShoppingCartService;
import bg.springshop.springshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public void registerShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setProducts(new ArrayList<>());
        this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void addProduct(ShoppingCart cart, Product product) {
        cart.getProducts().add(product);
        this.shoppingCartRepository.save(cart);
    }

    @Override
    public void deleteElement(ShoppingCart cart, Product product) {
        cart.getProducts().removeIf(e -> e.id == product.id);

        this.shoppingCartRepository.save(cart);
    }

}

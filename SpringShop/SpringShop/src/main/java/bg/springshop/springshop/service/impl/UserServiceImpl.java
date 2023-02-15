package bg.springshop.springshop.service.impl;

import bg.springshop.springshop.model.binding.UserRegistrationBindingModel;
import bg.springshop.springshop.model.entity.Order;
import bg.springshop.springshop.model.entity.Product;
import bg.springshop.springshop.model.entity.Role;
import bg.springshop.springshop.model.entity.ShoppingCart;
import bg.springshop.springshop.model.entity.User;
import bg.springshop.springshop.model.entity.enums.RoleEnum;
import bg.springshop.springshop.model.view.ProductCartViewModel;
import bg.springshop.springshop.repository.ProductRepository;
import bg.springshop.springshop.repository.UserRepository;
import bg.springshop.springshop.service.RoleService;
import bg.springshop.springshop.service.ShoppingCartService;
import bg.springshop.springshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final ShoppingCartService shoppingCartService;
    private ProductRepository productRepository;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository,
                           RoleService roleService,
                           PasswordEncoder passwordEncoder,
                           ShoppingCartService shoppingCartService,
                           ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.shoppingCartService = shoppingCartService;
        this.productRepository = productRepository;
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

        ShoppingCart shoppingCart = new ShoppingCart();
        this.shoppingCartService.registerShoppingCart(shoppingCart);
        user.setShoppingCart(shoppingCart);
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

    @Override
    public User getCurrentLoggedInUser() {

        return this.userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
            .orElse(null);
    }

    @Override
    public void addToCart(Long productId) {

        User currUser = this.getCurrentLoggedInUser();

        Product dsao = this.productRepository.findAll().stream().filter(e -> Objects.equals(e.id, productId)).findFirst().orElse(null);

        this.shoppingCartService.addProduct(currUser.getShoppingCart(), dsao);
        this.userRepository.save(currUser);
    }

    @Override
    public List<ProductCartViewModel> generateProductsInCart() {
        return this.getCurrentLoggedInUser().getShoppingCart()
            .getProducts().stream().map(e -> {
                return modelMapper.map(e, ProductCartViewModel.class);
            }).collect(Collectors.toList());
    }

    @Override
    public void removeFromCart(Long id) {
        User user = this.getCurrentLoggedInUser();
        Product product = this.productRepository.findById(id).orElse(null);
        this.shoppingCartService.deleteElement(user.getShoppingCart(), product);

        this.userRepository.save(user);
    }

}

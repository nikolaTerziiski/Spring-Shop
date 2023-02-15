package bg.springshop.springshop.web;

import bg.springshop.springshop.model.entity.Product;
import bg.springshop.springshop.model.view.ProductCartViewModel;
import bg.springshop.springshop.model.view.ShoppingCartProductViewModel;
import bg.springshop.springshop.service.ProductService;
import bg.springshop.springshop.service.ShoppingCartService;
import bg.springshop.springshop.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ShoppingCartController {

    private ProductService productService;
    private UserService userService;
    private ShoppingCartService shoppingCartService;

    public ShoppingCartController(ProductService productService,
                                  UserService userService,
                                  ShoppingCartService shoppingCartService) {

        this.productService = productService;

        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }


    @GetMapping("/cart/add/{productId}")
    public String add(@PathVariable Long productId) {

        this.userService.addToCart(productId);

        return "redirect:/products/details/{productId}";
    }

    @GetMapping("/cart")
    public String personalCart(Model model) {

        List<ProductCartViewModel> products = this.userService.generateProductsInCart();

        boolean isEmpty = true;
        if(products.size() > 0 ) {
            model.addAttribute("products", products);
            isEmpty = false;
        }

        model.addAttribute("isEmpty", isEmpty);

        return "cart";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {

        this.userService.removeFromCart(id);

        return "redirect:/cart";
    }
}

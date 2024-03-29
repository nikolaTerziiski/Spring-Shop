package bg.springshop.springshop.web;

import bg.springshop.springshop.model.binding.ProductCreateBindingModel;
import bg.springshop.springshop.model.binding.ProductEditBindingModel;
import bg.springshop.springshop.model.entity.Product;
import bg.springshop.springshop.model.entity.ShoppingCart;
import bg.springshop.springshop.model.entity.User;
import bg.springshop.springshop.model.view.ProductAllViewModel;
import bg.springshop.springshop.model.view.ProductDetailsViewModel;
import bg.springshop.springshop.service.ProductService;
import bg.springshop.springshop.service.UserService;
import bg.springshop.springshop.service.impl.SpringShopUserDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    public ProductController(ProductService productService,
                             SpringShopUserDetailsService springShopUserDetailsService,
                             UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @ModelAttribute
    public ProductCreateBindingModel productCreateBindingModel() {
        return new ProductCreateBindingModel();
    }

    @ModelAttribute
    public ProductEditBindingModel productEditBindingModel() {
        return new ProductEditBindingModel();
    }

    @GetMapping("/add")
    public String add(){
        return "products-add";
    }

    @PostMapping("/add")
    public String add(@Valid ProductCreateBindingModel productCreateBindingModel,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("productCreateBindingModel", productCreateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productBindingModel", bindingResult);

            return "redirect:add";
        }

        this.productService.addProduct(productCreateBindingModel);
        return "redirect:all";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model){

        //Check if the product doesnt exists to redirect to error page
        ProductDetailsViewModel productDetailsViewModel = this.productService.createProductForDetails(id);
        if(productDetailsViewModel == null){
            return "redirect:error";
        }


        //Increment product view
        this.productService.incrementProductView(id);

        model.addAttribute("productToShow", productDetailsViewModel);
        model.addAttribute("isCreator", false);
        model.addAttribute("isAdmin", false);
        model.addAttribute("isInShoppingCart", false);

        User user = userService.getCurrentLoggedInUser();
        //Check if user exists
        if(user == null){
            return "product-details";
        }

        if(productDetailsViewModel.getCreatorId() == user.id){
            model.addAttribute("isCreator", true);
        }

        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(e ->
            e.toString().equals("ROLE_ADMIN")))
        {
            model.addAttribute("isAdmin", true);
            model.addAttribute("isCreator", false);
        }

        ShoppingCart cart = user.getShoppingCart();

        if (user.getShoppingCart().getProducts().size() != 0) {
            if (user.getShoppingCart().getProducts().stream().anyMatch(e -> Objects.equals(e.id, id)))
                model.addAttribute("isInShoppingCart", true);
        }
        return "product-details";
    }

    @GetMapping("/all")
    public String all(Model model) {

        List<ProductAllViewModel>  products = this.productService.takeAll();

        model.addAttribute("products", products);

        return "products-all";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        ProductEditBindingModel productEditBindingModel = this.productService.createProductForEdit(id);
        if(productEditBindingModel == null){
            return "redirect:error";
        }

        if(this.productService.doesUserCreateProduct(id)
            || SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(e ->
            e.toString().equals("ROLE_ADMIN"))){

            model.addAttribute("canChange", false);
            model.addAttribute("productEditBindingModel", productEditBindingModel);
            return "products-edit";
        }

        model.addAttribute("canChange", true);
        return "products-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid ProductEditBindingModel productEditBindingModel,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       @PathVariable Long id){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("productEditBindingModel", productEditBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productEditBindingModel", bindingResult);

            return "redirect:/products/edit/{id}";
        }

        this.productService.editProduct(productEditBindingModel, id);
        return "redirect:/products/details/{id}";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        if(!this.productService.doesProductExist(id)){
            return "redirect:/error";
        }

        if(this.productService.doesUserCreateProduct(id)
            || SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(e ->
            e.toString().equals("ROLE_ADMIN"))){

            this.productService.deleteProduct(id);
            return "redirect:/";
        }

        return "redirect:/error";
    }
}

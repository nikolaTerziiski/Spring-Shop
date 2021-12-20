package bg.springshop.springshop.web;

import bg.springshop.springshop.model.binding.ProductBindingModel;
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
    public ProductBindingModel productBindingModel() {
        return new ProductBindingModel();
    }

    @GetMapping("/add")
    public String add(){
        return "products-add";
    }

    @PostMapping("/add")
    public String add(@Valid ProductBindingModel productBindingModel,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("routeAddBindingModel", productBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productBindingModel", bindingResult);

            return "redirect:add";
        }

        this.productService.addProduct(productBindingModel);
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


        if(productDetailsViewModel.getCreatorId() == userService.getCurrentLoggedInUser().id){
            model.addAttribute("isCreator", true);
            return "product-details";
        }

        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("ROLE_ADMIN") ||
            SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("ROLE_MASTER"))
        {
            model.addAttribute("isAdmin", true);
        }
        return "product-details";
    }

    @GetMapping("/all")
    public String all() {


        return "products-all";
    }

}

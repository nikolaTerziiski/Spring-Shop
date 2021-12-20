package bg.springshop.springshop.web;

import bg.springshop.springshop.model.binding.ProductBindingModel;
import bg.springshop.springshop.service.ProductService;
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

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute
    public ProductBindingModel productBindingModel() {
        return new ProductBindingModel();
    }

    @GetMapping("/add")
    public String add(){
        return "addProduct";
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
        model.addAttribute("routeToExplore", this.productService.createProductForDetails(id));

        return "product-details";
    }

    @GetMapping("/all")
    public String all() {
        return "productsAll";
    }

}

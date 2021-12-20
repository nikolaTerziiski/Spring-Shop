package bg.springshop.springshop.web;

import bg.springshop.springshop.model.entity.Category;
import bg.springshop.springshop.model.entity.enums.CategoryEnum;
import bg.springshop.springshop.model.view.ProductAllViewModel;
import bg.springshop.springshop.service.CategoryService;
import bg.springshop.springshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final ProductService productService;
    public CategoryController(CategoryService categoryService,
                              ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/{name}")
    public String name(@PathVariable String name, Model model){

        Category category = this.categoryService.containsCategory(name);
        if(category == null){
            return "redirect:/error";
        }

        List<ProductAllViewModel> categoriesToDisplay =  this.productService.getProductsByCategory(category);
        if(categoriesToDisplay.size() == 0){
            model.addAttribute("isEmpty", true);

            return "category-details";
        }
        model.addAttribute("categories", categoriesToDisplay);

        return "category-details";
    }
}

package bg.springshop.springshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @GetMapping("/{name}")
    public String name(@PathVariable String name, Model model){

        model.addAttribute("name", name);
        return "category-details";
    }
}

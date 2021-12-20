package bg.springshop.springshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping("/add/{productId}")
    public String add(@PathVariable Long productId) {

        //TODO add to shoppingCart

        return "index";
    }
}

package bg.springshop.springshop.web;

import bg.springshop.springshop.model.entity.Order;
import bg.springshop.springshop.model.entity.User;
import bg.springshop.springshop.service.OrderService;
import bg.springshop.springshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class OrderController {

    private UserService userService;
    private OrderService orderService;

    public OrderController(UserService userService,
                           OrderService orderService) {

        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/orders/create")
    public String create() {

        User currUser = this.userService.getCurrentLoggedInUser();
        Order order = this.orderService.createOrder(currUser);

        return "successful-order";
    }
    @GetMapping("/orders/finish")
    public String finish() {

        return "order-finish";
    }
}

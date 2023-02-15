package bg.springshop.springshop.web;

import bg.springshop.springshop.model.binding.RegisterProblemBindingModel;
import bg.springshop.springshop.model.binding.RequestLiveCheckBindingModel;
import bg.springshop.springshop.model.binding.UserRegistrationBindingModel;
import bg.springshop.springshop.model.entity.Order;
import bg.springshop.springshop.model.entity.User;
import bg.springshop.springshop.model.view.OrderProfileViewModel;
import bg.springshop.springshop.service.LiveRequestsService;
import bg.springshop.springshop.service.OrderService;
import bg.springshop.springshop.service.ReportService;
import bg.springshop.springshop.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private ReportService reportService;
    private LiveRequestsService liveRequestsService;
    private OrderService orderService;

    public UserController(UserService userService,
                          ReportService reportService,
                          LiveRequestsService liveRequestsService,
                          OrderService orderService) {
        this.userService = userService;
        this.reportService = reportService;
        this.liveRequestsService = liveRequestsService;
        this.orderService = orderService;
    }

    @ModelAttribute
    public UserRegistrationBindingModel userRegistrationBindingModel(){
        return new UserRegistrationBindingModel();
    }

    @ModelAttribute
    public RegisterProblemBindingModel registerProblemBindingModel(){
        return new RegisterProblemBindingModel();
    }

    @ModelAttribute
    public RequestLiveCheckBindingModel requestLiveCheckBindingModel() {return new RequestLiveCheckBindingModel(); }

    @GetMapping("/register")
    public String register(Model model1){
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationBindingModel userRegistrationBindingModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors() || !userRegistrationBindingModel.getPassword()
            .equals(userRegistrationBindingModel.getConfirmPassword())){
            redirectAttributes
                .addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel)
                .addFlashAttribute("usernameExists", false)
                .addFlashAttribute("doesEmailExists", false);

            return "redirect:register";
        }

        boolean doesUsernameExists = userService.userExistsByUsername(userRegistrationBindingModel.getUsername());
        boolean doesEmailExists = userService.userExistsByEmail(userRegistrationBindingModel.getEmail());
        if(doesEmailExists || doesUsernameExists){
            redirectAttributes.addFlashAttribute("usernameExists", doesUsernameExists);
            redirectAttributes.addFlashAttribute("doesEmailExists", doesEmailExists);
            return "redirect:register";
        }

        this.userService.registerUser(userRegistrationBindingModel);
        return "redirect:login";
    }

    @GetMapping("/profile/{name}")
    private String profile(@PathVariable String name, Model model){

        User user = this.userService.getCurrentLoggedInUser();

        boolean doesHaveOrders = true;

        List<OrderProfileViewModel> orders = this.orderService.ordersForUser(user);
        if (orders.size() > 0) {
            doesHaveOrders = false;
        }

        model.addAttribute("doesHaveOrders", doesHaveOrders);
        model.addAttribute("orders", orders);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/contacts")
    private String contacts(Model model2) {
        return "contacts";
    }

    @PostMapping("/contacts")
    private String contacts(@Valid RegisterProblemBindingModel registerProblemBindingModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("hasError", true);
            return "redirect:contacts";
        }
        this.reportService.registerReport(registerProblemBindingModel);
        return "redirect:/";
    }

    @PostMapping("/contacts-live")
    private String contactsLive(@Valid RequestLiveCheckBindingModel requestLiveCheckBindingModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("hasErrorSecond", true);
            return "redirect:contacts";
        }

        this.liveRequestsService.registerLiveRequest(requestLiveCheckBindingModel);
        return "redirect:/";
    }

    @GetMapping("/administrative")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    private String administrative() {
        return "redirect:/";
    }
}

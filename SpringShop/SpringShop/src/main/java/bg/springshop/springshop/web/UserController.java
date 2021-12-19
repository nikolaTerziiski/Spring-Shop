package bg.springshop.springshop.web;

import bg.springshop.springshop.model.binding.UserRegistrationBindingModel;
import bg.springshop.springshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public UserRegistrationBindingModel userRegistrationBindingModel(){
        return new UserRegistrationBindingModel();
    }

    @GetMapping("/register")
    public String register(Model model){
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
}

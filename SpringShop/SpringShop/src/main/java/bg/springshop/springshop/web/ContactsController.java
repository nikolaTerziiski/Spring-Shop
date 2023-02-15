package bg.springshop.springshop.web;

import bg.springshop.springshop.model.binding.RegisterProblemBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacts")
public class ContactsController {

    @ModelAttribute
    public RegisterProblemBindingModel registerProblemBindingModel(){
        return new RegisterProblemBindingModel();
    }
}

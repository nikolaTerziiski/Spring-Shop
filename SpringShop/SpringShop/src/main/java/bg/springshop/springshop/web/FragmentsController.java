package bg.springshop.springshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentsController {

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}

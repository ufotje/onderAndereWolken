package client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping(value = "/logout")
    public String handleLogoutGet(ModelMap model) {
        return "redirect:/home";
    }

}

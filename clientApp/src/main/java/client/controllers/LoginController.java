package client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String handleLoginGet(){
        return "login";
    }

    @PostMapping(value = "/login")
    public String handlePostLogin(){
        return "redirect:/admin";
    }

}

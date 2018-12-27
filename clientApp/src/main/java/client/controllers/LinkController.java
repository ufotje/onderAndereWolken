package client.controllers;

import client.services.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("links")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping
    public String getAllLinks(ModelMap modelMap) {
        modelMap.addAttribute("links", linkService.findAllLinks());
        return "links";
    }

}

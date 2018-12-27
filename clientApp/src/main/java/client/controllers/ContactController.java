package client.controllers;

import client.entities.Contact;
import client.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public String handleGetContactPage() {
        return "contact";
    }



    @RequestMapping(method = RequestMethod.POST)
    public String handlePostContactPage(@Valid @ModelAttribute("contact") Contact contact, BindingResult br, ModelMap modelMap) {
        String message = "oh neeeeeeeee D=";

        if (br.hasErrors()) {
            modelMap.addAttribute("errors", br.getAllErrors());
            return "contact";
        }
        Contact updateSucceed = contactService.addContact(contact);
        if (updateSucceed != null) {
            message = "Berichtje verzonden! <3";

        }
        modelMap.addAttribute("saveTheMessage", message);
        return "contact";
    }

}

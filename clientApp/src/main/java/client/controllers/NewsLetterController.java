package client.controllers;
import client.entities.NewsLetter;
import client.services.NewsLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("newsletter")

public class NewsLetterController {

    @Autowired
    private NewsLetterService newsLetterService;

    @PostMapping(value = "/subscribe")
    public String handleSubscribe(@Valid @ModelAttribute("nieuwsbrief") NewsLetter newsLetter, BindingResult br, RedirectAttributes redirectAttributes){
        NewsLetter doesExist = newsLetterService.findByMail(newsLetter.getMail());
        String message = null;
        if(br.hasErrors()){
            redirectAttributes.addFlashAttribute( "errors", br.getAllErrors());
            return "redirect:/home";
        }

        if (doesExist!=null) {
            newsLetterService.deleteNewsletter(doesExist);
            message = "Email verwijderd";
        }
        else{
            newsLetterService.subscribe(newsLetter);
            message = "Email toegevoegd";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/home";
    }
}
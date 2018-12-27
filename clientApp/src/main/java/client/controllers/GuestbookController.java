package client.controllers;


import client.entities.Guestbook;
import client.services.GuestbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("gastenboek")
public class GuestbookController {

    @Autowired
    private GuestbookService guestbookService;

    // GET PAGE
    @GetMapping
    public String handleGet(@ModelAttribute("guestbookEntry") Guestbook guestbook, ModelMap modelMap) {
        modelMap.addAttribute("gbEntries", guestbookService.findAllGuestbookEntries());
        return "guestbook";
    }

    // POST FORM
    @PostMapping
    public String handlePost(@Valid @ModelAttribute("guestbookEntry") Guestbook guestbook, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes, @RequestParam("winnie") String honeypot) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("showFormWithErrors", "show");
            modelMap.addAttribute("gbEntries", guestbookService.findAllGuestbookEntries());
            return "guestbook";
        } else {
            if (honeypot.length() != 0) {
                return "redirect:/gastenboek";
            } else {
                guestbookService.addGuestbook(guestbook);
                redirectAttributes.addFlashAttribute("formFeedback", "Bericht werd met succes verstuurd!");
                return "redirect:/gastenboek";
            }
        }
    }

    // REMOVE GUESTBOOK ENTRY (OPTION ONLY VISIBLE WHEN LOGGED IN, SEE JSP)
    @GetMapping(value = "/verwijder/{id}")
    public String handleRemoveGuestbookEntry(@PathVariable("id") int id, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            Guestbook guestbook = guestbookService.findById(id);
            if (guestbook != null) {
                guestbookService.deleteGuestbook(guestbook);
                redirectAttributes.addFlashAttribute("deleteSuccess", "Bericht verwijderd.");
            }
        }
        return "redirect:/gastenboek";
    }

}

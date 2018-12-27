package client.controllers;

import client.entities.Comment;
import client.entities.Story;
import client.services.CommentService;
import client.services.StoryService;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by PearlS on 7/11/2017.
 */

@Controller
@RequestMapping("commentaar")
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    StoryService storyService;

    @PostMapping(value = "nieuw")
    public String handlePost(@ModelAttribute("commentaar") Comment comment, ModelMap modelMap, @RequestParam(value = "storyId") int storyId,  RedirectAttributes redirectAttributes) {
        String message = "oh neeeeeeeee D=";
        Story story = storyService.findById(storyId);
        comment.setStory(story);
        comment.setBody(Jsoup.clean(comment.getBody(), Whitelist.simpleText()));
        Comment updateSucceed = commentService.addComment(comment);
        if (updateSucceed != null) {
            message = "Berichtje verzonden! <3";
        }
        redirectAttributes.addFlashAttribute("saveTheMessage", message);
        return "redirect:/verhalen/"+storyId;
    }


}

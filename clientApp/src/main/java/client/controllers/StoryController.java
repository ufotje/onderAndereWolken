package client.controllers;

import client.controllers.services.VisitorSpamPreventable;
import client.entities.Story;
import client.entities.Trip;
import client.services.CommentService;
import client.services.StoryService;
import client.services.TripService;
import client.services.search.LatestEditOrderable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

import static client.controllers.services.Pagination.pagingJsp;
import static client.services.search.SearchUtils.showStoriesByCountry;

@Controller
@RequestMapping("verhalen")
public class StoryController {

    @Autowired
    private StoryService storyService;
    @Autowired
    private TripService tripService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private VisitorSpamPreventable visitorService;

    private int ITEMS_ON_PAGE = 8;

    @GetMapping
    public String handleAllStoriesMainpage(ModelMap model) {
        return handleAllStoriesPages("1", model);
    }

    @GetMapping(params = "pagina")
    public String handleAllStoriesPages(@RequestParam("pagina") String page, ModelMap model) {
        Map<LatestEditOrderable, String> stories = new LinkedHashMap<>();
        storyService.findAllStories().forEach(story -> stories.put(story, "story"));
        pagingJsp(stories, page, ITEMS_ON_PAGE, "/verhalen?pagina=", model);
        return "stories";
    }

    @GetMapping(value = "trip/{tripId}")
    public String getAllStories(ModelMap model, @PathVariable("tripId") String tripID, @RequestParam(value = "pagina", required = false) String page) {
        Trip trip = tripService.findById(Integer.parseInt(tripID));
        model.addAttribute("tripname", trip.getTitle());
        Map<LatestEditOrderable, String> stories = new LinkedHashMap<>();
        storyService.findAllStoriesByTrip(trip).forEach(story -> stories.put(story, "story"));
        pagingJsp(stories, page, ITEMS_ON_PAGE, "/verhalen?pagina=", model);
        return "stories";
    }

    @PostMapping
    public ModelAndView handlePost(@ModelAttribute("storyForm") Story story, Map<String, Object> model) {
        storyService.addStory(story);
        String message = "Pagina succesvol opgeslagen";
        return new ModelAndView("newStory", "saveTheMessage", message);
    }

    @GetMapping(value = "{storyId}")
    public String getStoryForm(@PathVariable("storyId") String id, ModelMap model) {
        Story story = storyService.findById(Integer.valueOf(id));
        model.addAttribute("story", story);
        model.addAttribute("comments", commentService.findAllCommentsByStory(story));
        return "story";
    }

    @PostMapping(value = "/addLike")
    public String addLike(@RequestParam(value = "storyId") int storyId, HttpServletRequest request, HttpServletResponse response) {
        if (!visitorService.hasPerformedAction(request, response, "story" + storyId)) {
            Story story = storyService.findById(storyId);
            story.setLikes(story.getLikes() + 1);
            storyService.updateStory(story.getId(), story);
        }
        return "redirect:/verhalen/" + storyId;
    }

    // REMOVE COMMENT
    @GetMapping(value = "{storyId}/verwijder_commentaar/{commentId}")
    public String removeCommentFromStory(@PathVariable("storyId") String storyId,
                                         @PathVariable("commentId") int commentId,
                                         RedirectAttributes redirectAttributes,
                                         HttpServletRequest request) {
        if (request.getUserPrincipal() == null) {
            redirectAttributes.addFlashAttribute("commentRemoveMessage", null);
        } else {
            commentService.deleteComment(commentService.findOne(commentId));
        }
        return "redirect:/verhalen/" + storyId + "#newcomments";
    }

    @GetMapping(params = {"land"})
    public String showForCountries(@RequestParam("land") String countryName, @RequestParam(value = "pagina", required = false) String page, ModelMap model) {
        Map<LatestEditOrderable, String> storiesForCountry = showStoriesByCountry(tripService, storyService, countryName);
        pagingJsp(storiesForCountry, page, ITEMS_ON_PAGE, "/verhalen?land=" + countryName + "&pagina=", model);
        model.addAttribute("storiesForCountry", countryName);
        return "stories";
    }

}


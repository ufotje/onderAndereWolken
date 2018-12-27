package client.controllers;

import client.services.MediaService;
import client.services.NewsLetterService;
import client.services.search.LatestEditOrderable;
import client.services.StoryService;
import client.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import static client.services.search.SearchUtils.*;
import static client.controllers.services.Pagination.pagingJsp;

@Controller
public class HomeController {

    @Autowired
    private StoryService storyService;
    @Autowired
    private NewsLetterService newsLetterService;
    @Autowired
    private TripService tripService;
    @Autowired
    private MediaService mediaService;
    private int ITEMS_ON_PAGE = 6;


    @GetMapping(value={"/", "/home", "/thuis", "/index", "/start", "/pagina"})
    public String handleGet(ModelMap model) {
        return handleLimitResultsPage("1", model);
    }

    @GetMapping(value = "/pagina/{number}")
    public String handleLimitResultsPage(@PathVariable("number") String page, ModelMap model) {
        Map<LatestEditOrderable, String> searchResults = searchMainPageItemsByTitle(tripService, storyService,mediaService,"");
        pagingJsp(searchResults, page, ITEMS_ON_PAGE, "/pagina/", model);
        return "index";
    }

    @GetMapping(value = "/zoeken", params = "waarde")
    public String handleSearch(@RequestParam("waarde") String searchTerms, ModelMap model) {
        return handleLimitSearch(searchTerms, "1", model);
    }

    @GetMapping(value = "/zoeken", params = {"waarde", "pagina"})
    public String handleLimitSearch(@RequestParam("waarde") String searchTerms, @RequestParam("pagina") String page, ModelMap model) {
        Map<LatestEditOrderable, String> searchResults = searchStoriesTags(storyService, searchMainPageItemsByTitle(tripService, storyService, mediaService, searchTerms), searchTerms);
        pagingJsp(searchResults, page, ITEMS_ON_PAGE, ("/zoeken?waarde=" + searchTerms + "&pagina="), model);
        model.addAttribute("searchTerms", searchTerms);
        return "index";
    }

    @GetMapping(value = "/verhalen", params = "tag")
    public String handleTags(@RequestParam("tag") String tag, ModelMap model) {
        return handleLimitTags(tag, "1", model);
    }

    @GetMapping(value = "/verhalen", params = {"tag", "pagina"})
    public String handleLimitTags(@RequestParam("tag") String tag, @RequestParam("pagina") String page, ModelMap model) {
        Map<LatestEditOrderable, String> searchResults = searchStoriesTags(storyService, new LinkedHashMap<>(), tag);
        pagingJsp(searchResults, page, ITEMS_ON_PAGE, ("/verhalen?tag=" + tag + "&pagina="), model); // Note to self added + page test to see what it does
        return "index";
    }

    @GetMapping(value = "/populair/{number}")
    public String showPopular(@PathVariable("number")String page, ModelMap model){
        Map<LatestEditOrderable, String> searchResults = orderMainPageItemsByPopularity(tripService, storyService, mediaService);
        pagingJsp(searchResults, page, ITEMS_ON_PAGE,"/populair/", model);
        return "index";
    }

}

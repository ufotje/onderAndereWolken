package client.controllers;

import client.controllers.services.VisitorSpamPreventable;
import client.entities.Media;
import client.entities.Story;
import client.services.MediaService;
import client.services.TripService;
import client.services.search.LatestEditOrderable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

import static client.config.GlobalVariables.REGEX_NUMBER;
import static client.controllers.services.Pagination.pagingJsp;
import static client.services.search.SearchUtils.showMediaByCountry;


@Controller
@RequestMapping("kijkjes")
public class MediaController {
    @Autowired
    private MediaService mediaService;
    @Autowired
    private TripService tripService;
    @Autowired
    private VisitorSpamPreventable visitorService;
    private int ITEMS_ON_PAGE = 8;

    @GetMapping
    public String getAllMediaMainPage(ModelMap model) {
        return getAllMediaPages("1", model);
    }

    @GetMapping(params = "pagina")
    public String getAllMediaPages(@RequestParam("pagina") String page, ModelMap model) {
        Map<LatestEditOrderable, String> media = new LinkedHashMap<>();
        mediaService.findAllMedia().forEach(video -> media.put(video, "media"));
        pagingJsp(media, page, ITEMS_ON_PAGE, "/kijkjes?pagina=", model);
        return "media";
    }

    @GetMapping(params = {"land"})
    public String getMediaByCountry(@RequestParam("land") String country, ModelMap model) {
        return showForCountries(country, "1", model);
    }

    @GetMapping(params = {"land", "pagina"})
    public String showForCountries(@RequestParam("land") String countryName, @RequestParam("pagina") String page, ModelMap model) {
        Map<LatestEditOrderable, String> mediaForCountry = showMediaByCountry(mediaService, tripService, countryName);
        pagingJsp(mediaForCountry, page, ITEMS_ON_PAGE, "/kijkjes?land=" + countryName +"&pagina=", model);
        model.addAttribute("mediaForCountry", countryName);
        return "media";
    }

    @PostMapping(value = "/addLike")
    public String addLike(@RequestParam(value = "mediaId") int mediaId, HttpServletRequest request, HttpServletResponse response) {
        if (!visitorService.hasPerformedAction(request, response, "media" + mediaId)) {
            Media media = mediaService.findById(mediaId);
            media.setLikes(media.getLikes() + 1);
            mediaService.updateMedia(media.getId(), media);
        }
        return "redirect:" + request.getHeader("Referer");
    }

}

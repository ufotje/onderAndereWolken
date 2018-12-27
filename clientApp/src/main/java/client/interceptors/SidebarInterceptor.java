package client.interceptors;

import client.entities.Country;
import client.services.CountryService;
import client.services.MediaService;
import client.services.StoryService;
import client.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static client.services.search.SearchUtils.*;

@Controller
public class SidebarInterceptor extends HandlerInterceptorAdapter {
    private static Map<String, Integer> countStoryForcountry = null;
    private static Map<String, Integer> countMediaForcountry = null;
    //private static Map<String, Integer> countAlbumForcountry = new HashMap<>();

    @Autowired
    private CountryService countryService;
    @Autowired
    private TripService tripService;
    @Autowired
    private StoryService storyService;
    @Autowired
    private MediaService mediaService;

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null) {
            List<Country> countries = countryService.findAllCountriesContainingActiveTrips();
            modelAndView.getModelMap().addAttribute("sidebarCountries", countries);

            if(countStoryForcountry == null && countMediaForcountry == null) { // TODO don't forget to add album
                countStoryForcountry = new HashMap<>();
                countMediaForcountry = new HashMap<>();
                //countAlbumForcountry = new HashMap<>();
                for(Country country : countries) {
                    countStoryForcountry.put(country.getName(), showStoriesByCountry(tripService, storyService, country.getName()).size());
                    countMediaForcountry.put(country.getName(), showMediaByCountry(mediaService, tripService, country.getName()).size());
                    //countAlbumForcountry.put(country.getName(), showAlbumsByCountry(tripService, albumService, country.getName()).size());
                    //TODO implement for albums when it's finished
                    // Todo Also think of alternative solution. this isn't functional.
                       // If hashmap is created each time it takes browser to long to load page. If saved in statics like now the info doesn't get updated
                }
            }
            modelAndView.getModelMap().addAttribute("countStoryCountries", countStoryForcountry);
            modelAndView.getModelMap().addAttribute("countMediaCountries", countMediaForcountry);
            //modelAndView.getModelMap().addAttribute("countAlbumCountries", countAlbumsForcountry);
        }
    }
}

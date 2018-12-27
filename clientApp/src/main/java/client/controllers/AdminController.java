package client.controllers;

import client.entities.*;
import client.services.*;
import client.services.implementation.StoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static client.config.GlobalVariables.getRegexNumber;

@Controller
@RequestMapping("admin")
public class AdminController {
    private final String VIEW_STORY_FORM = "admin/storyForm";
    private final String VIEW_TRIP_FORM = "admin/tripForm";
    private final String VIEW_MEDIA_FORM = "admin/mediaForm";
    private final String VIEW_LOCATION_FORM = "admin/locationForm";
    private final String ATTRIBUTE_FORM_FEEDBACK = "statusMsg";
    private AbstractMap.SimpleEntry<Boolean, String> statusMsg;
    private String REGEX_NUMBER = getRegexNumber();
    private String referer = "/admin";
    private final String LOCAL_HOST = "http://localhost:8080/";

    @Autowired
    private TripService tripService;
    @Autowired
    private StoryServiceImpl storyService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private MediaService mediaService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private ContactService contactService;

    // MAIN ADMIN PAGE
    @GetMapping
    public String handleGetMain(ModelMap model) {
        model.addAttribute("trips", tripService.findTripsSortedByLatestEdit(5, true));
        model.addAttribute("stories", storyService.findStoriesSortedByLatestEdit(5, true));
        model.addAttribute("media", mediaService.findMediaSortedByLatestEdit(5, true));
        return "admin/main";
    }

    // ADMIN TRIPS OVERVIEW
    @GetMapping(value = "/trips")
    public String handleGetTrips(ModelMap model) {
        model.addAttribute("trips", tripService.findAllTripsSortedByLatestEdit(true));
        return "admin/trips";
    }

    // ADMIN STORIES OVERVIEW
    @GetMapping(value = "/verhalen")
    public String handleGetStories(ModelMap model) {
        model.addAttribute("stories", storyService.findAllStoriesSortedByLatestEdit(true));
        return "admin/stories";
    }

    // ADMIN LINKEN OVERVIEW
    @GetMapping(value = "/links")
    public String handleGetLink(@ModelAttribute("addLink") Link link, ModelMap modelMap) {
        modelMap.addAttribute("links", linkService.findAllLinks());
        return "admin/links";
    }

    // ADMIN MEDIA OVERVIEW
    @GetMapping(value = "/kijkjes")
    public String handleGetLink(ModelMap modelMap) {
        modelMap.addAttribute("media", mediaService.findAllMediaSortedByLatestEdit(true));
        return "admin/media";
    }

    // SETTINGS
    @GetMapping(value = "/settings")
    public String handleGetSettings() {
        return "settings";
    }

    @PostMapping(value = "/settings")
    public String handlePostSettings() {
        return "redirect:/admin";
    }


    // ADD TRIP
    @GetMapping(value = "/trips/nieuw")
    public String handleGetNewTrip(@ModelAttribute("myTrip") Trip trip, ModelMap model) {
        model.addAttribute("countries", countryService.findAllCountries());
        return VIEW_TRIP_FORM;
    }

    @PostMapping(value = "/trips/nieuw")
    public String handlePostNewTrip(@Valid @ModelAttribute("myTrip") Trip trip, BindingResult br, @RequestParam("tripCountries") String[] selectedCountries, RedirectAttributes redirectAttributes) {
        trip.setCountries(convertStringToCountry(selectedCountries));
        if (br.hasErrors()) {
            return VIEW_TRIP_FORM;
        }
        statusMsg = new SimpleEntry<>(false, "Oeps er is een probleem opgedoken, nieuwe trip is niet toegevoegd");
        String view = VIEW_TRIP_FORM;
        Trip updateSucceed = tripService.addTrip(trip);
        if (updateSucceed != null) {
            view = "redirect:/admin/trips";
            statusMsg = new SimpleEntry<>(true, "Nieuwe trip is succesvol toegevoegd");
        }
        redirectAttributes.addFlashAttribute(ATTRIBUTE_FORM_FEEDBACK, statusMsg);
        return view;
    }

    // EDIT TRIP
    @GetMapping(value = "/trips/bewerk/{id}")
    public String handleGetEditTrip(@ModelAttribute("myTrip") Trip trip, BindingResult br,
                                    @PathVariable("id") String tripIdentifier, ModelMap model,
                                    HttpServletRequest request) {
        if(!br.hasErrors()) referer = request.getHeader("Referer");
        referer = "redirect:/"+referer.replace(LOCAL_HOST,"");
        trip = (tripIdentifier.matches(REGEX_NUMBER)) ? (tripService.findById(Integer.valueOf(tripIdentifier))) : (tripService.findByTitle(tripIdentifier));

        if (trip == null) {
            return "redirect:/admin";
        }

        List<Country> allCountries = new ArrayList<>(countryService.findAllCountries());
        for (Country selected : trip.getCountries()) {
            for (int i = 0; i < allCountries.size(); i++) {
                if (allCountries.get(i).getName().equals(selected.getName())) {
                    allCountries.remove(i);
                }
            }
        }
        model.addAttribute("countries", allCountries);
        model.addAttribute("trip", trip);
        model.addAttribute("stories", storyService.findAllStoriesByTrip(trip));

        return VIEW_TRIP_FORM;
    }

    @PostMapping(value = "/trips/bewerk/{identifier}")
    public String handlePostEditTrip(@Valid @ModelAttribute("myTrip") Trip tripChanges, BindingResult br,
                                     @PathVariable("identifier") String tripIdentifier,
                                     @RequestParam("tripCountries") String[] selectedCountries, RedirectAttributes redirectAttributes) {
        Trip trip = (tripIdentifier.matches(REGEX_NUMBER)) ? (tripService.findById(Integer.valueOf(tripIdentifier))) : (tripService.findByTitle(tripIdentifier));
        if (br.hasErrors()) {
            return VIEW_TRIP_FORM;
        }
        statusMsg = new SimpleEntry<>(false, "Oeps er is een probleem opgedoken, bewerkingen zijn niet doorgevoerd");
        String view = VIEW_TRIP_FORM;
        if (trip != null) {
            trip.setCountries(convertStringToCountry(selectedCountries));
            trip.setTitle(tripChanges.getTitle());
            trip.setStartDate(tripChanges.getStartDate());
            trip.setEndDate(tripChanges.getEndDate());
            Trip updateSucceed = tripService.updateTrip(trip);
            if (updateSucceed != null) {
                view = referer;
                statusMsg = new SimpleEntry<>(true, "Bewerkingen zijn succesvol opgeslagen");
            }
        }
        redirectAttributes.addFlashAttribute(ATTRIBUTE_FORM_FEEDBACK, statusMsg);
        return view;
    }

    @GetMapping(value = "/trips/wijzig_zichtbaarheid/{identifier}")
    public String handleGetInvisibleTrip(@PathVariable("identifier") String tripIdentifier, HttpServletRequest request) {
        referer = request.getHeader("Referer");
        referer = "redirect:/"+referer.replace(LOCAL_HOST,"");
        Trip trip = (tripIdentifier.matches(REGEX_NUMBER)) ? (tripService.findById(Integer.valueOf(tripIdentifier))) : (tripService.findByTitle(tripIdentifier));
        if (trip != null) {
            tripService.toggleVisibility(trip);
        }
        return referer;
    }

    // DELETE TRIP
    @GetMapping(value = "/trips/verwijder/{identifier}")
    public String handleGetDeleteTrip(@PathVariable("identifier") String tripIdentifier, ModelMap model, HttpServletRequest request) {
        referer = request.getHeader("Referer");
        referer = "redirect:/"+referer.replace(LOCAL_HOST,"");
        Trip trip = (tripIdentifier.matches(REGEX_NUMBER)) ? (tripService.findById(Integer.valueOf(tripIdentifier))) : (tripService.findByTitle(tripIdentifier));
        statusMsg = new SimpleEntry<>(false, "Kan trip niet verwijderen");
        if (trip != null) {
            tripService.removeTrip(trip);
            statusMsg = new SimpleEntry<>(true, "Trip successvol verwijderd");
        }
        model.addAttribute(ATTRIBUTE_FORM_FEEDBACK, statusMsg);
        return referer;
    }

    private List<Country> convertStringToCountry(String... selectedCountries) {
        Set<Country> countries = new HashSet<>();
        for (String countryName : selectedCountries) {
            countries.add(countryService.findCountry(countryName));
        }
        countries.remove(null);
        return new ArrayList<>(countries);
    }


    // ADD STORY
    @GetMapping(value = "/verhalen/nieuw")
    public String handleGetNewStory(@ModelAttribute("myStory") Story story, ModelMap modelMap) {
        modelMap.addAttribute("trips", tripService.findAllTrips());
        return VIEW_STORY_FORM;
    }

    @PostMapping(value = "/verhalen/nieuw")
    public String handlePostNewStory(@Valid @ModelAttribute("myStory") Story story, BindingResult br,
                                     ModelMap modelMap, @RequestParam Map<String, String> formParams,
                                     RedirectAttributes redirectAttributes) {
        String view = VIEW_STORY_FORM;
        addFormValuesToStory(story, br, formParams);
        if (br.hasErrors()) {
            addStoryToModel(story, modelMap);
            return view;
        }
        statusMsg = new SimpleEntry<>(false, "Oeps er is een probleem opgedoken, verhaal is niet toegevoegd");
        Story uploadSucceed = storyService.addStory(story);
        if (uploadSucceed != null) {
            view = "redirect:/admin/verhalen";
            statusMsg = new SimpleEntry<>(true, "Nieuw verhaal is succesvol toegevoegd");
        }
        redirectAttributes.addFlashAttribute(ATTRIBUTE_FORM_FEEDBACK, statusMsg);
        return view;
    }

    // EDIT STORY
    @GetMapping(value = "/verhalen/bewerk/{id}")
    public String handleGetEditStory(@ModelAttribute("myStory") Story story, @PathVariable("id") String storyIdentifier,
                                     ModelMap model, HttpServletRequest request) {
        referer = request.getHeader("Referer");
        referer = "redirect:/"+referer.replace(LOCAL_HOST,"");
        story = (storyIdentifier.matches(REGEX_NUMBER)) ? (storyService.findById(Integer.valueOf(storyIdentifier))) : (storyService.findByTitle(storyIdentifier));
        if (story == null) {
            return referer;
        }
        addStoryToModel(story, model);
        return "admin/storyForm";
    }

    @PostMapping(value = "/verhalen/bewerk/{id}")
    public String handlePostEditStory(@Valid @ModelAttribute("myStory") Story story, BindingResult br,
                                      @RequestParam Map<String, String> formParams, @PathVariable("id") String storyId,
                                      ModelMap model, RedirectAttributes redirectAttributes) {
        String view = VIEW_STORY_FORM;
        addFormValuesToStory(story, br, formParams);
        if (br.hasErrors()) {
            addStoryToModel(story, model);
            return view;
        }
        statusMsg = new SimpleEntry<>(false, "Oeps er is een probleem opgedoken, bewerkingen zijn niet doorgevoerd");
        Story updateSucceed = null;
        if (storyId.matches(REGEX_NUMBER)) updateSucceed = storyService.updateStory(Integer.valueOf(storyId), story);
        if (updateSucceed != null) {
            view = "redirect:/admin/verhalen";
            statusMsg = new SimpleEntry<>(true, "Bewerkingen zijn succesvol opgeslagen");
        }
        redirectAttributes.addFlashAttribute(ATTRIBUTE_FORM_FEEDBACK, statusMsg);
        return view;
    }

    @GetMapping(value = "/verhalen/wijzig_zichtbaarheid/{identifier}")
    public String handleGetInvisibleStory(@PathVariable("identifier") String storyIdentifier, HttpServletRequest request) {
        referer = request.getHeader("Referer");
        referer = "redirect:/"+referer.replace(LOCAL_HOST,"");
        Story story = (storyIdentifier.matches(REGEX_NUMBER)) ? (storyService.findById(Integer.valueOf(storyIdentifier))) : (storyService.findByTitle(storyIdentifier));
        if (story != null) storyService.toggleVisibility(story);
        return referer;
    }

    // DELETE STORY
    @GetMapping(value = "/verhalen/verwijder/{identifier}")
    public String handleGetDeleteStory(@PathVariable("identifier") String storyIdentifier, ModelMap model, HttpServletRequest request) {
        referer = request.getHeader("Referer");
        referer = "redirect:/"+referer.replace(LOCAL_HOST,"");
        Story story = (storyIdentifier.matches(REGEX_NUMBER)) ? (storyService.findById(Integer.valueOf(storyIdentifier))) : (storyService.findByTitle(storyIdentifier));
        statusMsg = new SimpleEntry<>(false, "Kan verhaal niet verwijderen");
        if (story != null) {
            storyService.removeStory(story);
            statusMsg = new SimpleEntry<>(true, "Verhaal successfully deleted");
        }
        model.addAttribute(ATTRIBUTE_FORM_FEEDBACK, statusMsg);
        return referer;
    }

    private ModelMap addStoryToModel(Story story, ModelMap model) {
        model.addAttribute("trips", tripService.findTrips(true));
        model.addAttribute("story", story);
        model.addAttribute("tags", storyService.generateTagStringFrom(story.getTags()));
        return model;
    }

    private Story addFormValuesToStory(Story story, BindingResult br, Map<String, String> formParams) {
        if (formParams.containsKey("editor")) {
            String storyText = formParams.get("editor");
            story.setStory(storyText);
            if (storyText != null && storyText.length() < 1)
                br.rejectValue("story", "error.story", "Niet ingevuld.");
        }
        if (formParams.containsKey("teaser")) {
            String teaser = formParams.get("teaser");
            story.setTeaser(storyService.generateValidTeaser(teaser, story.getStory()));
        }
        if (formParams.containsKey("selectedTripId")) {
            String tripId = formParams.get("selectedTripId");
            if (tripId.matches(REGEX_NUMBER)) {
                Trip trip = tripService.findById(Integer.valueOf(tripId));
                if (trip != null) {
                    story.setTrip(trip);
                    if (!trip.isVisible()) story.setVisible(false);
                }
            }
            if (story.getTrip() == null) br.rejectValue("trip", "error.story", "Ongeldige selectie.");
        }
        if (formParams.containsKey("storyTags")) {
            String tags = formParams.get("storyTags");
            story.setTags(storyService.generateTagListFrom(tags));
        }
        return story;
    }

    // ADD PICTURES
    @RequestMapping(value = "/kiekjes", method = RequestMethod.GET)
    public String handleGetNewPicture() {
        return "newPicture";
    }

    @RequestMapping(value = "/kiekjes", method = RequestMethod.POST)
    public String handlePostNewPicture() {
        return "redirect:/admin";
    }


    // ADD MEDIA
    @GetMapping(value = "/kijkjes/nieuw")
    public String handleGetNewMedia(@ModelAttribute("myMedia") Media media, ModelMap modelMap) {
        modelMap.addAttribute("trips", tripService.findAllTrips());
        return VIEW_MEDIA_FORM;
    }

    @PostMapping(value = "/kijkjes/nieuw")
    public String handlePostNewMedia(@Valid @ModelAttribute("myMedia") Media media, BindingResult br,
                                     ModelMap modelMap, @RequestParam Map<String, String> formParams,
                                     RedirectAttributes redirectAttributes) {
        String view = VIEW_MEDIA_FORM;
        addFormValuesToMedia(media, br, formParams);
        if (br.hasErrors()) {
            addMediaToModel(media, modelMap);
            return view;
        }
        statusMsg = new SimpleEntry<>(false, "Oeps er is een probleem opgedoken, kijkje is niet toegevoegd");
        Media mediaSucceed = mediaService.addMedia(media);
        if (mediaSucceed != null) {
            view = "redirect:/admin/kijkjes";
            statusMsg = new SimpleEntry<>(true, "Nieuw kijkje is succesvol toegevoegd");
        }
        redirectAttributes.addFlashAttribute(ATTRIBUTE_FORM_FEEDBACK, statusMsg);
        return view;
    }

    // EDIT MEDIA
    @GetMapping(value = "/kijkjes/bewerk/{id}")
    public String handleGetEditMedia(@ModelAttribute("myMedia") Media media, @PathVariable("id") String mediaIdentifier,
                                     ModelMap model, HttpServletRequest request) {
        referer = request.getHeader("Referer");
        referer = "redirect:/"+referer.replace(LOCAL_HOST,"");
        media = (mediaIdentifier.matches(REGEX_NUMBER)) ? (mediaService.findById(Integer.valueOf(mediaIdentifier))) : (mediaService.findByTitle(mediaIdentifier));
        if (media == null) {
            return referer;
        }
        addMediaToModel(media, model);
        return VIEW_MEDIA_FORM;
    }

    @PostMapping(value = "/kijkjes/bewerk/{id}")
    public String handlePostEditMedia(@Valid @ModelAttribute("myMedia") Media media, BindingResult br,
                                      @RequestParam Map<String, String> formParams, @PathVariable("id") String mediaId,
                                      ModelMap model, RedirectAttributes redirectAttributes) {
        String view = VIEW_MEDIA_FORM;
        addFormValuesToMedia(media, br, formParams);
        if (br.hasErrors()) {
            addMediaToModel(media, model);
            return view;
        }
        statusMsg = new SimpleEntry<>(false, "Oeps er is een probleem opgedoken, bewerkingen zijn niet doorgevoerd");
        Media updateSucceed = null;
        if (mediaId.matches(REGEX_NUMBER)) updateSucceed = mediaService.updateMedia(Integer.valueOf(mediaId), media);
        if (updateSucceed != null) {
            view = "redirect:/admin/kijkjes";
            statusMsg = new SimpleEntry<>(true, "Bewerkingen zijn succesvol opgeslagen");
        }
        redirectAttributes.addFlashAttribute(ATTRIBUTE_FORM_FEEDBACK, statusMsg);
        return view;
    }

    @GetMapping(value = "/kijkjes/wijzig_zichtbaarheid/{identifier}")
    public String handleGetInvisibleMedia(@PathVariable("identifier") String mediaIdentifier, HttpServletRequest request) {
        referer = request.getHeader("Referer");
        referer = "redirect:/"+referer.replace(LOCAL_HOST,"");
        Media media = (mediaIdentifier.matches(REGEX_NUMBER)) ? (mediaService.findById(Integer.valueOf(mediaIdentifier))) : (mediaService.findByTitle(mediaIdentifier));
        if (media != null) mediaService.toggleVisibility(media);
        return referer;
    }

    // DELETE MEDIA
    @GetMapping(value = "/kijkjes/verwijder/{identifier}")
    public String handleGetDeleteMedia(@PathVariable("identifier") String mediaIdentifier, ModelMap model, HttpServletRequest request) {
        referer = request.getHeader("Referer");
        referer = "redirect:/"+referer.replace(LOCAL_HOST,"");
        Media media = (mediaIdentifier.matches(REGEX_NUMBER)) ? (mediaService.findById(Integer.valueOf(mediaIdentifier))) : (mediaService.findByTitle(mediaIdentifier));
        statusMsg = new SimpleEntry<>(false, "Kan kijkje niet verwijderen");
        if (media != null) {
            mediaService.removeMedia(media);
            statusMsg = new SimpleEntry<>(true, "Kijkje successfully deleted");
        }
        model.addAttribute(ATTRIBUTE_FORM_FEEDBACK, statusMsg);
        return referer;
    }

    private ModelMap addMediaToModel(Media media, ModelMap model) {
        model.addAttribute("trips", tripService.findTrips(true));
        model.addAttribute("media", media);
        return model;
    }

    private Media addFormValuesToMedia(Media media, BindingResult br, Map<String, String> formParams) {
        if (formParams.containsKey("selectedTripId")) {
            String tripId = formParams.get("selectedTripId");
            if (tripId.matches(REGEX_NUMBER)) {
                Trip trip = tripService.findById(Integer.valueOf(tripId));
                if (trip != null) {
                    media.setTrip(trip);
                    if (!trip.isVisible()) media.setVisible(false);
                }
            }
            if (media.getTrip() == null) br.rejectValue("trip", "error.story", "Ongeldige selectie.");
        }
        return media;
    }


    // ADD LINK
    @PostMapping(value = "/links/nieuw")
    public String handlePostNewLink(@Valid @ModelAttribute("addLink") Link link, BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelMap modelMap) throws UnsupportedEncodingException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("links", linkService.findAllLinks());
            return "admin/links";
        } else {
            // TODO: 13/11/2017 check for duplicate links + proper link format (not really priority) 
            linkService.addLink(link);
            redirectAttributes.addFlashAttribute("formFeedback", "Link toegevoegd.");
            return "redirect:/admin/links";
        }
    }

    // EDIT LINK
    @GetMapping(value = "/links/bewerk/{id}")
    public String handleEditLink(@PathVariable("id") int linkId, ModelMap modelMap) {
        modelMap.addAttribute("links", linkService.findAllLinks());
        modelMap.addAttribute("addLink", linkService.findById(linkId));
        return "admin/editLink";
    }

    @PostMapping(value = "/links/bewerk/{id}")
    public String handleEditPostLink(@Valid @ModelAttribute("addLink") Link linkUpdate, BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("links", linkService.findAllLinks());
            return "admin/editLink";
        } else {
            Link oldLink = linkService.findById(linkUpdate.getId());
            if (oldLink.equals(linkUpdate)) {
                redirectAttributes.addFlashAttribute("formFeedback", "Geen wijzigingen gevonden.");
                return "redirect:/admin/links";
            } else {
                linkService.updateLink(linkUpdate);
                redirectAttributes.addFlashAttribute("formFeedback", "Link aangepast.");
                return "redirect:/admin/links";
            }
        }
    }

    // DELETE LINK
    @GetMapping(value = "/links/verwijder/{id}")
    public String handleDeleteLink(@PathVariable("id") int linkId, RedirectAttributes redirectAttributes) {
        Link link = linkService.findById(linkId);
        if (link != null) {
            linkService.deleteLink(link);
            redirectAttributes.addFlashAttribute("formFeedback", "Link verwijderd.");
        }
        return "redirect:/admin/links";
    }

    /**
     * This method serves to set the site statistics in the sidebar on admin pages
     */
    //todo need to add statistics in methods on both server and client side ?AOP?
    /*private ModelMap addSiteStatistics(ModelMap model) {
        return model.addAttribute("amountTrips", totalAmountTripsInDb);
    }*/

    // SHOW LOCATIONS

    // ADD LOCATION
    @GetMapping(value = "/locaties/nieuw")
    public String handleGetNewLocation(@ModelAttribute("newLocation") Location location, ModelMap modelMap) {
        modelMap.addAttribute("trips", tripService.findAllTrips());
        return VIEW_LOCATION_FORM;
    }

    @PostMapping(value = "/locaties/nieuw")
    public String handlePostNewLocation(@ModelAttribute("newLocation") Location location,
                                        @RequestParam("selectedTripId") int tripId,
                                        ModelMap modelMap) {
        modelMap.addAttribute("trips", tripService.findAllTrips());
        validateLocation(tripId, location, modelMap);
        if (modelMap.containsAttribute("tripSelectError") || modelMap.containsAttribute("locationError")) {
            return VIEW_LOCATION_FORM;
        } else {
            Trip trip = tripService.findById(tripId);
            location.setTrip(trip);
            locationService.addLocation(location);
            return "redirect:/admin/locaties/nieuw";
        }
    }

    private void validateLocation(int tripId, Location location, ModelMap modelMap) {
        if (tripId == 0) {
            modelMap.addAttribute("tripSelectError", "Ongeldige selectie.");
        } else {
            modelMap.addAttribute("selectedTripId", tripId);
        }
        if (location.getLatitude().length() == 0 || location.getLongitude().length() == 0) {
            modelMap.addAttribute("locationError", "Geen locatie gevonden.");
        }
    }

    //Zie alle contactinfo
    @GetMapping("allecontacten")
    public String seeAllContacts(ModelMap modelMap){
        List<Contact> contacts =contactService.getAllContacts();
        modelMap.addAttribute("contacts", contacts);
        return "admin/contactlist";
    }

    //verwijder contactinfo
    @GetMapping("allecontacten/verwijder/{id}")
    public String handleDeleteContact(@PathVariable("id") int contactId, RedirectAttributes redirectAttributes){
        Contact contact = contactService.getContactById(contactId);
        if (contact != null){
            contactService.deleteContact(contact);
        }
        return "redirect:/admin/allecontacten";
    }

    @GetMapping("allecontacten/lees/{id}")
    public String handleOneContact(@PathVariable("id") int contactId, ModelMap modelMap){
        Contact contact = contactService.getContactById(contactId);
        if (contact != null){
            modelMap.addAttribute("contact", contact);
        }
        return "admin/contactpage";
    }

}

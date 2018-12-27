package be.intec.controllers;

import be.intec.entities.Comment;
import be.intec.entities.Story;
import be.intec.entities.Tag;
import be.intec.entities.Trip;
import be.intec.repositories.CommentRepository;
import be.intec.repositories.StoryRepository;
import be.intec.repositories.TagRepository;
import be.intec.repositories.TripRepository;
import be.intec.utilityClasses.MapKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static be.intec.repositories.implementation.CommonSpecification.*;
import static org.springframework.util.StringUtils.hasText;

@RestController
@RequestMapping("stories")
public class StoryController {

    @Autowired
    TripRepository tripRepository;
    @Autowired
    StoryRepository storyRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    CommentRepository commentRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Story> getAllTripsPossibleParams(@RequestParam Map<String, String> readCriteria){
        return storyRepository.findAll(filterVisibilityAndOptionalWhere(readCriteria, Story.class), limitAndOrSort(readCriteria)).getContent();
    }

    @GetMapping(value= "/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Story getStoryById(@PathVariable("id") int id){
        return storyRepository.findStoryById(id);
    }

    @GetMapping(value = "/trip/{identifier}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Story> handleStoriesByTripname(@PathVariable("identifier") String tripIdentifier, @RequestParam Map<String, String> readCriteria) {
        Trip trip = (tripIdentifier.matches("^\\d+$")) ? (tripRepository.findOne(Integer.valueOf(tripIdentifier))) : (tripRepository.findByTitle(tripIdentifier));
        return storyRepository.findAll(joinSpecifications(filterVisibilityAndOptionalWhere(null, false, Story.class),
                whereFieldEquals("trip", trip, Story.class)),
                limitAndOrSort(readCriteria)).getContent();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addStory(@RequestBody Story story, HttpServletRequest request) {
        if(!turnsInvisibleIfTripInvisible(story))
            return new ResponseEntity("Trip ontbreekt voor verhaal", HttpStatus.EXPECTATION_FAILED);
        story.setLatestEdit(LocalDateTime.now());
        story.setPicUrl(validatePicUrl(request, story.getPicUrl()));
        preventDuplicateTags(story);
        storyRepository.save(story);
        return new ResponseEntity(story, HttpStatus.OK);
    }

    @PutMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateStory (@RequestBody Story story, @PathVariable("id") int id, HttpServletRequest request){
        HttpStatus status = HttpStatus.OK;
        Story doesStoryExist= storyRepository.findStoryById(id);
        if (doesStoryExist!=null) {
            if(!turnsInvisibleIfTripInvisible(story))
                return new ResponseEntity("Trip ontbreekt voor verhaal", HttpStatus.EXPECTATION_FAILED);
            story.setId(id);
            story.setLatestEdit(LocalDateTime.now());
            preventDuplicateTags(story);
            story.setPicUrl(validatePicUrl(request, story.getPicUrl()));
            storyRepository.save(story);

            status = HttpStatus.ACCEPTED;
        }else {
            status = HttpStatus.BAD_REQUEST;
            return  new ResponseEntity("Verhaal bestaat niet",status);
        }
        return  new ResponseEntity(status);
    }

    @PutMapping(value = "/{id}", params = {"toggleVisibility"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateToggleStoryVisibility(@PathVariable("id") int id) {
        Story story = storyRepository.findOne(id);
        if(story != null) {
            story.setVisible(!story.isVisible());
            if(!turnsInvisibleIfTripInvisible(story))
                return new ResponseEntity("Trip ontbreekt voor verhaal", HttpStatus.EXPECTATION_FAILED);
            storyRepository.save(story);
            return new ResponseEntity(story, HttpStatus.OK);
        }
        return new ResponseEntity("Geen story gevonden met id: " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteStory ( @PathVariable("id") int id){
        HttpStatus status;
        Story doesStoryExist= storyRepository.findStoryById(id);
        if (doesStoryExist!=null){
            doesStoryExist.setDeleted(true);
            status = HttpStatus.OK;
            List<Comment> comments= commentRepository.findCommentByStory(doesStoryExist);
            if(comments!= null){
                for (Comment comment:comments){
                    commentRepository.delete(comment);
                }
            }
        }else{
            status = HttpStatus.BAD_REQUEST;
            return  new ResponseEntity("Verhaal bestaat niet",status);
        }
        return  new ResponseEntity(status);
    }

    @GetMapping(params = "search", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<Story> handleSearchResultStoryByTitle(@RequestParam("search") String search, @RequestParam Map<String, String> params) {
        Set<Story> storiesByTitle = new LinkedHashSet<>(storyRepository.findAll(joinSpecifications(searchTitleContainsIgnoreCase(Story.class, search.split(" ")), filterVisibilityAndOptionalWhere(params, Story.class))));
        return limitResults(storiesByTitle, params);
    }

    @GetMapping(params = "searchTag", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<Story> handleSearchResultStoryByTags(@RequestParam("searchTag") String search, @RequestParam Map<String, String> params) {
        Set<Tag> tags = new LinkedHashSet<>();
        if(hasText(search.trim())) Arrays.stream(search.split(" ")).forEach(word -> tags.add(tagRepository.findTagByWord(word)));
        tags.removeAll(Collections.singleton(null));

        LinkedHashMap<Story, MapKey> stories = new LinkedHashMap<>();

        for (Tag tag : tags) {
            List<Story> storiesForTag = storyRepository.findStoriesByTag(tag);
            for (Story story : storiesForTag) {
                if (stories.containsKey(story)) {
                    stories.get(story).occurancesIncrement();
                } else {
                    stories.put(story, new MapKey(story.getId(), story.getLatestEdit(), 0));
                }
            }
        }

        Map<MapKey, Story> storiesSorted = stories.entrySet().stream()
                .sorted(Map.Entry.comparingByValue((Comparator.comparing(MapKey::getOccurances).thenComparing(MapKey::getLatestEdit)).reversed()))
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey, (v1, v2) -> v1, LinkedHashMap::new));
        return limitResults(new LinkedHashSet<>(storiesSorted.values()), params);
    }

    private Set<Story> limitResults(Set<Story> stories,  Map<String, String> pageableParams) {
        int size = (pageableParams.containsKey("size") && pageableParams.get("size").matches("^\\d+$")) ? Integer.valueOf(pageableParams.get("size")) : Integer.MAX_VALUE;
        int page = (pageableParams.containsKey("page") && pageableParams.get("page").matches("^\\d+$") && Integer.valueOf(pageableParams.get("page")) > 0) ? Integer.valueOf(pageableParams.get("page")) : 0;
        return stories.stream().skip(Math.min(stories.size(), page * size)).limit(Math.min(stories.size(), (++page) * size)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @GetMapping(value = "popular", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Story> getByMostPopular(){
        return storyRepository.findAllByVisibleAndDeletedOrderByLikesDesc(true, false);
    }

    private String validatePicUrl(HttpServletRequest request, String PicUrl) {
        return (hasText(PicUrl)) ? PicUrl : (request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath()) + "/images/defaultStoryBackgr.jpg");
    }

    private Story preventDuplicateTags(Story story) {
        for(int i = 0; i < story.getTags().size(); i++) {
            Tag tagExists = tagRepository.findTagByWord(story.getTags().get(i).getWord());
            if(tagExists != null) {
                List<Tag> tags = story.getTags();
                tags.set(i, tagExists);
                story.setTags(tags);
            }
        }
        return story;
    }

    private boolean turnsInvisibleIfTripInvisible(Story story) {
        Trip trip = tripRepository.findOne(story.getTrip().getId());
        if(trip == null) return false;
        if(!trip.isVisible()) story.setVisible(false);
        return true;
    }

}
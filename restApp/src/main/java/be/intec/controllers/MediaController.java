package be.intec.controllers;

import be.intec.entities.Media;
import be.intec.entities.Trip;
import be.intec.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static be.intec.repositories.implementation.CommonSpecification.*;

@RestController
@RequestMapping("media")
public class MediaController {

    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private TripRepository tripRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Media> handleAllMediaPossibleParams(@RequestParam Map<String, String> readCriteria){
        return mediaRepository.findAll(filterVisibilityAndOptionalWhere(readCriteria, Media.class), limitAndOrSort(readCriteria)).getContent();
    }

    @GetMapping(value= "/{Identifier}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Media handleMediaById(@PathVariable("Identifier") String mediaIdentifier){
        return (mediaIdentifier.matches("^\\d+$")) ? (mediaRepository.findOne(Integer.valueOf(mediaIdentifier)))
                : (mediaRepository.findMediaByTitle(mediaIdentifier));
    }

    @GetMapping(value= "/trip/{Identifier}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Media> handleMediaByTrip(@PathVariable("Identifier") String tripIdentifier, @RequestParam Map<String, String> readCriteria){
        System.out.println("Hello wereld en al zijn bewoners");
        Trip trip = (tripIdentifier.matches("^\\d+$")) ? (tripRepository.findOne(Integer.valueOf(tripIdentifier)))
                : (tripRepository.findByTitle(tripIdentifier));
        System.out.println(tripIdentifier);
        return mediaRepository.findAll(joinSpecifications(filterVisibilityAndOptionalWhere(null, false, Media.class),
                                                          whereFieldEquals("trip", trip, Media.class)),
                                                          limitAndOrSort(readCriteria)).getContent();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addMedia(@RequestBody Media media) {
        media.setLatestEdit(LocalDateTime.now());
        mediaRepository.save(media);
        return new ResponseEntity(media, HttpStatus.OK);
    }

    @PutMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateMedia (@RequestBody Media media, @PathVariable("id") int id){
        HttpStatus status = HttpStatus.OK;
        boolean mediaExists = mediaRepository.exists(id);
        if(mediaExists) {
            media.setId(id);
            media.setLatestEdit(LocalDateTime.now());
            mediaRepository.save(media);
            status = HttpStatus.ACCEPTED;
        } else {
            status = HttpStatus.BAD_REQUEST;
            return  new ResponseEntity("Geen media gevonden voor id: ", status);
        }
        return  new ResponseEntity(status);
    }

    @PutMapping(value = "/{id}", params = {"toggleVisibility"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateToggleMediaVisibility(@PathVariable("id") int id) {
        Media media = mediaRepository.findOne(id);
        if(media != null) {
            media.setVisible(!media.isVisible());
            mediaRepository.save(media);
            return new ResponseEntity(media, HttpStatus.OK);
        }
        return new ResponseEntity("Geen media gevonden voor id: " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteMedia ( @PathVariable("id") int id){
        HttpStatus status = HttpStatus.OK;;
        Media media = mediaRepository.findOne(id);
        if (media!=null){
            media.setDeleted(true);
            mediaRepository.save(media);
        }else{
            status = HttpStatus.BAD_REQUEST;
            return  new ResponseEntity("Geen media gevonden voor id: " + id, status);
        }
        return  new ResponseEntity(status);
    }

    @GetMapping(params = "search", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<Media> handleSearchResultMediaByTitle(@RequestParam("search") String search, @RequestParam Map<String, String> params) {
        Set<Media> mediaByTitle = new LinkedHashSet<>(mediaRepository.findAll(joinSpecifications(searchTitleContainsIgnoreCase(Media.class, search.split(" ")), filterVisibilityAndOptionalWhere(params, Media.class))));
        return limitResults(mediaByTitle, params);
    }

    private Set<Media> limitResults(Set<Media> media,  Map<String, String> pageableParams) {
        int size = (pageableParams.containsKey("size") && pageableParams.get("size").matches("^\\d+$")) ? Integer.valueOf(pageableParams.get("size")) : Integer.MAX_VALUE;
        int page = (pageableParams.containsKey("page") && pageableParams.get("page").matches("^\\d+$") && Integer.valueOf(pageableParams.get("page")) > 0) ? Integer.valueOf(pageableParams.get("page")) : 0;
        return media.stream().skip(Math.min(media.size(), page * size)).limit(Math.min(media.size(), (++page) * size)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @GetMapping(value = "popular", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Media> getByMostPopular(){
        return mediaRepository.findAllByVisibleAndDeletedOrderByLikesDesc(true, false);
    }

}

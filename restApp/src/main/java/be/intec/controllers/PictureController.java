package be.intec.controllers;

import be.intec.entities.Picture;
import be.intec.repositories.PictureRepository;
import be.intec.repositories.comparators.SortById;
import be.intec.services.SavePicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("pictures")
public class PictureController {

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    SavePicturesService savePicturesService;

    @Autowired
    SortById sortById;

    @GetMapping
    public List<Picture> handleGet() {
        return pictureRepository.findAll();
    }

    @PostMapping
    @CrossOrigin
    public String handlePost(@RequestPart(name = "photos[]", required = false) MultipartFile[] files) {
        savePicturesService.saveAllPictures(files);
        return "Saved";
    }

    @GetMapping("/{id}")
    public Picture handleGetPictureById(@PathVariable("id") int id) {
        return pictureRepository.findPictureById(id);
    }

    @GetMapping("/latest")
    public Picture handleGetLatestPicture(){

        List<Picture> pictures = pictureRepository.findAll();
        pictures.sort(sortById);
        Picture picture = null;

        try{
            picture = pictures.get(0);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }

        return picture;
    }


}

package be.intec.services;

import be.intec.entities.Picture;
import be.intec.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class SavePicturesService {

    @Autowired
    PictureRepository pictureRepository;

    @Value("${resource.server.socket}")
    private String resourceSocket;

    public void saveAllPictures(MultipartFile[] files) {
        for (MultipartFile file : files) {
            savePicture(file, "c://pictures/", resourceSocket+"resource_pictures/");
        }
    }

    public void savePicture(MultipartFile file, String fileSystemLocation, String applicationDirectory) {
        savePictureInDatabase(applicationDirectory, file);
        savePictureFileOnFilesystem(fileSystemLocation, file);
    }

    public Picture savePictureInDatabase(String applicationResourceDirectory, MultipartFile file) {

        String location = applicationResourceDirectory + file.getOriginalFilename();
        Picture picture = new Picture(location, null);
        pictureRepository.save(picture);

        return picture;
    }

    public void savePictureFileOnFilesystem(String fileSystemLocation, MultipartFile file) {
        try {
            Path location = Paths.get(fileSystemLocation, file.getOriginalFilename());
            Files.createDirectories(location.getParent());
            Files.createFile(location);
            Files.write(location, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

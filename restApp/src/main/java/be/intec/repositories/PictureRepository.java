package be.intec.repositories;

import be.intec.entities.Album;
import be.intec.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture,Integer>{

    @Transactional
    Picture findPictureById(int id);

    @Transactional
    List<Picture> findPictureByAlbum(Album album);

}

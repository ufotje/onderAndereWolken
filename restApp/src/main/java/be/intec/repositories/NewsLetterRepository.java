package be.intec.repositories;
import be.intec.entities.NewsLetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NewsLetterRepository extends JpaRepository<NewsLetter,Integer>{

    @Transactional
    public NewsLetter findNewsLetterByMail(String mail);

}
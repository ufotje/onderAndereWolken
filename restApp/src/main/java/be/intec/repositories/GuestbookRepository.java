package be.intec.repositories;

import be.intec.entities.Guestbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GuestbookRepository extends JpaRepository<Guestbook, Integer> {

    @Transactional
    @Query(value = "SELECT g FROM Guestbook g ORDER BY g.postDate DESC")
    List<Guestbook> findAll();

}

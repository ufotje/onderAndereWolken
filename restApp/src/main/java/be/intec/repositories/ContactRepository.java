package be.intec.repositories;

import be.intec.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by PearlS on 13/11/2017.
 */
public interface ContactRepository extends JpaRepository<Contact, Integer> {
}

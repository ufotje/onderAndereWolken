package be.intec.repositories;

import be.intec.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.transaction.Transactional;

public interface CountryRepository extends JpaRepository<Country,Integer>, JpaSpecificationExecutor<Country>{

    @Transactional
    Country findCountryByName(String name);
}



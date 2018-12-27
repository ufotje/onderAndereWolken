package client.services;

import client.entities.Country;

import java.util.List;

public interface CountryService {
    Country findCountry(String name);
    List<Country> findAllCountries();
    List<Country> findAllCountriesContainingActiveTrips();
}

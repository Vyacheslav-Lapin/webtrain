package lab.service;

import java.util.List;

import lab.model.Country;

import org.springframework.transaction.annotation.Propagation;

public interface CountryService {

    List<Country> getAllCountriesInsideTransaction(Propagation propagation);

    List<Country> getAllCountriesRequired();

    List<Country> getAllCountriesRequiresNew();

    List<Country> getAllCountriesSupports();

    List<Country> getAllCountriesNever();

    List<Country> getAllCountriesMandatory();

    List<Country> getAllCountriesNotSupported();

    List<Country> getAllCountries();
}

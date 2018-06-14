package lab.service;

import lab.dao.CountryDao;
import lab.model.Country;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

//@Repository is more convenient declaration for such a class than general @Service
@Repository
@FieldDefaults(level = PRIVATE)
public class CountryServiceImpl implements CountryService {

	CountryDao countryDao;

	public List<Country> getAllCountriesInsideTransaction(
			Propagation propagation) {
		if (Propagation.REQUIRED.equals(propagation)) {
			return getAllCountriesRequired();
		} else if (Propagation.REQUIRES_NEW.equals(propagation)) {
			return getAllCountriesRequiresNew();
		} else if (Propagation.SUPPORTS.equals(propagation)) {
			return getAllCountriesSupports();
		} else if (Propagation.NEVER.equals(propagation)) {
			return getAllCountriesNever();
		} else if (Propagation.MANDATORY.equals(propagation)) {
			return getAllCountriesMandatory();
		} else if (Propagation.NOT_SUPPORTED.equals(propagation)) {
			return getAllCountriesNotSupported();
		} else {
			return getAllCountries();
		}
	}

	public List<Country> getAllCountriesRequired() {
		return countryDao.findAll().collect(Collectors.toList());
	}

	public List<Country> getAllCountriesRequiresNew() {
		return countryDao.findAll().collect(Collectors.toList());
	}

	public List<Country> getAllCountriesSupports() {
		return countryDao.findAll().collect(Collectors.toList());
	}

	public List<Country> getAllCountriesNever() {
		return countryDao.findAll().collect(Collectors.toList());
	}

	public List<Country> getAllCountriesMandatory() {
		return countryDao.findAll().collect(Collectors.toList());
	}

	public List<Country> getAllCountriesNotSupported() {
		return countryDao.findAll().collect(Collectors.toList());
	}

	public List<Country> getAllCountries() {
		return countryDao.findAll().collect(Collectors.toList());
	}

	public CountryDao getCountryDao() {
		return countryDao;
	}

	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

}
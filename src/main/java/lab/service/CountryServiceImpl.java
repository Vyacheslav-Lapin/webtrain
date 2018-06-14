package lab.service;

import lab.dao.CountryDao;
import lab.model.Country;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.transaction.annotation.Propagation.*;

@RequiredArgsConstructor
//@Repository is more convenient declaration for such a class than general @Service
@Service("countryService")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CountryServiceImpl implements CountryService {

	CountryDao countryDao;

	public List<Country> getAllCountriesInsideTransaction(
			Propagation propagation) {
		if (REQUIRED.equals(propagation)) {
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

	@Transactional(readOnly = true, propagation = REQUIRED)
	public List<Country> getAllCountriesRequired() {
		return countryDao.findAll().collect(Collectors.toList());
	}

	@Transactional(readOnly = true, propagation = REQUIRES_NEW)
	public List<Country> getAllCountriesRequiresNew() {
		return countryDao.findAll().collect(Collectors.toList());
	}

	@Transactional(readOnly = true, propagation = SUPPORTS)
	public List<Country> getAllCountriesSupports() {
		return countryDao.findAll().collect(Collectors.toList());
	}

	@Transactional(readOnly = true, propagation = NEVER)
	public List<Country> getAllCountriesNever() {
		return countryDao.findAll().collect(Collectors.toList());
	}

	@Transactional(readOnly = true, propagation = MANDATORY)
	public List<Country> getAllCountriesMandatory() {
		return countryDao.findAll().collect(Collectors.toList());
	}

	@Transactional(readOnly = true, propagation = NOT_SUPPORTED)
	public List<Country> getAllCountriesNotSupported() {
		return countryDao.findAll().collect(Collectors.toList());
	}

//	@Transactional(readOnly = true, propagation = NOT_SUPPORTED)
	public List<Country> getAllCountries() {
		return countryDao.findAll().collect(Collectors.toList());
	}

}
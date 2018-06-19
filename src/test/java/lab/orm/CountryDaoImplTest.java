package lab.orm;

import lab.JavaConfig;
import lab.dao.jpa.CountryJpaDaoImpl;
import lab.model.Country;
import lab.model.SimpleCountry;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIn.in;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Illustrates basic use of Hibernate as a JPA provider.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CountryDaoImplTest {

    CountryJpaDaoImpl countryJpaDao;

    @Test
    @DisplayName("test save country")
    void testSaveCountry() {
        // given
        val exampleCountry = new SimpleCountry("Australia", "AU");
        long initialSize = countryJpaDao.findAll().count();

        // when
        countryJpaDao.save(exampleCountry);

        //then
        List<Country> countryList = countryJpaDao.findAll()
                .collect(Collectors.toList());

        assertThat(((long) countryList.size()), is(initialSize + 1));
        assertThat(exampleCountry, is(in(countryList)));
    }

    @Test
    void testGetAllCountries() {
        // given
        long initialSize = countryJpaDao.findAll().count();

        // when
        countryJpaDao.save(new SimpleCountry("Canada", "CA"));

        // then
        assertThat(countryJpaDao.findAll().count(), is(initialSize + 1));
    }

    @Test
    void testGetCountryByName() {
        // given
        val exampleCountry = new SimpleCountry("Australia", "AU");
        countryJpaDao.save(exampleCountry);

        // when
        Optional<Country> country = countryJpaDao.get("Australia");

        //then
        assertTrue(country.isPresent());
        assertThat(exampleCountry, is(country.get()));
    }

}

package lab.jdbc;

import io.vavr.Tuple;
import lab.JavaConfig;
import lab.dao.JdbcCountryDao;
import lab.model.Country;
import lab.model.SimpleCountry;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lab.dao.JdbcCountryDao.COUNTRY_INIT_DATA;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@FieldDefaults(level = PRIVATE, makeFinal = true)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class JdbcTest {

    JdbcCountryDao jdbcCountryDao;

    @NonFinal
    List<Country> expectedCountryList;

    @NonFinal
    List<Country> expectedCountryListStartsWithA;

    @NonFinal
    Country countryWithChangedName;

    @BeforeEach
    void setUp() {

        expectedCountryList = IntStream.range(0, COUNTRY_INIT_DATA.length)
                .mapToObj(i -> Tuple.of(i + 1, COUNTRY_INIT_DATA[i][0], COUNTRY_INIT_DATA[i][1]))
                .map(issTuple -> new SimpleCountry(issTuple._1, issTuple._2, issTuple._3))
                .collect(Collectors.toList());

        expectedCountryListStartsWithA = expectedCountryList.stream()
                .filter(country -> country.getName().startsWith("A"))
                .collect(Collectors.toList());

        countryWithChangedName = new SimpleCountry(8, "Russia", "RU");

        jdbcCountryDao.loadCountries();
    }

    @Test
    @DirtiesContext
    void testCountryList() {
        List<Country> countryList = jdbcCountryDao.getCountryList();
        assertNotNull(countryList);
        assertEquals(expectedCountryList.size(), countryList.size());
        for (int i = 0; i < expectedCountryList.size(); i++)
            assertEquals(expectedCountryList.get(i), countryList.get(i));
    }

    @Test
    @DirtiesContext
    void testCountryListStartsWithA() {
        List<Country> countryList = jdbcCountryDao.getCountryListStartWith("A");
        assertNotNull(countryList);
        assertEquals(expectedCountryListStartsWithA.size(), countryList.size());
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++)
            assertEquals(expectedCountryListStartsWithA.get(i), countryList.get(i));
    }

    @Test
    @DirtiesContext
    void testCountryChange() {
        jdbcCountryDao.updateCountryName("RU", "Russia");
        assertThat(jdbcCountryDao.getCountryByCodeName("RU"), is(countryWithChangedName));
    }

}
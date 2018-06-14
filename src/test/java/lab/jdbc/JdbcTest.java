package lab.jdbc;

import io.vavr.Tuple;
import lab.JavaConfig;
import lab.dao.CountryDao;
import lab.model.Country;
import lab.model.SimpleCountry;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@FieldDefaults(level = PRIVATE, makeFinal = true)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class JdbcTest {

    static String[][] COUNTRY_INIT_DATA = {
            {"Australia", "AU"},
            {"Canada", "CA"},
            {"France", "FR"},
            {"Hong Kong", "HK"},
            {"Iceland", "IC"},
            {"Japan", "JP"},
            {"Nepal", "NP"},
            {"Russian Federation", "RU"},
            {"Sweden", "SE"},
            {"Switzerland", "CH"},
            {"United Kingdom", "GB"},
            {"United States", "US"}};

    static List<Country> EXPECTED_COUNTRY_LIST =
            IntStream.range(0, COUNTRY_INIT_DATA.length)
                    .mapToObj(i -> Tuple.of(i + 1, COUNTRY_INIT_DATA[i][0], COUNTRY_INIT_DATA[i][1]))
                    .map(issTuple -> new SimpleCountry(issTuple._1, issTuple._2, issTuple._3))
                    .collect(Collectors.toList());

    static List<Country> EXPECTED_COUNTRY_LIST_STARTS_WITH_A =
            EXPECTED_COUNTRY_LIST.stream()
                    .filter(country -> country.getName().startsWith("A"))
                    .collect(Collectors.toList());

    static Country COUNTRY_WITH_CHANGED_NAME =
            new SimpleCountry(8, "Russia", "RU");

    CountryDao jdbcCountryDao;

    @BeforeEach
    void setUp() {
        for (String[] countryData : COUNTRY_INIT_DATA)
            jdbcCountryDao.save(countryData[0], countryData[1]);
    }

    @AfterEach
    void tearDown() {
        jdbcCountryDao.clear();
    }

    @Test
    @DirtiesContext
    @DisplayName("findAll method works correctly")
    void testGetAll() {
        //when
        Stream<Country> countries = jdbcCountryDao.findAll();

        // then
        assertTrue(countries.allMatch(EXPECTED_COUNTRY_LIST::contains));
    }

    @Test
    @DirtiesContext
    @DisplayName("getCountriesStartsWith method works correctly")
    void testGetCountriesStartsWith() {
        // when
        Stream<Country> countries = jdbcCountryDao.getCountriesStartsWith("A");

        // then
//        assertEquals(EXPECTED_COUNTRY_LIST_STARTS_WITH_A.size(), countries.count());
        assertTrue(countries.allMatch(EXPECTED_COUNTRY_LIST_STARTS_WITH_A::contains));
    }

    @Test
    @DirtiesContext
    void testCountryChange() {
        jdbcCountryDao.updateNameByCodeName("RU", "Russia");
        assertThat(jdbcCountryDao.getByCodeName("RU"), is(COUNTRY_WITH_CHANGED_NAME));
    }

}
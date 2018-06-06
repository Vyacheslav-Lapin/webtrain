package lab.dao;

import lab.model.Country;
import lab.model.SimpleCountry;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Repository
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CountryDao extends NamedParameterJdbcDaoSupport {

    public static final String[][] COUNTRY_INIT_DATA = {
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

    static String LOAD_COUNTRIES_SQL = "insert into country (name, code_name) values ('%s', '%s')";
    static String GET_ALL_COUNTRIES_SQL = "select * from country";
    static String GET_COUNTRIES_BY_NAME_SQL = "select * from country where name like :name";
    static String GET_COUNTRY_BY_NAME_SQL = "select * from country where name = '%s'";
    static String GET_COUNTRY_BY_CODE_NAME_SQL = "select * from country where code_name = '%s'";
    static String UPDATE_COUNTRY_NAME_SQL = "update country SET name='%s' where code_name='%s'";

    static RowMapper<Country> COUNTRY_ROW_MAPPER = (rs, __) ->
            new SimpleCountry(
                    rs.getInt("id"),
                    rs.getString("name"),
                    null // TODO: insert it with "code_name"
            );

    public CountryDao(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public List<Country> getCountryList() {
        // TODO: implement it
        return null;
    }

    public List<Country> getCountryListStartWith(String name) {
        return getNamedParameterJdbcTemplate().query(
                GET_COUNTRIES_BY_NAME_SQL,
                new MapSqlParameterSource(
                        "name",
                        name + "%"),
                COUNTRY_ROW_MAPPER);
    }

    public void updateCountryName(String codeName, String newCountryName) {
        // TODO: implement it
    }

    public void loadCountries() {
        for (String[] countryData : COUNTRY_INIT_DATA)
            getJdbcTemplate().execute(
                    String.format(LOAD_COUNTRIES_SQL,
                            countryData[0],
                            countryData[1]));
    }

    public Country getCountryByCodeName(String codeName) {
        return getJdbcTemplate().query(
                String.format(GET_COUNTRY_BY_CODE_NAME_SQL, codeName),
                COUNTRY_ROW_MAPPER)
                .get(0);
    }

    public Country getCountryByName(String name) {
        List<Country> countryList = getJdbcTemplate().query(
                String.format(GET_COUNTRY_BY_NAME_SQL, name),
                COUNTRY_ROW_MAPPER);

        if (countryList.isEmpty())
            throw new CountryNotFoundException();

        return countryList.get(0);
    }
}

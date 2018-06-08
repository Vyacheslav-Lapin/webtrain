package lab.dao;

import lab.model.Country;
import lab.model.SimpleCountry;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static lab.commons.Java9.mapOf;
import static lombok.AccessLevel.PRIVATE;

@Repository
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class JdbcCountryDao extends NamedParameterJdbcDaoSupport {

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

    static String LOAD_COUNTRIES_SQL = "insert into country (name, code_name) values (:name, :codeName)";
    static String GET_ALL_COUNTRIES_SQL = "select * from country";
    static String GET_COUNTRIES_BY_NAME_SQL = "select * from country where name like :name";
    static String GET_COUNTRY_BY_NAME_SQL = "select * from country where name = :name";
    static String GET_COUNTRY_BY_CODE_NAME_SQL = "select * from country where code_name = :codeName";
    static String UPDATE_COUNTRY_NAME_SQL = "update country SET name=:name where code_name=:code_name";

    static RowMapper<Country> COUNTRY_ROW_MAPPER = (rs, __) ->
            new SimpleCountry(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("code_name")
            );

    public JdbcCountryDao(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public List<Country> getCountryList() {
        return getNamedParameterJdbcTemplate().query(
                GET_ALL_COUNTRIES_SQL,
                COUNTRY_ROW_MAPPER);
    }

    public List<Country> getCountryListStartWith(String name) {
        return getNamedParameterJdbcTemplate().query(
                GET_COUNTRIES_BY_NAME_SQL,
                mapOf("name", name + "%"),
                COUNTRY_ROW_MAPPER);
    }

    public void updateCountryName(String codeName, String newCountryName) {
        getNamedParameterJdbcTemplate().update(UPDATE_COUNTRY_NAME_SQL,
                mapOf("code_name", codeName,
                        "name", newCountryName));
    }

    public void loadCountries() {
        for (String[] countryData : COUNTRY_INIT_DATA)
            getNamedParameterJdbcTemplate().update(LOAD_COUNTRIES_SQL,
                    mapOf("name", countryData[0],
                            "codeName", countryData[1]));
    }

    public Country getCountryByCodeName(String codeName) {
        return getNamedParameterJdbcTemplate()
                .queryForObject(GET_COUNTRY_BY_CODE_NAME_SQL,
                        mapOf("codeName", codeName),
                        COUNTRY_ROW_MAPPER);
    }

    public Country getCountryByName(String name) {
        List<Country> countryList = getNamedParameterJdbcTemplate().query(
                GET_COUNTRY_BY_NAME_SQL,
                mapOf("name", name),
                COUNTRY_ROW_MAPPER);

        if (countryList.isEmpty())
            throw new CountryNotFoundException();

        return countryList.get(0);
    }
}

package lab.dao.jdbc;

import lab.dao.CountryDao;
import lab.model.Country;
import lab.model.CountryImpl;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.stream.Stream;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static java.util.Objects.requireNonNull;
import static lab.commons.Java9BackPort.mapOf;
import static lombok.AccessLevel.PRIVATE;

@Repository
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class JdbcCountryDao extends NamedParameterJdbcDaoSupport implements CountryDao {

    static String INSERT_SQL = "insert into country (name, code_name) values (?, ?)";
    static String GET_ALL_SQL = "select * from country";
    static String GET_BY_NAME_LIKE_SQL = "select * from country where name like :name";
    static String GET_BY_NAME_SQL = "select * from country where name = :name";
    static String GET_BY_CODE_NAME_SQL = "select * from country where code_name = :codeName";
    static String UPDATE_NAME_SQL = "update country set name=:name where code_name=:codeName";
    static String UPDATE_SQL = "update country set name=:name, code_name=:codeName where id=:id";
    static String DELETE_SQL = "delete from country where id = :id";
    static String DELETE_ALL_SQL = "delete from country";

    static String ID_FIELD = "id";
    static String NAME_FIELD = "name";
    static String CODE_NAME_FIELD = "codeName";

    static RowMapper<Country> COUNTRY_ROW_MAPPER = (rs, __) ->
            new CountryImpl(
                    rs.getInt(ID_FIELD),
                    rs.getString(NAME_FIELD),
                    rs.getString("code_name")
            );

    public JdbcCountryDao(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @NotNull
    @Override
    public Country save(@NotNull Country country) {
        return country.setId(
                save(country.getName(), country.getCodeName()));
    }

    @Override
    public long save(String name, String codeName) {
        val keyHolder = new GeneratedKeyHolder();
        requireNonNull(getJdbcTemplate())
                .update(connection -> {
                            val ps = connection.prepareStatement(
                                    INSERT_SQL, RETURN_GENERATED_KEYS);
                            ps.setString(1, name);
                            ps.setString(2, codeName);
                            return ps;
                        },
                        keyHolder);
        return requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Stream<Country> findAll() {
        return requireNonNull(getNamedParameterJdbcTemplate())
                .query(GET_ALL_SQL, COUNTRY_ROW_MAPPER)
                .stream();
    }

    @Override
    public Stream<Country> getCountriesStartsWith(String name) {
        return requireNonNull(getNamedParameterJdbcTemplate())
                .query(GET_BY_NAME_LIKE_SQL,
                        mapOf(NAME_FIELD, name + "%"),
                        COUNTRY_ROW_MAPPER)
                .stream();
    }

    @Override
    public void updateNameByCodeName(@NotNull String codeName, @NotNull String newName) {
        requireNonNull(getNamedParameterJdbcTemplate())
                .update(UPDATE_NAME_SQL,
                        mapOf(CODE_NAME_FIELD, codeName,
                                NAME_FIELD, newName));
    }

    @Override
    public Optional<Country> getByCodeName(@NotNull String codeName) {
        return Optional.ofNullable(
                requireNonNull(getNamedParameterJdbcTemplate())
                        .queryForObject(GET_BY_CODE_NAME_SQL,
                                mapOf(CODE_NAME_FIELD, codeName),
                                COUNTRY_ROW_MAPPER));
    }

    @Override
    public Optional<Country> get(@NotNull String name) {
        return Optional.ofNullable(
                requireNonNull(getNamedParameterJdbcTemplate())
                        .queryForObject(GET_BY_NAME_SQL,
                                mapOf(NAME_FIELD, name),
                                COUNTRY_ROW_MAPPER));
    }

    @Override
    public void update(@NotNull Country country) {
        requireNonNull(getNamedParameterJdbcTemplate())
                .update(UPDATE_SQL,
                        mapOf(ID_FIELD, country.getId(),
                                NAME_FIELD, country.getName(),
                                CODE_NAME_FIELD, country.getCodeName()));
    }

    @Override
    public void delete(@NotNull Country country) {
        requireNonNull(getNamedParameterJdbcTemplate())
                .update(DELETE_SQL,
                        mapOf(ID_FIELD, country.getId()));
    }

    @Override
    public void clear() {
        requireNonNull(getJdbcTemplate())
                .update(DELETE_ALL_SQL);
    }
}

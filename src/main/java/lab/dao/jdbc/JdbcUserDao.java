package lab.dao.jdbc;

import lab.aop.Loggable;
import lab.dao.UserDao;
import lab.model.User;
import lab.model.UserImpl;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static lab.commons.Java9BackPort.mapOf;
import static lombok.AccessLevel.PRIVATE;

@Repository
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class JdbcUserDao extends NamedParameterJdbcDaoSupport implements UserDao, JdbcDao {

    static String INSERT_SQL = "insert into \"user\" (firstname, lastname) values (?, ?)";
    static String UPDATE_SQL = "update \"user\" set firstName = :firstName, lastName = :lastName where id = :id";
    static String DELETE_SQL = "delete from \"user\" where id = :id";
    static String DELETE_ALL_SQL = "delete from \"user\"";
    static String GET_ALL_SQL = "select id, firstname, lastname from \"user\"";
    static String GET_BY_ID_SQL = "select id, firstname, lastname from \"user\" where id = :id";

    static String ID_FIELD = "id";
    static String FIRST_NAME_FIELD = "firstName";
    static String LAST_NAME_FIELD = "lastName";

    static RowMapper<User> ROW_MAPPER = (rs, __) ->
            new UserImpl()
                    .setId(rs.getInt(ID_FIELD))
                    .setFirstName(rs.getString(FIRST_NAME_FIELD))
                    .setLastName(rs.getString(LAST_NAME_FIELD));

    public JdbcUserDao(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @NotNull
    @Override
    @Loggable
    public User save(@NotNull User user) {
        return user.setId(insertWithGettingId(INSERT_SQL, ps -> {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
        }));
    }

    @Loggable
    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(getNamedParameterJdbcTemplate())
                .map(npjt -> npjt.queryForObject(GET_BY_ID_SQL, mapOf(ID_FIELD, id), ROW_MAPPER));
    }

    @Override
    public Stream<User> findAll() {
        return Objects.requireNonNull(getJdbcTemplate())
                .query(GET_ALL_SQL, ROW_MAPPER).stream();
    }

    public void delete(@NotNull User entity) {
        Objects.requireNonNull(getNamedParameterJdbcTemplate())
                .update(DELETE_SQL, mapOf(ID_FIELD, entity.getId()));
    }

    @Override
    public void update(@NotNull User user) {
        Objects.requireNonNull(getNamedParameterJdbcTemplate())
                .update(UPDATE_SQL, mapOf(
                        ID_FIELD, user.getId(),
                        FIRST_NAME_FIELD, user.getFirstName(),
                        LAST_NAME_FIELD, user.getLastName()));
    }

    @Override
    public void clear() {
        requireNonNull(getJdbcTemplate())
                .update(DELETE_ALL_SQL);
    }
}

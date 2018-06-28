package lab.commons;

import lab.commons.CheckedConsumer;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static java.util.Objects.requireNonNull;

public interface JdbcDao {

    @Nullable
    JdbcTemplate getJdbcTemplate();

    @Nullable
    NamedParameterJdbcTemplate getNamedParameterJdbcTemplate();

    default long insertWithGettingId(@NotNull String sql,
                                     @NotNull CheckedConsumer<PreparedStatement> stmtPreparer) {
        val keyHolder = new GeneratedKeyHolder();
        requireNonNull(getJdbcTemplate()).update(connection -> {
            val ps = connection.prepareStatement(sql, RETURN_GENERATED_KEYS);
            stmtPreparer.unchecked().accept(ps);
            return ps;
        }, keyHolder);
        return requireNonNull(keyHolder.getKey()).longValue();
    }
}

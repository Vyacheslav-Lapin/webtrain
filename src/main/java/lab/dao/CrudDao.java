package lab.dao;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Stream;

public interface CrudDao<T extends Identifiable> {

    <S extends T> S save(@NotNull S entity);

    Optional<T> findById(long id);

    Stream<T> findAll();

    default long count() {
        return findAll().count();
    }

    void delete(@NotNull T entity);

    void update(@NotNull T entity);

    default boolean existsById(long id) {
        return findAll().anyMatch(entity -> entity.getId() == id);
    }
}

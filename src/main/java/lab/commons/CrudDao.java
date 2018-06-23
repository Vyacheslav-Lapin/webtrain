package lab.commons;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Stream;

public interface CrudDao<T extends Identifiable<T>> {

    @NotNull
    <S extends T> S save(@NotNull S entity);

    Stream<T> findAll();

    void delete(@NotNull T entity);

    void update(@NotNull T entity);

    default void clear() {
        findAll().forEach(this::delete);
    }

    default Optional<T> findById(long id) {
        return findAll().filter(t -> t.getId() == id).findAny();
    }

    default long count() {
        return findAll().count();
    }

    default boolean existsById(long id) {
        return findAll().anyMatch(entity -> entity.getId() == id);
    }
}

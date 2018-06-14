package lab.dao;

import lab.model.Country;
import lab.model.SimpleCountry;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Stream;

public interface CountryDao extends CrudDao<Country> {

    default long save(@NotNull String name, @NotNull String codeName) {
        val country = new SimpleCountry(name, codeName);
        save(country);
        return country.getId();
    }

    @Override
    default Optional<Country> findById(long id) {
        return findAll()
                .filter(country -> country.getId() == id)
                .findAny();
    }

    default Optional<Country> get(@NotNull String name) {
        return findAll()
                .filter(country -> country.getName().equals(name))
                .findAny();
    }

    default Optional<Country> getByCodeName(@NotNull String codeName) {
        return findAll()
                .filter(country -> country.getCodeName().equals(codeName))
                .findAny();
    }

    default void clear() {
        findAll().forEach(this::delete);
    }

    default Stream<Country> getCountriesStartsWith(@NotNull String a) {
        return findAll()
                .filter(country -> country.getName().startsWith(a));
    }

    default void updateNameByCodeName(@NotNull String codeName, @NotNull String newName) {
        update(
                getByCodeName(codeName)
                        .orElseThrow(CountryNotFoundException::new)
                        .setName(newName));
    }
}
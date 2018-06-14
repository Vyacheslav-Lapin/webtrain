package lab.dao.jpa;

import lab.model.Country;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository("countryJpaDao")
public class CountryJpaDaoImpl extends AbstractJpaDao {

    @Override
    public void save(@NotNull Country country) {
        withEntityManager(entityManager -> entityManager.merge(country));
    }

    @Override
    public Stream<Country> findAll() {
        return mapEntityManager(entityManager ->
                entityManager.createQuery(
                        "select c from SimpleCountry c",
                        Country.class)
                        .getResultList())
                .stream();
    }

    @Override
    public Optional<Country> get(@NotNull String name) {
        return mapEntityManager(entityManager ->
                Optional.ofNullable(
                        entityManager.createQuery(
                                "select c from SimpleCountry c where c.name = :name",
                                Country.class)
                                .setParameter("name", name)
                                .getSingleResult())
        );
    }

    @Override
    public void update(@NotNull Country country) {
        withEntityManager(entityManager ->
                entityManager.refresh(country)
        );
    }

    @Override
    public void delete(@NotNull Country country) {
        withEntityManager(entityManager ->
                entityManager.remove(country)
        );
    }

    @Override
    public Optional<Country> findById(long id) {
        return mapEntityManager(entityManager ->
                Optional.ofNullable(
                        entityManager.find(Country.class, id))
        );
    }

    @Override
    public void clear() {
        withEntityManager(entityManager ->
                entityManager.createQuery("delete from SimpleCountry")
                        .executeUpdate()
        );
    }
}

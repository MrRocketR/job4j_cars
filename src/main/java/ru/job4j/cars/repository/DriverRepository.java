package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Driver;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class DriverRepository {

    private final CrudRepository crudRepository;

    public void create(Driver driver) {
        crudRepository.run(session -> session.persist(driver));
    }

    public void update(Driver driver, int id) {
        crudRepository.run("UPDATE Driver as d SET  d.name = :fName"
                        + " WHERE  d.id = :fId",
                Map.of("fId", id,
                        "fName", driver.getName())
        );
    }

    public void deleteDriver(int id) {
        crudRepository.run("DELETE Driver as d where d.id = :fId",
                Map.of("fId", id));
    }

    public Optional<Driver> findDriverById(int id) {
        return crudRepository.optional("from Driver as d where id = :fId", Driver.class,
                Map.of("fId", id));
    }




}

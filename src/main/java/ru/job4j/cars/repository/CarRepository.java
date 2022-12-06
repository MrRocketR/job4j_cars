package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarRepository {

    private final CrudRepository crudRepository;

    public void createCar(Car car) {
        crudRepository.run(session -> session.persist(car));
    }

    public void update(Car car, int id) {
        crudRepository.run("UPDATE Car as c SET  c.name = :fName,"
                        + " c.engine_id = :fEngine WHERE  c.id = :fId",
                Map.of("fId", id,
                        "fEngine", car.getEngine().getId(),
                        "fName", car.getName())
        );
    }

    public void delete(int id) {
        crudRepository.run("DELETE Car as c where c.id = :fId",
                Map.of("fId", id));
    }


    public Optional<Car> getCarById(int id) {
        return crudRepository.optional("from Car where id = :fId", Car.class,
                Map.of("fId", id));

    }

    public List<Car> showCars() {
        return crudRepository.query("from Car", Car.class);
    }

    public List<Car> showCarsByEngine(Engine engine) {
        return crudRepository.query("from Car c where c.engine_id = :fEngine", Car.class,
                Map.of("fId", engine.getId()));
    }

    public List<Driver> showDrivers(int id) {
        Optional<Car> carOptional = getCarById(id);
        return carOptional.get().getDrivers();
    }
}

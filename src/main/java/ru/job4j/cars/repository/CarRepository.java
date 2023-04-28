package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

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
                        + "c.engine = :fEngine, c.transmission = :fTransmission, "
                        + " c.body = :fBody WHERE  c.id = :fId",
                Map.of("fId", id,
                        "fName", car.getName(),
                        "fEngine", car.getEngine(),
                        "fTransmission", car.getTransmission(),
                        "fBody", car.getBody()));

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

}

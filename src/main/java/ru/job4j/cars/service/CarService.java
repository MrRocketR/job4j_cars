package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.CarRepository;

import java.util.List;
import java.util.Optional;
@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void createCar(Car car) {
        carRepository.createCar(car);
    }

    public void updateCar(Car car, int id) {
        carRepository.update(car, id);
    }

    public void deleteCar(int id) {
        carRepository.delete(id);
    }


    public Optional<Car> getCarById(int id) {
        return carRepository.getCarById(id);

    }

}

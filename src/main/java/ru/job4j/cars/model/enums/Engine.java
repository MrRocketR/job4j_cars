package ru.job4j.cars.model.enums;

public enum Engine {
    Gasoline("Бензин"),
    Diesel("Дизель"),
    Hybrid("Гибрид");

    private String engine;


    Engine(String engine) {
        this.engine = engine;
    }

    public String getEngine() {
        return engine;
    }
}

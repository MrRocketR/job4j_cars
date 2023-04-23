package ru.job4j.cars.model;

public enum Transmission {
    Manual("Механическая"),
    Automatic("Автоматическая"),
    Variable("Вариатор");

    private String transmission;

    Transmission(String transmission) {
        this.transmission = transmission;
    }

    public String getTransmission() {
        return transmission;
    }
}

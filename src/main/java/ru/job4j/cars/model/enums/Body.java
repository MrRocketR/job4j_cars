package ru.job4j.cars.model.enums;

public enum Body {
    Hatchback("Хэтчбэк"),
    Minivan("Минивэн"),
    Cabriolet("Кабриолет"),
    Sedan("Седан");

    private String body;

    Body(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

}

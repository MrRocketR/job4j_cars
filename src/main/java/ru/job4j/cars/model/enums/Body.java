package ru.job4j.cars.model.enums;

import java.util.HashMap;
import java.util.Map;

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

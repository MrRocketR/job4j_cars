package ru.job4j.cars.model.enums;

import java.util.HashMap;
import java.util.Map;
public class EnumMapper {

    private Map<String, Body> bodies = new HashMap<>();
    private Map<String, Engine> engines = new HashMap<>();
    private Map<String, Transmission> transmissions  = new HashMap<>();

    public EnumMapper() {
        bodies.put(Body.Hatchback.getBody(), Body.Hatchback);
        bodies.put(Body.Minivan.getBody(), Body.Minivan);
        bodies.put(Body.Cabriolet.getBody(), Body.Cabriolet);
        bodies.put(Body.Sedan.getBody(), Body.Sedan);

        engines.put(Engine.Gasoline.getEngine(), Engine.Gasoline);
        engines.put(Engine.Diesel.getEngine(), Engine.Diesel);
        engines.put(Engine.Hybrid.getEngine(), Engine.Hybrid);

        transmissions.put(Transmission.Manual.getTransmission(), Transmission.Manual);
        transmissions.put(Transmission.Automatic.getTransmission(), Transmission.Automatic);
        transmissions.put(Transmission.Variable.getTransmission(), Transmission.Variable);
    }


    public Body getBody(String body) {
        return bodies.get(body);
    }

    public Engine getEngine(String engine) {
        return engines.get(engine);
    }

    public Transmission getTransmission(String transmission) {
        return transmissions.get(transmission);
    }
}

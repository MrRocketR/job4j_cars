package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.EngineRepository;

import java.util.List;
import java.util.Optional;
@Service
public class EngineService {

    private final EngineRepository engineRepository;

    public EngineService(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    public  void create(Engine engine) {
       engineRepository.create(engine);
    }
    public List<Engine> showEngines() {
        return engineRepository.showEngines();
    }

    public Engine getEngineByName(String engine) {
        return engineRepository.findByEngineName(engine).get();
    }
    public Optional<Engine> findById(int engineId) {
        return engineRepository.findById(engineId);
    }
}

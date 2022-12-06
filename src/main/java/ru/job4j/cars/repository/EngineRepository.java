package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.List;

@Repository
@AllArgsConstructor
public class EngineRepository {

    private final CrudRepository crudRepository;

    public  void create(Engine engine) {
        crudRepository.run(session -> session.persist(engine));
    }
    public List<Engine> showEngines() {
        return crudRepository.query("from Engines", Engine.class);
    }
}

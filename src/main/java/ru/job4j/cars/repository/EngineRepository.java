package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class EngineRepository {

    private final CrudRepository crudRepository;

    public  void create(Engine engine) {
        crudRepository.run(session -> session.save(engine));
    }
    public List<Engine> showEngines() {
        return crudRepository.query("from Engine", Engine.class);
    }


    public Optional<Engine> findByEngineName(String engine) {
        return crudRepository.optional(
                "from Engine where name = :fName", Engine.class,
                Map.of("fName", engine)
        );
    }


    public Optional<Engine> findById(int engineId) {
        return crudRepository.optional(
                "from Engine where id = :fId", Engine.class,
                Map.of("fId", engineId)
        );
    }
}

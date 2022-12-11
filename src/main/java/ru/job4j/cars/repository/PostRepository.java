package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class PostRepository {

    private final CrudRepository crudRepository;

    public List<Post> showAllPosts() {
        return crudRepository.query("from Post", Post.class);
    }

    public void createPost(Post post) {
        crudRepository.run(session -> session.persist(post));
    }

    public Optional<Post> findById(int id) {
        return crudRepository.optional("from Post where id = :fId",
                Post.class, Map.of("fId", id));
    }

    public void delete(int id) {
        crudRepository.run("DELETE Post as p where p.id = :fId",
                Map.of("fId", id));
    }


    public List<Post> showNewPosts() {
        return crudRepository.query("SELECT p FROM Post p WHERE p.created between current_date and current_date + 1", Post.class);
    }

    public List<Post> showWithPhoto() {
        return crudRepository.query("From Post as p where p.photo != 0", Post.class);
    }

    public List<Post> showCarByName(Car car) {
        return crudRepository.query("From Post as p where p.car_id = :fId",
                Post.class, Map.of("fId", car.getId()));
    }
}


package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class PostRepository {

    private final CrudRepository crudRepository;

    public List<Post> showAllPosts() {
        return crudRepository.query("SELECT p FROM Post p left join fetch p.car c left join fetch p.user u", Post.class);

    }


    public void createPost(Post post) {
        post.setStatus(false);
        post.setCreated(LocalDateTime.now());
        crudRepository.run(session -> session.persist(post));
    }

    public void updatePost(Post post, int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("fId", id);
        map.put("fDescription", post.getDescription());
        map.put("fPrice", post.getPrice());
        map.put("fPhoto", post.getPhoto());
        map.put("fStatus", post.isStatus());
        crudRepository.run("UPDATE Post as p SET  p.description = :fDescription,"
                        + "p.price = :fPrice, p.photo = :fPhoto, p.status = :fStatus "
                        + "WHERE p.id = :fId",
                map);
    }


    public Optional<Post> findById(int id) {
        return crudRepository.optional("select p from Post p left join fetch p.car c left join fetch p.user u where p.id = :fId",
                Post.class, Map.of("fId", id));
    }


    public void delete(int id) {
        crudRepository.run("DELETE Post as p where p.id = :fId",
                Map.of("fId", id));
    }


    public List<Post> showNewPostsByTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dayBefore = LocalDateTime.now().minusDays(1);
        return crudRepository.query("FROM Post AS p WHERE p.created BETWEEN :fDayBefore AND :fNow", Post.class,
                Map.of("fNow", now, "fDayBefore", dayBefore));
    }


    public List<Post> showWithPhoto() {
        return crudRepository.query("From Post as p where p.photo != 0", Post.class);
    }

    public List<Post> showCarByName(Car car) {
        return crudRepository.query("From Post as p where p.car_id = :fId",
                Post.class, Map.of("fId", car.getId()));
    }
}


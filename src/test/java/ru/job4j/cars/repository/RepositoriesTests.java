package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class RepositoriesTests {

    private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(REGISTRY)
            .buildMetadata().buildSessionFactory();

    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final PostRepository postRepository = new PostRepository(crudRepository);
    private final UserRepository userRepository = new UserRepository(crudRepository);
    private final CarRepository carRepository = new CarRepository(crudRepository);
    private final EngineRepository engineRepository = new EngineRepository(crudRepository);


    @AfterEach
    public void wipeTable() {
        Session session = sf.openSession();
        session.beginTransaction();
        System.out.println("Cleaning tables!");
        session.createNativeQuery("TRUNCATE TABLE auto_user RESTART IDENTITY CASCADE;");
        session.createNativeQuery("TRUNCATE TABLE auto_post RESTART IDENTITY CASCADE;");
        session.getTransaction().commit();
        System.out.println("Cleaning done!");
    }


    @Test
    public void createUserAndFindByIdAndLogin() {
        User user = new User();
        user.setLogin("userTest");
        user.setPassword("userTest");
        userRepository.create(user);
        User userDbById = userRepository.findById(user.getId()).get();
        User userDbByLogin = userRepository.findByLogin("userTest").get();
        Assertions.assertEquals(user.getLogin(), userDbById.getLogin());
        Assertions.assertEquals(user.getLogin(), userDbByLogin.getLogin());


    }


    @Test
    public void createUserAndListOfUsers() {
        User user = new User();
        user.setLogin("userTest");
        user.setPassword("userTest");
        userRepository.create(user);
        User user2 = new User();
        user.setLogin("userTest2");
        user.setPassword("userTest2");
        userRepository.create(user2);
        List<User> users = userRepository.getUsersList();
        List<User> expectedList = List.of(user, user2);
        Assertions.assertEquals(expectedList, users);
    }

    @Test
    public void postCreateAndFindById() {
        User user = new User();
        user.setLogin("userTest");
        user.setPassword("userTest");
        Post post = new Post();
        post.setCreated(LocalDateTime.now());
        post.setDescription("Tested");
        userRepository.create(user);
        post.setUser(user);
        postRepository.createPost(post);
        Post postInDB = postRepository.findById(post.getId()).get();
        Assertions.assertEquals(post.getDescription(), postInDB.getDescription());
    }

    @Test
    public void deletePost() {
        User user = new User();
        user.setLogin("userTest");
        user.setPassword("userTest");
        Post post = new Post();
        post.setCreated(LocalDateTime.now());
        post.setDescription("Tested");
        userRepository.create(user);
        post.setUser(user);
        postRepository.createPost(post);
        postRepository.delete(post.getId());
        Optional<Post> postInDB = postRepository.findById(post.getId());
        Assertions.assertTrue(postInDB.isEmpty());
    }

    @Test
    public void showPosts() {
        User user = new User();
        user.setLogin("userTest");
        user.setPassword("userTest");
        userRepository.create(user);
        Post post = new Post();
        post.setCreated(LocalDateTime.now());
        post.setDescription("Tested");
        post.setUser(user);
        Post post2 = new Post();
        post2.setCreated(LocalDateTime.now());
        post2.setDescription("Post");
        postRepository.createPost(post);
        postRepository.createPost(post2);
        List<Post> posts = postRepository.showAllPosts();
        List<Post> expectedList = List.of(post, post2);
        Assertions.assertEquals(expectedList, posts);
    }

    @Test
    public void showWithPhoto() {
        User user = new User();
        user.setLogin("userTest");
        user.setPassword("userTest");
        userRepository.create(user);
        Post post1 = new Post();
        post1.setCreated(LocalDateTime.now());
        post1.setDescription("Tested");
        post1.setUser(user);
        post1.setPhoto(new byte[2]);
        Post post2 = new Post();
        post2.setCreated(LocalDateTime.now());
        post2.setDescription("Post");
        Post post3 = new Post();
        post3.setCreated(LocalDateTime.now());
        post3.setDescription("Post3");
        post3.setPhoto(new byte[2]);
        postRepository.createPost(post1);
        postRepository.createPost(post2);
        postRepository.createPost(post3);
        List<Post> withPhotoDb = postRepository.showWithPhoto();
        List<Post> expected = List.of(post1, post3);
        Assertions.assertEquals(expected, withPhotoDb);
    }

    @Test
    public void showNewPosts() {
        User user = new User();
        user.setLogin("userTest");
        user.setPassword("userTest");
        userRepository.create(user);
        Post post1 = new Post();
        post1.setCreated(LocalDateTime.now().minusDays(10));
        post1.setDescription("Tested");
        post1.setUser(user);
        Post post2 = new Post();
        post2.setCreated(LocalDateTime.now());
        post2.setDescription("Post");
        Post post3 = new Post();
        post3.setCreated(LocalDateTime.now().minusDays(2));
        post3.setDescription("Post3");
        postRepository.createPost(post1);
        postRepository.createPost(post2);
        postRepository.createPost(post3);
        List<Post> actualPosts  = postRepository.showNewPosts();
        List<Post> expected = List.of(post2);
        Assertions.assertEquals(expected, actualPosts);
    }

}
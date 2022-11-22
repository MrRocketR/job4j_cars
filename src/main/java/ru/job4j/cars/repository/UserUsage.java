package ru.job4j.cars.repository;

import lombok.var;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.User;

import java.util.Optional;

public class UserUsage {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            var userRepository = new UserRepository(sf);
            var user = new User();
            user.setLogin("admin2");
            user.setPassword("admin2");
            userRepository.create(user);
            user.setPassword("qwerty");
            userRepository.update(user);
            userRepository.findAllOrderById()
                    .forEach(System.out::println);
            userRepository.delete(user.getId());
            userRepository.findByLikeLogin("ov").forEach(System.out::println);
            userRepository.findById(2).ifPresent(System.out::println);
            userRepository.findByLogin("Petrov").ifPresent(System.out::println);
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}

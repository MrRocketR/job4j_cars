package ru.job4j.cars.repository;

import lombok.var;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.User;

public class UserUsage {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            var userRepository = new UserRepository(sf);
            var user = new User();
            user.setLogin("admin");
            user.setPassword("admin");
            Session session = sf.openSession();
            userRepository.create(user, session);
            user.setPassword("qwerty");
            userRepository.update(user, session);
            userRepository.findAllOrderById(session)
                    .forEach(System.out::println);
            userRepository.delete(user.getId(), session);
            userRepository.findByLikeLogin("ov", session).forEach(System.out::println);
            userRepository.findById(2, session).ifPresent(System.out::println);
            userRepository.findByLogin("Petrov", session).ifPresent(System.out::println);
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}

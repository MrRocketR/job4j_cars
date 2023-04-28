package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import ru.job4j.cars.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<User> create(User user) {
        return userRepository.create(user);
    }

    public void update(int id, User user) {
        userRepository.update(id, user);
    }


    public void delete(int userId) {
        userRepository.delete(userId);
    }


    public Optional<User> findByLoginAndPassword(String login, String password) {
        return userRepository.findUserByLoginAndPassword(login, password);
    }

    public Optional<User> findById(int userId) {
        return userRepository.findById(userId);
    }

    public List<Post> findUserPosts(int userId) {
       return findById(userId).get().getPostList();
    }

}

package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class UserRepository {
    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе.
     *
     * @param user пользователь.
     * @return пользователь с id.
     */


    public Optional<User> create(User user) {
        Optional<User> optional;
        try {
            crudRepository.run(session -> session.save(user));
            optional = Optional.of(user);
        } catch (Exception e) {
            optional = Optional.empty();
        }
        return optional;
    }


    /**
     * Обновить в базе пользователя.
     *
     * @param user пользователь.
     */
    public void update(int id, User user) {
        crudRepository.run("UPDATE User as u SET  u.login = :fLogin,"
                        + " u.password = :fPassword"
                        + "WHERE  u.id = :fId",
                Map.of("fId", id,
                        "fLogin", user.getLogin(),
                        "fPassword", user.getPassword())
        );
    }


    /**
     * Удалить пользователя по id.
     *
     * @param userId ID
     */
    public void delete(int userId) {
        crudRepository.run(
                "delete from User where id = :fId",
                Map.of("fId", userId)
        );
    }

    /***
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */

    public List<User> findAllOrderById() {
        return crudRepository.query("from User as u order by u.id asc", User.class);
    }

    /***
     * Найти пользователя по ID
     * @return пользователь.
     */

    public Optional<User> findById(int userId) {
        return crudRepository.optional(
                "from User where id = :fId", User.class,
                Map.of("fId", userId)
        );
    }

    /**
     * Список пользователей по login LIKE %key%
     *
     * @param key key
     * @return список пользователей.
     */

    public List<User> findByLikeLogin(String key) {
        return crudRepository.query(
                "from User where login like :fKey", User.class,
                Map.of("fKey", "%" + key + "%")
        );
    }

    /***
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */

    public Optional<User> findByLogin(String login) {
        return crudRepository.optional(
                "from User where login = :fLogin", User.class,
                Map.of("fLogin", login)
        );
    }

    public List<User> getUsersList() {
        List<User> list = crudRepository.query("From User", User.class);
        return list;
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        return crudRepository.optional(
                "from User where login = :fLogin and password = :fPassword", User.class,
                Map.of("fLogin", login,
                        "fPassword", password)
        );
    }
}

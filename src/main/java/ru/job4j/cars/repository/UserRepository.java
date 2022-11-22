package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {

    private final SessionFactory sf;
    private static final String UPDATE_HQL = "UPDATE User as u  SET u.login = :fLogin, "
            + "u.password = :fPass  WHERE u.id = :fId";

    private static final String DELETE_HQL = "DELETE User where id = :fId";
    private static final String ORDER_BY_ID_HQL = "from User as u order by u.id asc";
    private static final String FIND_BY_ID = "from User as u where u.id = :fId";
    private static final String FIND_LIKE_KEY = "from User as u where u.login like :fPattern";

    private static final String FIND_BY_LOGIN = "from User as u where u.login = :fLogin";

    /**
     * Сохранить в базе.
     *
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return user;
    }


    /**
     * Обновить в базе пользователя.
     *
     * @param user пользователь.
     */
    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(UPDATE_HQL)
                    .setParameter("fLogin", user.getLogin())
                    .setParameter("fPass", user.getPassword())
                    .setParameter("fId", user.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }


    /**
     * Удалить пользователя по id.
     *
     * @param userId ID
     */
    public void delete(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(DELETE_HQL)
                    .setParameter("fId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }


    /**
     * Список пользователь отсортированных по id.
     *
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        List<User> list = session.createQuery(ORDER_BY_ID_HQL, User.class).list();
        return list;
    }

    /**
     * Найти пользователя по ID
     *
     * @return пользователь.
     */
    public Optional<User> findById(int userId) {
        Session session = sf.openSession();
        Query<User> query = session.createQuery(
                FIND_BY_ID, User.class);
        query.setParameter("fId", userId);
        Optional<User> user = Optional.ofNullable(query.uniqueResult());
        return user;
    }

    /**
     * Список пользователей по login LIKE %key%
     *
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        Query<User> query = session.createQuery(
                FIND_LIKE_KEY, User.class);
        List<User> list = query.setParameter("fPattern", "%" + key + "%").list();
        return list;

    }

    /**
     * Найти пользователя по login.
     *
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        Query<User> query = session.createQuery(
                FIND_BY_LOGIN, User.class);
        query.setParameter("fLogin", login);
        Optional<User> user = Optional.ofNullable(query.uniqueResult());
        return user;
    }
}

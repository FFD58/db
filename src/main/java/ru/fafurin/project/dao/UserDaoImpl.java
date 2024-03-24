package ru.fafurin.project.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.fafurin.project.hyber.HibernateSessionFactoryUtil;
import ru.fafurin.project.models.User;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private final Session session;

    public UserDaoImpl() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }

    public User findFyId(long id) {
        User user;
        user = session.get(User.class, id);
        session.close();
        return user;
    }

    public List<User> findAll() {
        return session.createQuery("from User", User.class).getResultList();
    }

    public void save(User user) {
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
    }

    public void update(User user) {
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
        session.close();
    }

    public void delete(User user) {
        Transaction transaction = session.beginTransaction();
        session.remove(user);
        transaction.commit();
        session.close();
    }

}

package ru.fafurin.project.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.Closeable;

public class EntityService implements Closeable {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public EntityService() {
        entityManagerFactory = Persistence.createEntityManagerFactory("project");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public Object findById(Class<?> objectClass, int id) {
        return entityManager.find(objectClass, id);
    }

    public void save(Object object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }

    public void update(Object object) {
        entityManager.getTransaction().begin();
        entityManager.merge(object);
        entityManager.getTransaction().commit();
    }

    public void delete(Object object) {
        entityManager.getTransaction().begin();
        entityManager.remove(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}

package com.aizatgaz.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public abstract class AbstractDAO<T> {
    private final Class<T> clazz;
    private SessionFactory sessionFactory;

    public AbstractDAO(final Class<T> clazzToSet, SessionFactory sessionFactory) {
        this.clazz = clazzToSet;
        this.sessionFactory = sessionFactory;
    }

    public T getById(int id) {
        return sessionFactory.openSession().get(clazz, (byte) id);
    }

    public List<T> getAll() {
        Query<T> query = sessionFactory.openSession().createQuery("from " + clazz.getName(), clazz);
        return query.getResultList();
    }

    public void removeById(int id) {
        try (Session session = sessionFactory.openSession()) {
            T t = session.get(clazz, id);
            Transaction transaction = session.beginTransaction();
            session.remove(t);
            transaction.commit();
        }
    }

    public void remove(T t) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(t);
            transaction.commit();
        }
    }

    public void save(T t) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(t);
            transaction.commit();
        }
    }

    public void update(T t) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(t);
            transaction.commit();
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

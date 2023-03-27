package com.bekrenov.dao;

import com.bekrenov.entity.Lend;
import com.bekrenov.util.SessionFactoryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Locale;

public class LendDAOImpl implements LendDAO{

    // TODO: consider applying transactions via AOP

    private SessionFactory sessionFactory;

    public LendDAOImpl() {
        sessionFactory = SessionFactoryBuilder.buildSessionFactory();
    }

    @Override
    public void save(Lend lend) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.merge(lend);

        session.getTransaction().commit();
    }

    @Override
    public List<Lend> findAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Lend> result = session.createQuery("from Lend", Lend.class).getResultList();
        session.getTransaction().commit();
        return result;

    }

    @Override
    public Lend findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Lend.class, id);
    }

    @Override
    public List<Lend> findByPattern(String searchPattern) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String hql = "from Lend where lower(personFirstName) like :searchPattern or " +
                     "lower(personLastName) like :searchPattern or " +
                     "lower(bookTitle) like :searchPattern";
        Query<Lend> query = session.createQuery(hql, Lend.class);
        query.setParameter("searchPattern", "%" + searchPattern.toLowerCase() + "%");
        List<Lend> result = query.getResultList();

        session.getTransaction().commit();
        return result;
    }

    @Override
    public void deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Lend lend  = session.get(Lend.class, id);
        session.remove(lend);

        session.getTransaction().commit();
    }




}

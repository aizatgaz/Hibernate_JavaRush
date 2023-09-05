package com.aizatgaz.dao;

import com.aizatgaz.entities.Rental;
import com.aizatgaz.entities.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

public class RentalDAO extends AbstractDAO<Rental> {
    public RentalDAO(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    public void returnFilm() {
        try (Session session = getSessionFactory().openSession()) {
            Query<Rental> query = session.createQuery("from Rental where returnDate is null", Rental.class);
            query.setMaxResults(1);
            Rental singleResult = query.getSingleResult();
            if (singleResult == null) return;

            singleResult.setReturnDate(LocalDateTime.now());
            update(singleResult);
        }
    }

}

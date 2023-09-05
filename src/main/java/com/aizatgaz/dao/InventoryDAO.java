package com.aizatgaz.dao;

import com.aizatgaz.entities.Inventory;
import com.aizatgaz.entities.Rental;
import com.aizatgaz.entities.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class InventoryDAO extends AbstractDAO<Inventory> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }

    public List<Inventory> getAvailableFilms(Staff staff) {
        try (Session session = getSessionFactory().openSession()) {
            Query<Inventory> query = session.createQuery("""
                select i from Inventory i 
                left join Rental r on r.inventory = i 
                where r.id is null 
                or r.returnDate is not null                 
                and r.staff = :STAFF 
                """, Inventory.class);
            query.setParameter("STAFF", staff);
            if (query.list().isEmpty()) return null;
            return query.list();
        }
    }
}

package com.aizatgaz.dao;

import com.aizatgaz.entities.City;
import org.hibernate.SessionFactory;

public class CityDAO extends AbstractDAO<City> {
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }
}

package com.aizatgaz.dao;

import com.aizatgaz.entities.Store;
import org.hibernate.SessionFactory;

public class StoreDAO extends AbstractDAO<Store> {

    public StoreDAO(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}

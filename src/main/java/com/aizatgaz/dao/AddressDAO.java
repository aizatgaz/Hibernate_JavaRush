package com.aizatgaz.dao;

import com.aizatgaz.entities.Address;
import org.hibernate.SessionFactory;

public class AddressDAO extends AbstractDAO<Address> {
    public AddressDAO(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}

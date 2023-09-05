package com.aizatgaz.dao;

import com.aizatgaz.entities.Actor;
import org.hibernate.SessionFactory;

public class ActorDAO extends AbstractDAO<Actor>{

    public ActorDAO(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}

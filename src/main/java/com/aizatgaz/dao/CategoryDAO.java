package com.aizatgaz.dao;

import com.aizatgaz.entities.Category;
import org.hibernate.SessionFactory;

public class CategoryDAO extends AbstractDAO<Category> {
    public CategoryDAO(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}

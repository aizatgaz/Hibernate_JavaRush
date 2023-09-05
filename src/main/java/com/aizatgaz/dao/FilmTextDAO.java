package com.aizatgaz.dao;

import com.aizatgaz.entities.FilmText;
import org.hibernate.SessionFactory;

public class FilmTextDAO extends AbstractDAO<FilmText> {
    public FilmTextDAO(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}

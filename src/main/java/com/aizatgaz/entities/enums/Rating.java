package com.aizatgaz.entities.enums;

import lombok.ToString;

@ToString
public enum Rating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17");

    private String value;

    Rating(String rating) {
        value = rating.toUpperCase();
    }

    public String getValue() {
        return value;
    }
}

package com.aizatgaz.entities.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.NoSuchElementException;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {

    @Override
    public String convertToDatabaseColumn(Rating rating) {
        return rating.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String s) {
        Rating[] values = Rating.values();
        for (Rating r : values) {
            if (r.toString().equals(s)) return r;
        }
        return null;
    }
}

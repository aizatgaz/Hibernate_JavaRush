package com.aizatgaz.entities.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Year;
import java.util.Objects;

@Converter(autoApply = true)
public class YearConverter implements AttributeConverter<Year, Short> {
    @Override
    public Short convertToDatabaseColumn(Year year) {
        if (Objects.isNull(year)) return null;
        return (short) year.getValue();
    }

    @Override
    public Year convertToEntityAttribute(Short year) {
        if (Objects.isNull(year)) return null;
        return Year.of(year);
    }
}

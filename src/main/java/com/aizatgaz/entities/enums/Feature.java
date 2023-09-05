package com.aizatgaz.entities;

import static java.util.Objects.isNull;

public enum Feature {

    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private String value;

    Feature(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Feature getFeatureFromValue(String value) {
        if (isNull(value) || value.isEmpty()) return null;

        Feature[] features = Feature.values();
        for (Feature f : features) {
            if (f.value.equals(value)) return f;
        }
        return null;
    }

}

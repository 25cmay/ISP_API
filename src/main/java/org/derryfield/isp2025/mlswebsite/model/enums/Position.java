package org.derryfield.isp2025.mlswebsite.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Position {
    STRIKER,
    WINGER,
    MIDFIELDER,
    FULLBACK,
    CENTER_BACK,
    GOALKEEPER;

    @JsonCreator
    public static Position fromString(String string) {
        try {
            return Position.valueOf(string.trim().toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                String.format("Invalid Position: %s. Must be one of %s", string, valuesAsString()), e);
        }
    }

    @JsonValue
    public String toString() {
        return name().replace("_", " ");
    }

    public static String valuesAsString() {
        return Arrays.stream(values())
                .map(Enum::toString)
                .collect(Collectors.joining(", "));
    }
}

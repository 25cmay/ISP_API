package org.derryfield.isp2025.mlswebsite.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum PreferredFoot {
    LEFT,
    RIGHT,
    BOTH;

    @JsonCreator
    public static PreferredFoot fromString(String string) {
        try {
            return PreferredFoot.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                String.format("Invalid Preferred Foot: %s. Must be one of %s", string, valuesAsString()), e);
        }
    }

    @JsonValue
    public String toString() {
        return name();
    }

    public static String valuesAsString() {
        return Arrays.stream(values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }
}
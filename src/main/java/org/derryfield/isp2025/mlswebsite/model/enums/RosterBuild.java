package org.derryfield.isp2025.mlswebsite.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum RosterBuild {
    DP,
    U22;

    @JsonCreator
    public static RosterBuild fromString(String string) {
        try {
            return RosterBuild.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                String.format("Invalid Roster Build: %s. Must be one of %s", string, valuesAsString()), e);
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
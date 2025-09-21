package org.derryfield.isp2025.mlswebsite.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum SpecialContractDesignations {
    YOUNG_DESIGNATED_PLAYER,
    DESIGNATED_PLAYER,
    U22_INITIATIVE,
    HOME_GROWN_PLAYER,
    PROFESSIONAL_PLAYER_DEVELOPMENT_ROLE,
    GENERATION_ADIDAS,
    TARGETED_ALLOCATION_MONEY,
    NONE;

    @JsonCreator
    public static SpecialContractDesignations fromString(String string) {
        try {
            return SpecialContractDesignations.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    String.format("Invalid Special Contract Designation :%s. Must be one of %s", string, valuesAsString()),
                    e);
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
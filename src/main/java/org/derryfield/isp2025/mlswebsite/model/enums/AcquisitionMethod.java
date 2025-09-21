package org.derryfield.isp2025.mlswebsite.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum AcquisitionMethod {
    DRAFT,
    TRADE,
    TRANSFER,
    FREE_AGENCY,
    HOMEGROWN,
    LOAN,
    EXPANSION_DRAFT,
    WAIVER,
    RE_ENTRY;

    @JsonCreator
    public static AcquisitionMethod fromString(String string) {
        try {
            return AcquisitionMethod.valueOf(string.trim().toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                String.format("Invalid Acquisition Method: %s. Must be one of %s", string, valuesAsString()), e);
        }
    }

    @JsonValue
    public String toString() {
        return name().replace("_", " ");
    }

    public static String valuesAsString() {
        return Arrays.stream(values())
                .map(AcquisitionMethod::toString)
                .collect(Collectors.joining(", "));
    }
}

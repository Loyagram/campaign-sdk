package com.loyagram.android.campaignsdk.general.enums;

/**
 * Handles Api Errors.
 */
public enum ApiErrors {
    CONNECTION("CONNECTION"),
    TIME_OUT("TIME_OUT"),
    UNKNOWN("UNKNOWN");

    private final String name;

    private ApiErrors(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}

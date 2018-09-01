package com.example.EnumTypes;

public enum DurationType {
    ZERO(0),
    SUPER_SHORT(1),
    SHORT(5),
    MEDIUM(10),
    LONG(15),
    SUPER_LONG(30);

    private final int value;

    DurationType(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }


}

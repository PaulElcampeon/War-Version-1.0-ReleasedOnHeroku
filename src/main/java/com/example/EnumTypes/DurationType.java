package com.example.EnumTypes;

public enum DurationType {
    SUPER_SHORT(2),
    SHORT(5),
    MEDIUM(8),
    LONG(10),
    SUPER_LONG(15);

    private final int value;

    DurationType(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }


}

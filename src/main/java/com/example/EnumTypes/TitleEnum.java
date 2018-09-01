package com.example.EnumTypes;

public enum TitleEnum {
    PRIVATE(1),
    CORPORAL(5),
    SERGEANT(10),
    MASTER_SERGEANT(15),
    SERGEANT_MAJOR(20),
    KNIGHT(25),
    KNIGHT_LIEUTENANT(30),
    KNIGHT_CAPTAIN(35),
    KNIGHT_CHAMPTION(40),
    LIEUTENANT_COMMANDER(45),
    COMMANDER(50),
    MARSHALL(55),
    FIELD_MARSHALL(60),
    GRAND_MARSHALL(65);

    private final int rank;

    TitleEnum(final int rankValue){
       rank = rankValue;
    }

    public int getValue(){
        return rank;
    }

}

package com.example.Models.Title;


import com.example.EnumTypes.TitleEnum;
import com.example.Models.Warrior.Warrior;

public class TitleIssuer {

    //CHECKED
    //TESTED
    public static void issueTitle(Warrior warrior){
        TitleEnum[] titles = TitleEnum.values();
        for(TitleEnum titleEnum : titles){
            if (titleEnum.getValue() == warrior.getLevel()) {
                warrior.setTitle(titleEnum);
                System.out.println("You gained a title "+titleEnum);
            }
        }
    }
}

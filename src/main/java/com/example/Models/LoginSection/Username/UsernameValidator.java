package com.example.Models.LoginSection.Username;


import com.example.Models.Warrior.Warrior;

import java.util.ArrayList;

public class UsernameValidator {

    public static boolean isValid(String username, ArrayList<Warrior> arrayList) {
        for (Warrior warrior : arrayList) {
            if (username.equalsIgnoreCase(warrior.getName())) {
                return false;
            }
        }
        return true;
    }
}

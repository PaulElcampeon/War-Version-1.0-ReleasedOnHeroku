package com.example.Models.Hospital;


import com.example.Models.Warrior.Warrior;

public class Hospital {

    //CHECKED
    //TESTED
    public static void healWarrior(Warrior warrior) {
        if (checkIfWarriorHasEnoughMoney((warrior))) {
            warrior.setHealthPoints(warrior.getHealthPointsGoal());
            warrior.setMoney(warrior.getMoney() - 10);
        }
    }


    public static boolean checkIfWarriorHasEnoughMoney(Warrior warrior) {
        if (warrior.getMoney() >= 10) {
            return true;
        }
        return false;
    }
}

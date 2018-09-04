package com.example.Models.CheckForInactiveUsers;

import com.example.Models.Warrior.Warrior;
import com.example.Services.DaoServices.WarriorDaoServiceImplementation;
import java.util.ArrayList;
import java.util.Date;

public class CheckForInactiveUsers {

    //CHECKED ALL GOOD FOR HEROKU

    public static void setToOffLineInActiveUsers() {
        System.out.println("setToOffLineInActiveUsers Thread is running");

        ArrayList<Warrior> warriorList = (ArrayList<Warrior>) new WarriorDaoServiceImplementation().getAll();
        if (warriorList.size() != 0) {
            for (Warrior warrior : warriorList) {
                if (warrior.isOnline()) {
                    if (new Date().getTime() - Long.parseLong(warrior.getLastActive()) >= 900000) { //been inACTIVE FOR MORE THAN 15 MINUTES
                        warrior.setOnline(false);
                        System.out.println(warrior.getName() + "HAS JUST BEEN SET OFFLINE");
                        new WarriorDaoServiceImplementation().updateObject(warrior.getName(), warrior);
                    }
                }
            }
        }
    }

}

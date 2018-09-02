package com.example;

import com.example.Dao.Mongo.WarriorDaoV3Mongo;
import com.example.Models.BattleSection.Battle.Battle;
import com.example.Models.CheckForInactiveUsers.CheckForInactiveUsers;
import com.example.Models.Warrior.Warrior;
import com.example.Services.BattleServices.BattleServiceImplementation;
import com.example.Services.DaoServices.WarriorDaoServiceImplementation;
import com.example.Services.ExperienceServices.ExperienceServiceImplementation;
import com.example.Services.LevelingServices.LevelingServiceImplementation;
import com.example.Services.TitleServices.TitleServiceImplemented;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
public class Main {

    public static void main(String args[]) throws IOException {
        SpringApplication.run(Main.class,args);

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable periodicTask = new Runnable() {
            public void run() {
                // Invoke method(s) to do the work
                CheckForInactiveUsers.setToOffLineInActiveUsers();
                Thread.currentThread().getName();
            }
        };

        executor.scheduleAtFixedRate(periodicTask, 0, 600, TimeUnit.SECONDS);//every 10 minutes



//        WarriorDaoServiceImplementation warriorDaoServiceImplementation = new WarriorDaoServiceImplementation();
//        Warrior warrior = (Warrior) warriorDaoServiceImplementation.getObject("Dave");
//        System.out.println(warrior.isOnline());
//
//        warrior.setOnline(true);
//
//        System.out.println(warrior.isOnline());
//
//        warriorDaoServiceImplementation.logout("Dave");
//
//        Warrior warrior1 = (Warrior) warriorDaoServiceImplementation.getObject("Dave");
//
//
//        System.out.println(warrior1.isOnline());

//        ExperienceServiceImplementation experienceServiceImplementation = new ExperienceServiceImplementation();
//        LevelingServiceImplementation levelingServiceImplementation = new LevelingServiceImplementation();
//        BattleServiceImplementation battleServiceImplementation = new BattleServiceImplementation();
//        TitleServiceImplemented titleServiceImplemented = new TitleServiceImplemented();
//
//        Warrior attacker;
//        Warrior defender;
//        attacker = (Warrior) warriorDaoServiceImplementation.getObject("Dave");
//        defender = (Warrior) warriorDaoServiceImplementation.getObject("Ed");
//
//        battleServiceImplementation.startBattleAll(attacker, defender);
//
//        System.out.println("VICTOR " + battleServiceImplementation.getVictor());
//
//
//
//        experienceServiceImplementation.setExperienceFromBattle(battleServiceImplementation.returnBattle());
//        levelingServiceImplementation.levelUp(battleServiceImplementation.getVictor());
//        titleServiceImplemented.issueTitle(battleServiceImplementation.getVictor());
    }





    //elxir and weapons and armour creation were not random were not random
    //messaging service
    //combine armour
    //combine weapon
    //combine elixir

}

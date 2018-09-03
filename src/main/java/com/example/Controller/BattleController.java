package com.example.Controller;


import com.example.Models.Other.InitializeResponse;
import com.example.Models.Other.LocationPrinter;
import com.example.Models.Warrior.Warrior;
import com.example.Services.BattleServices.BattleServiceImplementation;
import com.example.Services.DaoServices.WarriorDaoServiceImplementation;
import com.example.Services.ExperienceServices.ExperienceServiceImplementation;
import com.example.Services.LevelingServices.LevelingServiceImplementation;
import com.example.Services.TitleServices.TitleServiceImplemented;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RestController
public class BattleController {

    private static WarriorDaoServiceImplementation warriorDaoServiceImplementation = new WarriorDaoServiceImplementation();
    private static BattleServiceImplementation battleServiceImplementation = new BattleServiceImplementation();
    private static ExperienceServiceImplementation experienceServiceImplementation = new ExperienceServiceImplementation();
    private static LevelingServiceImplementation levelingServiceImplementation = new LevelingServiceImplementation();
    private static TitleServiceImplemented titleServiceImplemented = new TitleServiceImplemented();
    private Warrior aggressor;
    private Warrior defender;
    private Gson gson = new Gson();

    //possible could change this end point to /battle/fight/{aggressor}/{defender} (we will use their names)
    @RequestMapping(value="/battle/fight", method=RequestMethod.POST)
    public void fight(@RequestBody HashMap<String,String> namesOfFighters, HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("BATTLE");
        System.out.println("NAME OF FIGHTERS: " + namesOfFighters);
        this.getFightersAsObjects(namesOfFighters);
        battleServiceImplementation.startBattleAll(aggressor, defender);
        this.updateVictorsLevelAndTitle(battleServiceImplementation);
        this.battleResultsCommentry(battleServiceImplementation);
        this.updateFightersInDB();
        HashMap<String, Object> battleDetails = new HashMap<>();
        battleDetails.put("warrior", aggressor);
        battleDetails.put("AttackersDamagePattern", battleServiceImplementation.getAttacksDamagePattern());
        battleDetails.put("DefendersDamagePattern", battleServiceImplementation.getDefendersDamagePattern());
        System.out.println("Attacks Damage Pattern: " + battleServiceImplementation.getAttacksDamagePattern());
        System.out.println("Defenders Damage Pattern: " + battleServiceImplementation.getDefendersDamagePattern());
        String battleDetailsJSON = gson.toJson(battleDetails);
        InitializeResponse.initialize(res);
        //Making sure to empty the array
        battleServiceImplementation.getAttacksDamagePattern().clear();
        battleServiceImplementation.getDefendersDamagePattern().clear();
        res.getWriter().write(battleDetailsJSON);
    }

    private void battleResultsCommentry(BattleServiceImplementation battleServiceImplementation){
        System.out.println("warrior with new title " + battleServiceImplementation.getVictor());
        System.out.println("TITLE FOR VICTOR IS " + battleServiceImplementation.getVictor());
        System.out.println("VICTOR: " + battleServiceImplementation.getVictor());
        System.out.println("LOSER: " + battleServiceImplementation.getLoser());
        System.out.println("AGGRESSOR: " + aggressor);
        System.out.println("DEFENDER: " + defender);
    }

    private void updateVictorsLevelAndTitle(BattleServiceImplementation battleServiceImplementation){
//        Warrior victor = (Warrior) warriorDaoServiceImplementation.getObject(battleServiceImplementation.getVictor());

        experienceServiceImplementation.setExperienceFromBattle(battleServiceImplementation.returnBattle());
        levelingServiceImplementation.levelUp(battleServiceImplementation.getVictor());
        titleServiceImplemented.issueTitle(battleServiceImplementation.getVictor());
//        victor.setExperiencePoints((battleServiceImplementation.getVictor().getExperiencePoints()));
//        victor.setLevel((battleServiceImplementation.getVictor().getLevel()));
//        victor.setTitle((battleServiceImplementation.getVictor().getTitle()));
//        warriorDaoServiceImplementation.updateObject(victor.getName(), victor);

    }

    private void getFightersAsObjects(HashMap<String,String> namesOfFighters){
        aggressor = (Warrior) warriorDaoServiceImplementation.getObject(namesOfFighters.get("aggressorName"));
        defender = (Warrior) warriorDaoServiceImplementation.getObject(namesOfFighters.get("defenderName"));
    }

    private void updateFightersInDB(){

        warriorDaoServiceImplementation.updateObject(aggressor.getName(), aggressor);
        warriorDaoServiceImplementation.updateObject(defender.getName(), defender);
    }
}

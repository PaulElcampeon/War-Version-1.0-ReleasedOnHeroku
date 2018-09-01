package com.example.Models.LevelingSection.ExperienceSetter;


import com.example.Models.BattleSection.Battle.Battle;
import com.example.Models.OperationSection.Operation.Operation;
import com.example.Models.Warrior.Warrior;

public class ExperienceSetter {

    //CHECKED
    private static int basicExperiencePoints, bonusExperiencePoints, experiencePointsGained, totalExperiencePoints;

    public static void setExperienceFromBattle(Battle battle){
        basicExperiencePoints = 20;
        experienceCalculator(battle.getVictor(), battle.getLoser());
    }


    public static void experienceCalculator(Warrior victor, Warrior loser) {
        int victorLevel = victor.getLevel();
        int loserLevel = loser.getLevel();
        int differenceInLevel = victorLevel - loserLevel;

        if (differenceInLevel > 4) {
            System.out.println("You did not gain any experience from this");
        }
        if (differenceInLevel > 0 && differenceInLevel < 5) {
            setExperienceIfAttackerIsHigherLevel(victor, differenceInLevel);
        }
        if (differenceInLevel <= 0 && differenceInLevel > -5) {
            setExperienceIfAttackerIsLowerLevel(victor, differenceInLevel);
        }
        if (differenceInLevel <= -5) {
            System.out.println("You did not gain any experience from this ");
        }
    }


    private static void setExperienceIfAttackerIsLowerLevel(Warrior victor, int differenceInLevel) {
        bonusExperiencePoints = Math.abs(5 * differenceInLevel);
        experiencePointsGained = basicExperiencePoints + bonusExperiencePoints;
        totalExperiencePoints = victor.getExperiencePoints() + basicExperiencePoints + bonusExperiencePoints;
        victor.setExperiencePoints(totalExperiencePoints);
        System.out.println("You gained " + experiencePointsGained + " experience points");
    }


    private static void setExperienceIfAttackerIsHigherLevel(Warrior victor, int differenceInLevel) {
        bonusExperiencePoints = 5 * differenceInLevel;
        experiencePointsGained = (basicExperiencePoints - bonusExperiencePoints);
        totalExperiencePoints = victor.getExperiencePoints() + (basicExperiencePoints - bonusExperiencePoints);
        victor.setExperiencePoints(totalExperiencePoints);
        System.out.println("You gained " + experiencePointsGained + " experience points");
    }


    public static void setExperienceFromOperation(Warrior warrior){
        experienceCalculator(warrior);
    }


    private static void experienceCalculator(Warrior warrior) {
        Operation operation = warrior.getOperationList().get(0);
        int OperationLevel = operation.getLevel();
        int warriorLevel = warrior.getLevel();
        int differenceInLevel = warriorLevel - OperationLevel;

        if (differenceInLevel >= 5) {
            System.out.println("YOU DID NOT EARN EXPERIENCE FROM THIS Operation");
        }else {
            System.out.println("YOU EARNED " + operation.getExperience() + " POINTS FROM THIS Operation");
            warrior.setExperiencePoints(warrior.getExperiencePoints()+ operation.getExperience());
        }
    }

}

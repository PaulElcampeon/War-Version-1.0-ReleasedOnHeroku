package com.example.Models.LevelingSection.Leveling;


import com.example.Models.Title.TitleIssuer;
import com.example.Models.Warrior.Warrior;

public class Leveling {

    //CHECKED
    //TESTED
    private static int count = 0;

    public static void levelUp(Warrior warrior){
        levelUpCalculator(warrior);
    }


    private static void levelUpCalculator(Warrior warrior) {
        if (warrior.getExperiencePoints() >= warrior.getExperiencePointsGoal()) {
            warrior.setLevel(warrior.getLevel() + 1);
            warrior.setExperiencePoints((warrior.getExperiencePoints() - warrior.getExperiencePointsGoal()));
            levelWarriorBonusDamageAndDefense(warrior,1);
            TitleIssuer.issueTitle(warrior);
            System.out.println("YOU ARE " + warrior.getExperiencePoints() + " POINTS INTO YOUR NEXT LEVEL");
            count++;

            if (warrior.getExperiencePoints() >= 100) {
                levelUp(warrior);
            }else {
                System.out.println("YOUR WARRIOR HAS LEVELED UP " + count + " TIMES");
            }
        }else {
            System.out.println("Your warrior did not level up");
        }
    }


    public static void levelWarriorBonusDamageAndDefense(Warrior warrior, int bonusIncrease) {
        warrior.setExperiencePointsGoal((int) (warrior.getExperiencePointsGoal() * 1.1));//INCREASE EXPERIENCE POINTS GOAL BY 20%
        warrior.setHealthPointsGoal((int) (warrior.getHealthPointsGoal() * 1.05));//INCREASE HEALTH BY 5%
        warrior.setBonusDamage(warrior.getBonusDamage() + bonusIncrease);
        warrior.setBonusDefense(warrior.getBonusDefense() + bonusIncrease);
        warrior.setChanceToHit(warrior.getChanceToHit() + 0.005);//INCREASE CHANCE TO HIT BY 0.005
        System.out.println("WARRIOR HEALTH IS NOW: " + warrior.getHealthPoints());
        System.out.println("WARRIOR CHANCE TO HIT IS NOW: " + warrior.getChanceToHit());
        System.out.println("WARRIOR BONUS DAMAGE IS NOW: " + warrior.getBonusDamage());
        System.out.println("WARRIOR BONUS DEFENSE IS NOW: " + warrior.getBonusDefense());
        System.out.println("WARRIOR LEVEL IS NOW: " + warrior.getLevel());
        System.out.println("WARRIOR EXPERIENCE POINTS GOAL IS NOW: " + warrior.getExperiencePointsGoal());

    }
}

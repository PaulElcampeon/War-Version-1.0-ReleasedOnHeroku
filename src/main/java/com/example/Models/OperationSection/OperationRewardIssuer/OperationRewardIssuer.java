package com.example.Models.OperationSection.OperationRewardIssuer;

import com.example.Models.ItemSection.ItemCreator.ItemCreator;
import com.example.Models.OperationSection.Operation.Operation;
import com.example.Models.PriceSection.PrizePriceAssigner.PrizePriceAssigner;
import com.example.Models.Title.TitleIssuer;
import com.example.Models.Warrior.Warrior;
import com.example.Services.ExperienceServices.ExperienceServiceImplementation;
import com.example.Services.LevelingServices.LevelingServiceImplementation;

public class OperationRewardIssuer {

    //CHECKED
    //TESTED
    private static ExperienceServiceImplementation experienceServiceImplementation;
    private static LevelingServiceImplementation levelingServiceImplementation;


    public static void issueOperationRewards(Warrior warrior) {
        initializeDependentVariables();
        setExperienceLevelTitleOfWarrior(warrior);
        setMoneyOfWarrior(warrior);
        createAndIssuePrizeToWarrior(warrior);
    }

    public static void initializeDependentVariables() {
        experienceServiceImplementation = new ExperienceServiceImplementation();
        levelingServiceImplementation = new LevelingServiceImplementation();

    }

    public static void setExperienceLevelTitleOfWarrior(Warrior warrior) {
        experienceServiceImplementation.setExperienceFromOperation(warrior);
        levelingServiceImplementation.levelUp(warrior);
        TitleIssuer.issueTitle(warrior);
    }

    public static void setMoneyOfWarrior(Warrior warrior) {
        int moneyFromOperation = warrior.getOperationList().get(0).getMoney();
        warrior.setMoney(warrior.getMoney() + moneyFromOperation);
    }

    public static void createAndIssuePrizeToWarrior(Warrior warrior) {
        Operation playerOperation = warrior.getOperationList().get(0);
        //create prize
        if (!checkIfBagIsFull(warrior)) {
            warrior.setBagFull(false);
            Object prize = ItemCreator.createPrize(playerOperation.getPrizeType(), playerOperation.getLevel());
            //set price of object
            PrizePriceAssigner.assignPrice(prize, playerOperation.getLevel());
            //add prize to bag
            warrior.getBag().add(prize);
        }else {
            warrior.setBagFull(true);
        }
    }

    public static boolean checkIfBagIsFull(Warrior warrior) {
        return warrior.getBag().size() == 12;
    }


}

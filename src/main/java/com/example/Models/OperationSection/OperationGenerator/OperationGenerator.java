package com.example.Models.OperationSection.OperationGenerator;

import com.example.EnumTypes.ItemType;
import com.example.Models.OperationSection.Operation.Operation;
import com.example.Models.OperationSection.OperationImages.OperationImages;
import java.util.ArrayList;
import java.util.Random;

public class OperationGenerator {

    public static ArrayList<Operation> generateMissions(int warriorLevel, int numberOfOperations) {
        ArrayList<Operation> generatedOperations = new ArrayList<>();

        for (int i = 0; i < numberOfOperations; i++) {
            Operation operation = new Operation();
            operation.setName(generateName());
            operation.setLevel(generateLevelOfMission(warriorLevel));
            operation.setPrizeType(generatePrizeType());
            operation.setExperience(generateExperience(warriorLevel));
            operation.setMoney(generateMoney(warriorLevel));
            operation.setDuration(generateDuration());
            operation.setImageUrl(generateImage());
            operation.setChanceOfSuccess(generateChanceOfSuccess());
            generatedOperations.add(operation);
            //Get RANDOM lEVEL OF MISSION
            //GET RANDOM PRIZE TYPE
            //GET RANDOM EXPEREINCE
            //GET RANDOM MONEY
            //GET RANDOM DURATION
            //GET RANDOM IMAGE
            //GET RANDOM chance of Success
        }

        return generatedOperations;
    }

    public static int generateLevelOfMission(int warriorLevel) {
        //mission level is gonna be between 3 levels of the warriors level
        return (new Random().nextInt(4)) + warriorLevel;
    }

    public static ItemType generatePrizeType() {
        ItemType[] itemTypes = ItemType.values();
        //2 - 0
        return itemTypes[new Random().nextInt(itemTypes.length)];
    }

    public static int generateExperience(int warriorLevel) {
        //Least experience is 25
        return 5 * (new Random().nextInt(warriorLevel + 1) + 5);
    }

    public static int generateMoney(int warriorLevel) {
        if (warriorLevel >= 50) {
            //150 - 65
            return 5 * 3 * (new Random().nextInt(6) + 5);
        }
        //90 - 15
        return 5 * 3 * (new Random().nextInt(6) + 1);
    }

    public static int generateDuration() {
        //30 - 5
//        return 5 * (new Random().nextInt(6) + 1);
        return (new Random().nextInt(10) + 1);

    }

    public static double generateChanceOfSuccess() {
        double[] chanceArr = {0.1, 0.15, 0, 0.2, 0.25, 0, 0};
        return 0.9 - (chanceArr[new Random().nextInt(7)]);
    }

    public static String generateImage() {
        return OperationImages.get();
    }

    public static String generateName() {
        String[] names = {"Pig Bristle", "Ajax", "Atilla", "Cactus", "Jock Scott", "Anvil", "Eagle Claw", "Nimbus", "Vodoo", "Necropolis", "Barren", "Alterac", "Trident", "Guilotine", "Ice Crown", "Citadel"};
        return names[new Random().nextInt(names.length)];
    }

}


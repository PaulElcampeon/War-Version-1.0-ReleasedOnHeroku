package com.example.Models.OperationSection.OnOperation;


import com.example.Models.OperationSection.Operation.Operation;
import com.example.Models.Warrior.Warrior;

import java.util.Random;

public class OnOperation {

    //CHECKED
    //TESTED
    private static double chanceOfSuccessOnThisOperation;
    private static Operation operation;

    public static void goOnOperation(Warrior warrior, Operation operation) {
        warrior.getOperationList().add(operation);
        warrior.getOperationList().get(0).setActive(true);
        warrior.setOnOperation(true);
        calculateChanceOfSuccess(warrior);
        calculateWhetherOperationWasSuccessfulOrNot();
    }

    private static void calculateChanceOfSuccess(Warrior warrior) {
        operation = warrior.getOperationList().get(0);
        int OperationLevel = operation.getLevel();
        double OperationChanceOfSuccess = operation.getChanceOfSuccess();
        int warriorLevel = warrior.getLevel();
        chanceOfSuccessOnThisOperation = OperationChanceOfSuccess;
        int differenceInLevel;
        if (warriorLevel < OperationLevel) {
            differenceInLevel = OperationLevel - warriorLevel;
            chanceOfSuccessOnThisOperation -= (differenceInLevel * 0.1);//need to round this to one decimal place
            if (chanceOfSuccessOnThisOperation < 0) {
                chanceOfSuccessOnThisOperation = 0;
            }
        }
        System.out.println("Chance Of Success: " + chanceOfSuccessOnThisOperation);
    }

    public static boolean calculateWhetherOperationWasSuccessfulOrNot() {//IF OPERATION IS SUCESSFULL WE NEED TO CREATE A PRIZE
        if (new Random().nextDouble() < chanceOfSuccessOnThisOperation) {
            operation.setSuccessful(true);
            return true;
        }
        operation.setSuccessful(false);
        return false;
    }

    public static void clearInActiveOperation(Warrior warrior) {
        if (!warrior.getOperationList().get(0).isActive()) {
            System.out.println("YOUR WARRIOR HAS AN INACTIVE OPERATION IT WILL NOW BE REMOVED");
            warrior.getOperationList().remove(0);
        } else {
            System.out.println("YOUR WARRIOR HAS AN ACTIVE OPERATION");
        }
    }

    public static double getChanceOfSuccessOnThisOperation() {
        return chanceOfSuccessOnThisOperation;
    }
}

//NEED TO USE THIS IN END POINT CALL
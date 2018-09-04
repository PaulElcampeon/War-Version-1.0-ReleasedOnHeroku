package com.example.Models.ItemSection.ElixirConsummation;


import com.example.EnumTypes.ElixirType;
import com.example.Models.ItemSection.Elixir.Elixir;
import com.example.Models.Warrior.Warrior;

import java.util.Date;

public class ElixirConsummation {

    //CHECKED ALL GOOD FOR HEROKU

    //Possibly we could just create elixirs through a method like createLesserHealthElixer() createHealthElixer() createGreaterHealthElixer()
    public static void consume(Warrior warrior, Elixir elixir) {

        if (elixir.getTypeOfElixir() == ElixirType.HEALTH) {
            warrior.setHealthPoints(warrior.getHealthPoints() + elixir.getAmount());
            if (warrior.getHealthPoints() > warrior.getHealthPointsGoal()) {
                warrior.setHealthPoints(warrior.getHealthPointsGoal());
            }
        }

        if (elixir.getTypeOfElixir() == ElixirType.ATTACK) {
            warrior.setBonusDamage(warrior.getBonusDamage() + elixir.getAmount());
            warrior.setElixirAffect("Bonus Damage :" + elixir.getAmount());
            warrior.setElixirAmount(elixir.getAmount());
            warrior.setElixirType(ElixirType.ATTACK);
            String elixirEndTimeString = setElixirEndTime(elixir);
            warrior.setElixirEndTime(elixirEndTimeString);
            warrior.setHasConsumedElixir(true);
        }

        if (elixir.getTypeOfElixir() == ElixirType.DEFENSE) {
            warrior.setBonusDefense(warrior.getBonusDefense() + elixir.getAmount());
            warrior.setElixirAffect("Bonus Defense :" + elixir.getAmount());
            warrior.setElixirAmount(elixir.getAmount());
            warrior.setElixirType(ElixirType.DEFENSE);
            String elixirEndTimeString = setElixirEndTime(elixir);
            warrior.setElixirEndTime(elixirEndTimeString);
            warrior.setHasConsumedElixir(true);
        }

        //REMOVING ELIXIR FROM WARRIORS BAG
        warrior.getBag().getElixirBag().remove(elixir);
    }

    private static String setElixirEndTime(Elixir elixir) {
        int elixirEndTime = elixir.getDuration();
        long elixirEndTimeAsLong = new Date().getTime() + (elixirEndTime * 60000);
        return String.valueOf(elixirEndTimeAsLong);
    }
}

package com.example.Models.ItemSection.CheckIfElixirHasRunOut;

import com.example.EnumTypes.ElixirType;
import com.example.Models.Warrior.Warrior;

import java.util.Date;

public class CheckIfElixirHasRunOut {

    public static void check(Warrior warrior) {

        if (new Date().getTime() > Long.parseLong(warrior.getElixirEndTime()))  {
            System.out.println("ELIXIR HAS RUN OUT");
            resetElixirEffects(warrior);
        }
    }


    private static void resetElixirEffects(Warrior warrior) {

        if (warrior.getElixirType() == ElixirType.DEFENSE) {
            warrior.setBonusDefense(warrior.getBonusDefense() - warrior.getElixirAmount());
            resetEffects(warrior);
        }

        if (warrior.getElixirType() == ElixirType.ATTACK) {
            warrior.setBonusDamage(warrior.getBonusDamage() - warrior.getElixirAmount());
            resetEffects(warrior);
        }
    }


    private static void resetEffects(Warrior warrior) {
        warrior.setHasConsumedElixir(false);
        warrior.setElixirAmount(0);
        warrior.setElixirAffect("");
        warrior.setElixirEndTime("");
    }
}

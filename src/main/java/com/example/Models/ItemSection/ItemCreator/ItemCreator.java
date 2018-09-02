package com.example.Models.ItemSection.ItemCreator;

import com.example.EnumTypes.ItemType;
import com.example.Models.ItemSection.ItemCreator.ArmourCreator.ArmourCreator;
import com.example.Models.ItemSection.ItemCreator.ElixirCreator.ElixirCreator;
import com.example.Models.ItemSection.ItemCreator.WeaponCreator.WeaponCreator;
import java.util.Random;

public class ItemCreator {

    //CHECKED
    //TESTED
    private static double  probability;


    public static Object createPrize(ItemType typeOfItem, int missionLevel) {

        probability = new Random().nextDouble();

        if (typeOfItem == ItemType.WEAPON) {
            return WeaponCreator.createWeapon(missionLevel, probability);
        }
        if (typeOfItem == ItemType.ARMOUR) {
            return ArmourCreator.createArmour(missionLevel, probability);
        }
        if (typeOfItem == ItemType.ELIXIR) {
            return ElixirCreator.createElixir();
        }
        return null;
    }
}

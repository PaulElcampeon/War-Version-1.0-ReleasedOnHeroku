package com.example.Models.PriceSection.PrizePriceAssigner;


import com.example.EnumTypes.ItemType;
import com.example.Models.ItemSection.Armour.Armour;
import com.example.Models.ItemSection.Elixir.Elixir;
import com.example.Models.ItemSection.Weapon.Weapon;
import com.example.Models.PriceSection.PriceCalculator.PriceCalculator;

public class PrizePriceAssigner {


    public static void assignPrice(Object object, int missionLevel) {

        if (object instanceof Weapon) {
            ((Weapon)object).setPrice(PriceCalculator.setPrice(missionLevel, ItemType.WEAPON, object));
        }
        if (object instanceof Armour) {
            ((Armour)object).setPrice(PriceCalculator.setPrice(missionLevel, ItemType.ARMOUR, object));
        }
        if (object instanceof Elixir) {
            ((Elixir)object).setPrice(PriceCalculator.setPrice(missionLevel, ItemType.ELIXIR, object));

        }

    }
}

package com.example.Models.PriceSection.PriceCalculator;


import com.example.EnumTypes.ItemType;
import com.example.Models.ItemSection.Armour.Armour;
import com.example.Models.ItemSection.Elixir.Elixir;
import com.example.Models.ItemSection.Weapon.Weapon;

public class PriceCalculator {


    public static int setPrice(int level, ItemType itemType, Object item) {

        if (itemType == ItemType.ELIXIR) {
            return setPriceForElixir(level, item);
        }

        if (itemType == ItemType.WEAPON) {
            return setPriceForWeapon(level, item);
        }

        if (itemType == ItemType.ARMOUR) {
            return setPriceForArmour(level, item);
        }
        //If all else fails then return 10 although there should be no reason for this to happen;
        return 10;
    }


    public static int setPriceForElixir(int level, Object item) {
        //Think we should have a limit that an elixir can only sell for a max of 500
        int priceAmount = level * ((Elixir)item).getAmount();
        return priceAmount > 500? 500 : priceAmount;
    }


    public static int setPriceForWeapon(int level, Object item) {
        //Think we should have a limit that a weapon can only sell for a max of 1000
        int priceAmount = (int)(level * ((Weapon)item).getTopRangeDamage());
        return priceAmount > 1000? 1000 : priceAmount;
    }


    public static int setPriceForArmour(int level, Object item) {
        //Think we should have a limit that an armour piece can only sell for a max of 1000
        int priceAmount = level * ((Armour)item).getDefenseLevel();
        return priceAmount > 1000? 1000 : priceAmount;
    }
}

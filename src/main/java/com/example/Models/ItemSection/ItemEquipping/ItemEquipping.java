package com.example.Models.ItemSection.ItemEquipping;


import com.example.EnumTypes.ArmourType;
import com.example.Models.ItemSection.Armour.Armour;
import com.example.Models.ItemSection.Weapon.Weapon;
import com.example.Models.Warrior.Warrior;

public class ItemEquipping {

    //when removing from bag we might need to use the index number
    public static void equipArmour(Warrior warrior, Armour armour){

        ArmourType typeOfArmour = armour.getBodyPart();
        System.out.println(typeOfArmour);
        Armour alreadyEquipped = null;

        if (typeOfArmour == ArmourType.HEAD) {
            alreadyEquipped = warrior.getArmourHead();
            warrior.setArmourHead(armour);
            warrior.getBag().getArmourBag().remove(armour);
            warrior.getBag().getArmourBag().add(alreadyEquipped);
        }

        if (typeOfArmour == ArmourType.CHEST) {
            alreadyEquipped = warrior.getArmourChest();
            warrior.setArmourChest(armour);
            warrior.getBag().getArmourBag().remove(armour);
            warrior.getBag().getArmourBag().add(alreadyEquipped);
        }

        if (typeOfArmour == ArmourType.LEG) {
            alreadyEquipped = warrior.getArmourLeg();
            warrior.setArmourLeg(armour);
            warrior.getBag().getArmourBag().remove(armour);
            warrior.getBag().getArmourBag().add(alreadyEquipped);
        }
    }

    public static void equipWeapon(Warrior warrior, Weapon weapon){
        Weapon alreadyEquipped = null;
        alreadyEquipped = warrior.getWeapon();
        warrior.setWeapon(weapon);
        warrior.getBag().getWeaponBag().remove(weapon);
        warrior.getBag().getWeaponBag().add(alreadyEquipped);
    }
}

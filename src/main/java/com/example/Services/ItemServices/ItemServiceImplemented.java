package com.example.Services.ItemServices;


import com.example.Models.ItemSection.Armour.Armour;
import com.example.Models.ItemSection.ItemEquipping.ItemEquipping;
import com.example.Models.ItemSection.Weapon.Weapon;
import com.example.Models.Warrior.Warrior;

public class ItemServiceImplemented implements ItemService {

    @Override
    public void equipArmour(Warrior warrior, Armour armour) {
        ItemEquipping.equipArmour(warrior, armour);
    }

    @Override
    public void equipWeapon(Warrior warrior, Weapon weapon) {
        ItemEquipping.equipWeapon(warrior, weapon);
    }
}

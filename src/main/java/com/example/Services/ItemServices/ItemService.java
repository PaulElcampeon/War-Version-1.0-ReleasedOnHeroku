package com.example.Services.ItemServices;


import com.example.Models.ItemSection.Armour.Armour;
import com.example.Models.ItemSection.Weapon.Weapon;
import com.example.Models.Warrior.Warrior;

public interface ItemService {

    public void equipArmour(Warrior warrior, Armour armour);

    public void equipWeapon(Warrior warrior, Weapon weapon);
}

package com.example.Services.LevelingServices;


import com.example.Models.LevelingSection.Leveling.Leveling;
import com.example.Models.Warrior.Warrior;

public class LevelingServiceImplementation implements LevelingService {


    @Override
    public void levelUp(Warrior warrior) {
        Leveling.levelUp(warrior);
    }

}

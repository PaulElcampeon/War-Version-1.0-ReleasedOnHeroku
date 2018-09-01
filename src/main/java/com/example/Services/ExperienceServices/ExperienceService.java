package com.example.Services.ExperienceServices;


import com.example.Models.BattleSection.Battle.Battle;
import com.example.Models.Warrior.Warrior;

public interface ExperienceService {

    public void setExperienceFromBattle(Battle battle);

    public void setExperienceFromOperation(Warrior warrior);
}

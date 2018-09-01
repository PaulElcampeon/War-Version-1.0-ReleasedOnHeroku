package com.example.Services.ExperienceServices;

import com.example.Models.BattleSection.Battle.Battle;
import com.example.Models.LevelingSection.ExperienceSetter.ExperienceSetter;
import com.example.Models.Warrior.Warrior;

public class ExperienceServiceImplementation implements  ExperienceService {


    public void setExperienceFromBattle(Battle battle) {
        ExperienceSetter.setExperienceFromBattle(battle);
    }

    public void setExperienceFromOperation(Warrior warrior) {
        ExperienceSetter.setExperienceFromOperation(warrior);
    }
}

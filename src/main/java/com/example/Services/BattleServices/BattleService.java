package com.example.Services.BattleServices;

import com.example.Models.BattleSection.Battle.Battle;
import com.example.Models.BattleSection.BattleReceipt.BattleReceipt;
import com.example.Models.Warrior.Warrior;

import java.util.ArrayList;

public interface BattleService {

    public void battlePrep(Warrior aggressor, Warrior defender);

    public void startBattle();

    public void startBattleAll(Warrior aggressor, Warrior defender);

    public void setVictorAndLoser();

    public void createBattleRececipt();

    public Warrior getVictor();

    public Warrior getLoser();

    public BattleReceipt getBattleReceipt();

    public void giveBattleReceiptsToBothPlayers();

    public Battle returnBattle();

    public ArrayList<Double> getAttacksDamagePattern();

    public ArrayList<Double> getDefendersDamagePattern();




}

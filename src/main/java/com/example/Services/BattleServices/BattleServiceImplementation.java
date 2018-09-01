package com.example.Services.BattleServices;

import com.example.Models.BattleSection.Battle.Battle;
import com.example.Models.BattleSection.BattleReceipt.BattleReceipt;
import com.example.Models.Warrior.Warrior;

import java.util.ArrayList;

public class BattleServiceImplementation implements BattleService {

    private static Battle battle = new Battle();

    @Override
    public void battlePrep(Warrior aggressor, Warrior defender) {
        battle.battlePrep(aggressor,defender);
    }

    @Override
    public void startBattle() {
        battle.startBattle();
    }

    @Override
    public void startBattleAll(Warrior aggressor, Warrior defender) {
        battle.startBattleAll(aggressor, defender);
    }

    @Override
    public void setVictorAndLoser() {
        battle.setVictorAndLoser();
    }

    @Override
    public void createBattleRececipt() {
        battle.createBattleReceipt();
    }

    @Override
    public void giveBattleReceiptsToBothPlayers() {
        battle.giveBattleReceiptsToBothPlayers();
    }

    @Override
    public Battle returnBattle() {
        return battle;
    }

    @Override
    public ArrayList<Double> getAttacksDamagePattern() {
        return battle.getAttackersDamagePattern();
    }

    @Override
    public ArrayList<Double> getDefendersDamagePattern() {
        return battle.getDefendersDamagePattern();
    }

    @Override
    public Warrior getVictor() {
        return battle.getVictor();
    }

    @Override
    public Warrior getLoser() {
        return battle.getLoser();
    }

    @Override
    public BattleReceipt getBattleReceipt() {
        return battle.getBattleReceipt();
    }
}

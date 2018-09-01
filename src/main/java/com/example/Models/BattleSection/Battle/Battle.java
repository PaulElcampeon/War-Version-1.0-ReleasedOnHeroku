package com.example.Models.BattleSection.Battle;


import com.example.Models.BattleSection.BattleReceipt.BattleReceipt;
import com.example.Models.Warrior.Warrior;
import lombok.Getter;
import lombok.Setter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@Getter
@Setter
public class Battle {

    //CHECKED
    private Warrior attacker, defender, victor, loser;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private String dateAsString, whoAttacksFirst;
    private BattleReceipt battleReceipt;
    private ArrayList<Double> attackersDamagePattern, defendersDamagePattern;
    double attackerHealthPoints, defenderHealthPoints, attackersAttack, defendersAttack, whoAttacksFirstProbability;


    public void battlePrep(Warrior attacker, Warrior defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.dateAsString = dateFormat.format(new Date());
        this.attackersDamagePattern = new ArrayList<>();
        this.defendersDamagePattern = new ArrayList<>();
        this.attackerHealthPoints = (double)this.attacker.getHealthPoints();
        this.defenderHealthPoints = (double)this.defender.getHealthPoints();
    }


    public void startBattle() {
        this.attackerAndDefendersDetails();
        this.runningBattle();
    }


    public void runningBattle() {
        //REASON FOR USING DOUBLES IS BECAUSE THIS WORKS BETTER WITH THE DEFENSE LEVEL OF THE ARMOUR ITEMS
        this.setWhoAttacksFirst();

        while(true) {
            this.attackersAttack = this.attacker.attack();
            this.defendersAttack = this.defender.attack();
            this.addingAttacksToDamagePatterns(attackersAttack, defendersAttack);
            this.attackSystem();
            System.out.println("ATTACKERS HEALTH IS :"+this.attackerHealthPoints);
            System.out.println("DEFENDERS HEALTH IS :"+this.defenderHealthPoints);
            if (isaWarriorsHealthBelowZero()) {
                System.out.println("FINAL");
                System.out.println("ATTACKERS HEALTH IS :"+this.attackerHealthPoints);
                System.out.println("DEFENDERS HEALTH IS :"+this.defenderHealthPoints);
                System.out.println("A warriors health is below 0");
                this.attacker.setHealthPoints((int)attackerHealthPoints);
                this.defender.setHealthPoints((int)defenderHealthPoints);
                break;
            }
        }
    }

    private void attackSystem() {
        if (whoAttacksFirst.equalsIgnoreCase("ATTACKER")) {
            this.subtractingAttacksFromFightersHealthAttackerHitsFirst();
        }else {
            this.subtractingAttacksFromFightersHealthDefenderHitsFirst();
        }
    }



    private void setWhoAttacksFirst() {
        this.whoAttacksFirst = new Random().nextDouble()>0.5?  "ATTACKER" : "DEFENDER";
        sayWhoAttacksFirst();
    }


    private void sayWhoAttacksFirst() {
        System.out.println("------------------------");
        if (this.whoAttacksFirst.equalsIgnoreCase("ATTACKER")) {
            System.out.println("ATTACKER ATTACKS FIRST");
        } else {
            System.out.println("DEFENDER ATTACKS FIRST");
        }
        System.out.println("------------------------");
    }


    private void subtractingAttacksFromFightersHealthAttackerHitsFirst() {
        this.defenderHealthPoints -= (this.defender.getTotalDamageReduction()) * this.attackersAttack;
        this.attackerHealthPoints -= (this.attacker.getTotalDamageReduction()) * this.defendersAttack;
    }


    private void subtractingAttacksFromFightersHealthDefenderHitsFirst() {
        this.attackerHealthPoints -= (this.attacker.getTotalDamageReduction()) * this.defendersAttack;
        this.defenderHealthPoints -= (this.defender.getTotalDamageReduction()) * this.attackersAttack;
    }


    private void addingAttacksToDamagePatterns(double attackersAttack, double defendersAttack) {
        this.attackersDamagePattern.add((this.defender.getTotalDamageReduction())*attackersAttack);
        this.defendersDamagePattern.add((this.attacker.getTotalDamageReduction())*defendersAttack);
        System.out.println("ATTACKERS ATTACK: "+attackersAttack);
        System.out.println("DEFENDERS ATTACK: "+defendersAttack);
    }


    private boolean isaWarriorsHealthBelowZero() {
        return this.defenderHealthPoints <= 0 || this.attackerHealthPoints <= 0;
    }


    public void setVictorAndLoser() {
       if (this.hasAttackerWon()) {
           this.setAttackAsVictor();
       }else {
           this.setDefenderAsVictor();
       }
    }


    private boolean hasAttackerWon() {
        return this.attacker.getHealthPoints() > this.defender.getHealthPoints();
    }


    private void setAttackAsVictor() {
        this.victor = this.attacker;
        this.loser = this.defender;
        this.defender.setHealthPoints(100);
        if(this.attacker.getHealthPoints() <= 0) {
            this.attacker.setHealthPoints(1);
        }
    }


    private void setDefenderAsVictor() {
        this.victor = this.defender;
        this.loser = this.attacker;
        this.defender.setHealthPoints(100);
        if(this.attacker.getHealthPoints()<=0){
            this.attacker.setHealthPoints(1);
        }
    }



    public void createBattleReceipt() {
        this.battleReceipt = new BattleReceipt(this);
    }


    public void giveBattleReceiptsToBothPlayers() {
        this.attacker.getBattleReceipts().add(this.battleReceipt);
        this.defender.getBattleReceipts().add(this.battleReceipt);
    }


    public void startBattleAll(Warrior attacker, Warrior defender) {
        this.battlePrep(attacker,defender);
        this.startBattle();
        this.setVictorAndLoser();
        this.createBattleReceipt();
        this.giveBattleReceiptsToBothPlayers();
    }


    private void attackerAndDefendersDetails() {
        System.out.println("Attacker is: "+this.attacker);
        System.out.println("Defender is: "+this.defender);
        System.out.println("Attackers attack range is between: "+this.attacker.getTotalTopDamage()+" - "+this.attacker.getTotalBottomDamage());
        System.out.println("Defenders attack range is between: "+this.defender.getTotalTopDamage()+" - "+this.defender.getTotalBottomDamage());
    }


}

package com.example.Models.Warrior;
import com.example.EnumTypes.ArmourType;
import com.example.EnumTypes.ElixirType;
import com.example.EnumTypes.TitleEnum;
import com.example.Models.BattleSection.BattleReceipt.BattleReceipt;
import com.example.Models.ItemSection.Armour.Armour;
import com.example.Models.ItemSection.Bag.Bag;
import com.example.Models.ItemSection.Weapon.Weapon;
import com.example.Models.OperationSection.Operation.Operation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.ArrayList;
import java.util.Random;

@Data
public class Warrior {

    private int id;
    private int healthPointsGoal = 100;
    private int healthPoints = 100;
    private String name, password, elixirAffect, elixirEndTime;
    private ElixirType elixirType;
    private int elixirAmount = 0;
    private int level = 1;
    private TitleEnum title = TitleEnum.PRIVATE;
    private ArrayList<BattleReceipt> battleReceipts = new ArrayList<>();
    private ArrayList<Operation> operationList = new ArrayList<>();
    private Armour armourHead = new Armour("Poor Mans Cap",1, ArmourType.HEAD,"https://images-na.ssl-images-amazon.com/images/I/419M0Iik8hL._AC_UL260_SR200,260_.jpg");
    private Armour armourChest = new Armour("Servants Chest Piece",1, ArmourType.CHEST,"http://www.caspergaming.com/FJKB/Images/leather_chest_piece.png");
    private Armour armourLeg = new Armour("Squires Leggings",1, ArmourType.LEG,"https://vignette.wikia.nocookie.net/elderscrolls/images/0/06/Studded_armor.png/revision/latest?cb=20121011211042");
    private Bag bag = new Bag();
    private Weapon weapon = new Weapon("Wooden Sword",3,1,"https://5.imimg.com/data5/XO/MV/MY-40012915/wooden-sword-500x500.jpg");
    private int money = 100;
    private String imageUrl = "https://wiki.guildwars2.com/images/thumb/5/56/Warrior_04_concept_art.png/350px-Warrior_04_concept_art.png";
    private int experiencePointsGoal = 100;
    private int experiencePoints = 0;
    private double bonusDamage = 0;
    private int bonusDefense = 0;
    private double chanceToHit = 0.75;
    private int victories = 0;
    private String lastActive;
    @JsonProperty
    private boolean hasConsumedElixir = false;
    @JsonProperty
    private boolean isOnline = false;
    @JsonProperty
    private boolean hasMessage = false;
    @JsonProperty
    private boolean isOnOperation = false;
    @JsonProperty
    private boolean isBagFull = false;


    public Warrior() {}

    public Warrior(String name, String password) {
        this.password = password;
        this.name = name;
    }

    public Warrior(String name, int healthPoints) {
        this.name = name;
        this.healthPoints = healthPoints;
    }

    public Warrior(String name, int level, int victories) {
        this.name = name;
        this.level = level;
        this.victories = victories;
    }

    public Warrior(TitleEnum title, int victories, int money) {
        this.title = title;
        this.victories = victories;
        this.money = money;
    }

    public Warrior(String name, int healthPoints, String password, String imageUrl) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.password = password;
        this.imageUrl = imageUrl;
    }


    public double getTotalTopDamage(){
        return this.weapon.getTopRangeDamage() + this.bonusDamage;
    }

    public double getTotalBottomDamage(){
        return this.weapon.getBottomRangeDamage() + this.bonusDamage;
    }

    public int getTotalDefenseFromArmour(){
        return this.armourChest.getDefenseLevel()+this.armourHead.getDefenseLevel() + this.armourLeg.getDefenseLevel();
    }

    public int getTotalDefense(){
        return this.getTotalDefenseFromArmour() + this.bonusDefense;
    }

    public double getTotalDamageReduction(){
        return (1-((double)this.getTotalDefense())/400);
    }


    public double attack() {
        double chanceToHitProbability = new Random().nextDouble();
        double criticalHitAmount = 1;
        if (chanceToHitProbability <= this.chanceToHit) {
            if (chanceToHitProbability <= 0.1) {
                criticalHitAmount = 1.3;
                System.out.println("YOU GOT A CRITICAL HIT");
                return this.getTotalTopDamage() * criticalHitAmount;
            }
                System.out.println("RANDOM NUMBER GENERATOR FOR CHANCE TO HIT");
                System.out.println(chanceToHitProbability);
                System.out.println("YOU HIT");

                double newAttack = (new Random().nextDouble()) * this.getTotalTopDamage();
                return (newAttack < this.getTotalBottomDamage()) ? this.getTotalBottomDamage() : newAttack;
        }
        System.out.println("YOU MISSED YOUR ATTACK");
        return 0;
    }


}

package com.example.Models.ItemSection.ItemCreator.ElixirCreator;

import com.example.EnumTypes.DurationType;
import com.example.EnumTypes.ElixirType;
import com.example.Models.ItemSection.Elixir.Elixir;
import com.example.Models.ItemSection.ItemCreator.ItemImages.ElixirImages;

import java.util.Random;

public class ElixirCreator {

    //CHECKED AND READY FOR HEROKU

    //CHECKED
    //TESTED
    private static Random rand = new Random();
    private static ElixirType[] elixirTypes;
    private static ElixirType elixirType;
    private static String imageUrl;
    private static DurationType[] durationTypes; //10 minutes
    private static DurationType durationType;
    private static int level;
    private static Elixir elixir;
    private static double probability;


    public static Elixir createElixir() {

        elixirTypes = ElixirType.values();
        elixirType = elixirTypes[rand.nextInt(2)];
        durationTypes = DurationType.values();
        durationType = durationTypes[rand.nextInt(durationTypes.length)];
        level = (new Random().nextInt(3)) + 1;
        imageUrl = ElixirImages.getImage(elixirType);
        probability = new Random().nextDouble();
        setElixirAmount(probability);

        if(elixirType == ElixirType.HEALTH) {
            elixir =  new Elixir("Health Potion", elixirType, 50 * level, imageUrl, level);
        }

        if(elixirType == ElixirType.ATTACK) {
            elixir = new Elixir("Attack Potion", elixirType, 10 * level, durationType, imageUrl, level);
        }

        if(elixirType == ElixirType.DEFENSE) {
            elixir = new Elixir("Defense Potion", elixirType, 10 * level, durationType, imageUrl, level);
        }

        return elixir;
    }


    public static void setElixirAmount(double probability){
        if (probability <= 0.7) { //70% chance
            level = 1;
        }
        if (probability > 0.7 && probability <= 0.9) {//20% chance
            level = 2;
        }
        if (probability > 0.9) { // 10% chance
            level = 3;
        }
    }

}

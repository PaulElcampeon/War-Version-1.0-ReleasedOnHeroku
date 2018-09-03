package com.example.Models.ItemSection.ItemCreator.ArmourCreator;

import com.example.EnumTypes.ArmourType;
import com.example.Models.ItemSection.Armour.Armour;
import com.example.Models.ItemSection.ItemCreator.ItemImages.ArmourImages;

import java.util.Random;

public class ArmourCreator {

    //CHECKED
    //TESTED
    private static Random rand;
    private static ArmourType[] bodyPartsName;
    private static ArmourType bodyPart;
    private static String imageUrl;
    private static int defenseLevel;
    private static Armour armour;

    public static Armour createArmour (int missionLevel, double probability) {
        rand = new Random();
        bodyPartsName = ArmourType.values();
        imageUrl = ArmourImages.getImage(bodyPart);
        bodyPart = bodyPartsName[rand.nextInt(2)];

        if (probability <= 0.7) {//have a 70% chance to get a normal armour
            //normal armour
            defenseLevel = rand.nextInt(2) + (missionLevel);
            armour = new Armour("Pauls Special Armour", defenseLevel, bodyPart, imageUrl);
        }

        if (probability > 0.7 && probability <= 0.9 ) {//have a 20% chance to get a good armour
            //good armour
            defenseLevel = rand.nextInt(2) + (missionLevel + 2);
            armour =  new Armour("Pauls Special Armour good", defenseLevel, bodyPart, imageUrl);
        }

        if (probability > 0.9) {//have a 10% chance to get a normal armour
            //very good armour
            defenseLevel = rand.nextInt(2) + (missionLevel + 4);
            armour =  new Armour("Pauls Special Armour very good", defenseLevel, bodyPart, imageUrl);
        }
        return armour;
    }


}

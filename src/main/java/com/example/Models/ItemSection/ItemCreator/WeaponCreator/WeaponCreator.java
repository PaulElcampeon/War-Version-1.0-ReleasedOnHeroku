package com.example.Models.ItemSection.ItemCreator.WeaponCreator;

import com.example.Models.ItemSection.Weapon.Weapon;
import java.util.Random;

public class WeaponCreator {

    //CHECKED
    //TESTED
    private static Random rand;
    private static int bottomRangeDamage;
    private static int topRangeDamage;
    private static String imageUrl;
    private static Weapon weapon;


    public static Weapon createWeapon(int missionLevel, double probability){

        rand = new Random();

        if(probability <= 0.75) {//have a 75% chance to get a normal weapon
            //normal weapon
            bottomRangeDamage = rand.nextInt(4) + (missionLevel);
            topRangeDamage = rand.nextInt(5) + bottomRangeDamage;
            imageUrl = "https://wow.zamimg.com/uploads/screenshots/normal/533880-ashbringer-retribution-paladin.jpg";
            weapon = new Weapon("Pauls Special Item", topRangeDamage, bottomRangeDamage, imageUrl, missionLevel);
        }

        if(probability > 0.75 && probability < 0.95 ){//have a 20% chance to get a good weapon
            //good weapon
            bottomRangeDamage = rand.nextInt(4) + (missionLevel + 2);
            topRangeDamage = rand.nextInt(5) + bottomRangeDamage;
            imageUrl = "https://wow.zamimg.com/uploads/screenshots/normal/554341.jpg";
            weapon = new Weapon("Pauls Special Item good", topRangeDamage, bottomRangeDamage, imageUrl, missionLevel + 1);
        }

        if(probability > 0.95){//have a 5% chance to get a normal weapon
            //very good weapon
            bottomRangeDamage = rand.nextInt(4) + (missionLevel + 6);
            topRangeDamage = rand.nextInt(10) + bottomRangeDamage;
            imageUrl = "https://o.aolcdn.com/images/dims?quality=100&image_uri=http%3A%2F%2Fwww.blogcdn.com%2Fwow.joystiq.com%2Fmedia%2F2011%2F01%2Farena.jpg&client=amp-blogside-v2&signature=e712b289e8156b0e61b5a8b9ab3e4ca5e91ba35b";
            weapon = new Weapon("Pauls Special Item very good", topRangeDamage, bottomRangeDamage, imageUrl, missionLevel + 2);
        }
        return weapon;
    }
}

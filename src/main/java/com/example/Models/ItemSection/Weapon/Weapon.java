package com.example.Models.ItemSection.Weapon;

import com.example.EnumTypes.ItemType;
import lombok.Data;

@Data
public class Weapon {

    private int id, level, price;
    private String name;
    private double topRangeDamage, bottomRangeDamage;
    private String imageUrl;
    private ItemType typeOfItem = ItemType.WEAPON;

    public Weapon(){};

    public Weapon(String name, int topRangeDamage, int bottomRangeDamage, String imageUrl) {
        this.name = name;
        this.topRangeDamage = topRangeDamage;
        this.bottomRangeDamage = bottomRangeDamage;
        this.imageUrl = imageUrl;
    }

    public Weapon(String name, int topRangeDamage, int bottomRangeDamage, String imageUrl, int level) {
        this.name = name;
        this.topRangeDamage = topRangeDamage;
        this.bottomRangeDamage = bottomRangeDamage;
        this.imageUrl = imageUrl;
        this.level = level;
    }

}

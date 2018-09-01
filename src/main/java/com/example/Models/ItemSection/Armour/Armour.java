package com.example.Models.ItemSection.Armour;


import com.example.EnumTypes.ArmourType;
import com.example.EnumTypes.ItemType;
import lombok.Data;

@Data
public class Armour {

    //CHECKED
    private int id, price, defenseLevel, level;
    private String name, imageUrl;
    private ArmourType bodyPart;
    private ItemType typeOfItem = ItemType.ARMOUR;


    public Armour() {}


    public Armour(String name, int defenseLevel, ArmourType bodyPart) {
        this.name = name;
        this.defenseLevel = defenseLevel;
        this.bodyPart = bodyPart;
    }


    public Armour(String name, int defenseLevel, ArmourType bodyPart, int level) {
        this.name = name;
        this.defenseLevel = defenseLevel;
        this.bodyPart = bodyPart;
        this.level = level;
    }


    public Armour(String name, int defenseLevel,  ArmourType bodyPart, String imageUrl) {
        this.name = name;
        this.defenseLevel = defenseLevel;
        this.bodyPart = bodyPart;
        this.imageUrl = imageUrl;
    }


    public String toString() {
        return "Name: "+this.name+", Defense Level: "+this.defenseLevel+", Body Part: "+this.bodyPart+", Image Url: "+this.imageUrl;
    }
}

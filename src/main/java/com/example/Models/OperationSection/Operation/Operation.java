package com.example.Models.OperationSection.Operation;

import com.example.EnumTypes.ItemType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Operation {

    //CHECKED
    private String name, imageUrl;
    private int id, level, money, experience, duration;
    private String startTime;
    private String endTime;
    private ItemType prizeType = ItemType.ELIXIR;
    private double chanceOfSuccess = 0.9;
    @JsonProperty
    private boolean isActive = false;
    @JsonProperty
    private boolean isSuccessful;


    public Operation() {}

    public Operation(int level, double chanceOfSuccess) {
        this.level = level;
        this.chanceOfSuccess = chanceOfSuccess;
    }

    public Operation(int level, int experience, int money) {
        this.level = level;
        this.experience = experience;
        this.money = money;
    }

    public Operation(int level, int experience, int money, ItemType prizeType) {
        this.level = level;
        this.experience = experience;
        this.money = money;
        this.prizeType = prizeType;
    }

    public Operation(String name, int level, int experience, int duration, String imageUrl, int money) {
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.duration = duration;
        this.imageUrl = imageUrl;
        this.money = money;
    }

    public Operation(String name, int level, int experience, int duration, String imageUrl, int money, double chanceOfSuccess) {
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.duration = duration;
        this.imageUrl = imageUrl;
        this.money = money;
        this.chanceOfSuccess = chanceOfSuccess;
    }

    public Operation(String name, int level, int experience, int duration, String imageUrl, int money, double chanceOfSuccess, ItemType prizeType) {
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.duration = duration;
        this.imageUrl = imageUrl;
        this.money = money;
        this.chanceOfSuccess = chanceOfSuccess;
        this.prizeType = prizeType;
    }

}

package com.example.Models.ItemSection.Elixir;


import com.example.EnumTypes.DurationType;
import com.example.EnumTypes.ElixirType;
import com.example.EnumTypes.ItemType;
import lombok.Data;

@Data
public class Elixir {

    //CHECKED
    private int id, amount, level, price;
    private String name;
    private String imageUrl = "https://thumbs.dreamstime.com/b/cartoon-glass-bottle-green-stopper-56186105.jpg";
    private ElixirType typeOfElixir;
    private int duration = DurationType.ZERO.getValue();
    private ItemType typeOfItem = ItemType.ELIXIR;


    public Elixir() {}



    public Elixir(String name, ElixirType typeofElixir, int amount, int duration) {
        this.name = name;
        this.typeOfElixir = typeofElixir;
        this.amount = amount;
        this.duration = duration;
    }

    public Elixir(String name, ElixirType typeofElixir, int amount) {
        this.name = name;
        this.typeOfElixir = typeofElixir;
        this.amount = amount;
    }


    public Elixir(String name, ElixirType typeofElixir, int amount, String imageUrl) {
        this.name = name;
        this.typeOfElixir = typeofElixir;
        this.amount = amount;
        this.imageUrl = imageUrl;
    }

    public Elixir(String name, ElixirType typeofElixir, int amount, DurationType duration, String imageUrl) {
        this.name = name;
        this.typeOfElixir = typeofElixir;
        this.amount = amount;
        this.duration = duration.getValue();
        this.imageUrl = imageUrl;
    }


    //SPECIFICALLY FOR HEALTH POTION
    public Elixir(String name, ElixirType typeofElixir, int amount, String imageUrl, int level) {
        this.name = name;
        this.typeOfElixir = typeofElixir;
        this.amount = amount;
        this.imageUrl = imageUrl;
        this.level = level;
    }


    public Elixir(String name, ElixirType typeofElixir, int amount, DurationType duration, String imageUrl, int level) {
        this.name = name;
        this.typeOfElixir = typeofElixir;
        this.amount = amount;
        this.duration = duration.getValue();
        this.imageUrl = imageUrl;
        this.level = level;
    }


    public String toString() {
        return "Name: "+this.name+", Type: "+this.typeOfElixir+", Amount: "+this.amount+", Duration: "+this.duration+", Image Url: "+this.imageUrl;
    }

}

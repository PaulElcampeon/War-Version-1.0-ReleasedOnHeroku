package com.example.Models.ItemSection.Bag;


import com.example.Models.ItemSection.Armour.Armour;
import com.example.Models.ItemSection.Elixir.Elixir;
import com.example.Models.ItemSection.Weapon.Weapon;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter
public class Bag implements BagInterface {

    //CHECKED
    private ArrayList<Weapon> weaponBag = new ArrayList<>();
    private ArrayList<Armour> armourBag = new ArrayList<>();
    private ArrayList<Elixir> elixirBag = new ArrayList<>();


    @Override
    public void add(Object object) {
        if (object instanceof Weapon) {
            weaponBag.add((Weapon)object);
        }
        if (object instanceof Armour) {
            armourBag.add((Armour) object);
        }
        if (object instanceof Elixir) {
            elixirBag.add((Elixir) object);
        }
    }


    @Override
    public void clear() {
        this.weaponBag.clear();
        this.armourBag.clear();
        this.elixirBag.clear();
    }


    @Override
    public int size(){
        return this.getElixirBag().size() + this.getArmourBag().size() + this.getWeaponBag().size();
    }


    public String toString() {
        return "Weapon Bag: "+this.weaponBag+", Elixir Bag: "+this.elixirBag+", Armour Bag: "+this.armourBag;
    }

}

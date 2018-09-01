package com.example.Models.ItemSection.ItemCreator.ItemImages;

import com.example.EnumTypes.ElixirType;

public class ElixirImages {

    //CHECKED
    public static String getImage(ElixirType elixirType) {
        if (elixirType == ElixirType.ATTACK) {
            return "http://gow.help/users/gow/images/imgnews/10/1/21/glaciarwater.jpg";
        }
        if (elixirType == ElixirType.DEFENSE) {
            return "http://gow.help/users/gow/images/imgnews/10/11/24/potiondefense.jpg";
        }
        if (elixirType == ElixirType.HEALTH) {
            return "https://cdnb.artstation.com/p/assets/images/images/006/740/775/large/alexandru-bulmaga-hp-potion.jpg?1500923339";
        }
        return "https://images-na.ssl-images-amazon.com/images/I/31qxpYX4e6L._SL500_AC_SS350_.jpg";
    }
}

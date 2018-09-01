package com.example.Models.ItemSection.ItemCreator.ItemImages;


import com.example.EnumTypes.ArmourType;

public class ArmourImages {

    //CHECKED
    public static String getImage(ArmourType armourType) {
        if (armourType == ArmourType.LEG) {
            return "https://static.webshopapp.com/shops/032318/files/011873134/275x275x2/leg-armour-and-sabatons.jpg";
        }
        if (armourType == ArmourType.HEAD) {
            return "https://images-na.ssl-images-amazon.com/images/I/61D09xnfSrL._UX385_.jpg";
        }
        if (armourType == ArmourType.CHEST) {
            return "https://images-na.ssl-images-amazon.com/images/I/61QznFLyBxL._UX466_.jpg";
        }
         return "https://sites.create-cdn.net/siteimages/28/4/9/284928/15/7/9/15798435/761x1000.jpg?1505296014";
    }
}

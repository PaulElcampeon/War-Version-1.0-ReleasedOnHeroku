package com.example.Models.OperationSection.OperationImages;

import java.util.Random;

public class OperationImages {

    private static String[] operationImages = {"https://d3qvqlc701gzhm.cloudfront.net/thumbs/2c7d16c220c9c00b5119e6e119fa2cb0dba3b0b46cfe4119f3ffb4b9a1d04daf-250.jpg",
            "https://pre00.deviantart.net/dbde/th/pre/f/2010/307/1/8/snowy_mountains_by_artek92-d323u5a.jpg", "https://i.ytimg.com/vi/c8zIG-71sh0/maxresdefault.jpg",
            "https://i.pinimg.com/originals/f8/cd/d6/f8cdd68f5a256c6b6f8a69443aa29597.jpg", "https://c402277.ssl.cf1.rackcdn.com/photos/1579/images/hero_full/HI_115201.jpg?1345598092",
            "http://animals.sandiegozoo.org/sites/default/files/2016-08/habitats_hero_desert.jpg",
            "https://s.yimg.com/ny/api/res/1.2/dCrdjGh_YtydgCD5cSRvdQ--~A/YXBwaWQ9aGlnaGxhbmRlcjtzbT0xO3c9MTI4MDtoPTk2MA--/http://media.zenfs.com/en/homerun/feed_manager_auto_publish_494/121ec28d7cc3935e2d845551a1d293c1",
            "https://upload.wikimedia.org/wikipedia/en/4/46/Ararat_Lunatic_Asylum_-_Aradale_Psychiatric_Hospital.jpg", "http://i.imgur.com/Fvgxo.jpg",
            "https://www.lovespells24.com/wp-content/uploads/2017/01/voodoo-love-spells.jpg", "http://3.bp.blogspot.com/_St657h4s204/TBcRaXCAUwI/AAAAAAAABXw/nHt0sg9ett8/s1600/_DSC4124.jpg",
            "https://www.dtelepathy.com/blog/wp-content/uploads/2015/10/Design-Graveyard.jpg", "https://vignette.wikia.nocookie.net/berserk/images/d/d8/Apostles_During_The_Eclipse.jpg/revision/latest?cb=20130626140645",
            "https://vignette.wikia.nocookie.net/berserk/images/f/f5/Eclipse_Infobox.jpg/revision/latest?cb=20130626140204", "https://pa1.narvii.com/6351/86f6405eb238b6a4202d03887e31143feaf3c072_hq.gif",
            "https://www.residentevil.net/share/blog/topics/0612cc.jpg"};




    public static String get() {

        return operationImages[new Random().nextInt(operationImages.length)];

    }


    public static String get(String operationName) {

        return operationImages[new Random().nextInt(operationImages.length)];

    }
}

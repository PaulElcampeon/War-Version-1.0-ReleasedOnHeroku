package com.example.Controller;


import com.example.Models.ItemSection.CheckIfElixirHasRunOut.CheckIfElixirHasRunOut;
import com.example.Models.ItemSection.ElixirConsummation.ElixirConsummation;
import com.example.Models.Other.InitializeResponse;
import com.example.Models.Other.LocationPrinter;
import com.example.Models.Warrior.Warrior;
import com.example.Services.DaoServices.WarriorDaoServiceImplementation;
import com.example.Services.ItemServices.ItemServiceImplemented;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ItemServiceController {

    private static WarriorDaoServiceImplementation warriorDaoServiceImplementation = new WarriorDaoServiceImplementation();
    private static ItemServiceImplemented itemServiceImplemented = new ItemServiceImplemented();
    private Gson gson = new Gson();
    private String[] servletPath;
    private int itemPositionInBag;
    private String objectAsString;
    Warrior userAvatar;

    @RequestMapping(value="equip/armour/{itemPositionInBag}", method= RequestMethod.PUT)
    public void equipArmour(@RequestBody Object object, HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("EQUIP ARMOUR");
        servletPath = req.getServletPath().split("/");
        itemPositionInBag = Integer.parseInt(servletPath[3]);
        objectAsString = gson.toJson(object);
        userAvatar = gson.fromJson(objectAsString,Warrior.class);
        itemServiceImplemented.equipArmour(userAvatar,userAvatar.getBag().getArmourBag().get(itemPositionInBag));
        System.out.println("Is the object a warrior ");
        System.out.println(userAvatar instanceof Warrior);
        System.out.println("THE WARRIOR IS "+userAvatar+" THE ID IS "+userAvatar.getId());
        userAvatar = (Warrior)warriorDaoServiceImplementation.updateObject(userAvatar.getName(), userAvatar);
        InitializeResponse.initialize(res);
        String userAvatarJSON = gson.toJson(userAvatar);
        res.getWriter().write(userAvatarJSON);
    }

    @RequestMapping(value="equip/weapon/{itemPositionInBag}", method= RequestMethod.PUT)
    public void equipWeapon(@RequestBody Object object, HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("EQUIP WEAPON");
        servletPath = req.getServletPath().split("/");
        itemPositionInBag = Integer.parseInt(servletPath[3]);
        objectAsString = gson.toJson(object);
        userAvatar = gson.fromJson(objectAsString,Warrior.class);
        itemServiceImplemented.equipWeapon(userAvatar,userAvatar.getBag().getWeaponBag().get(itemPositionInBag));
        System.out.println("Is the object a warrior ");
        System.out.println(userAvatar instanceof Warrior);
        warriorDaoServiceImplementation.updateObject(userAvatar.getName(), userAvatar);
        InitializeResponse.initialize(res);
        String userAvatarJSON = gson.toJson(userAvatar);
        res.getWriter().write(userAvatarJSON);
    }

    @RequestMapping(value="use/elixir/{itemPositionInBag}", method= RequestMethod.PUT)
    public void useElixir(@RequestBody Object object, HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("USE ELIXIR");
        servletPath = req.getServletPath().split("/");
        itemPositionInBag = Integer.parseInt(servletPath[3]);
        objectAsString = gson.toJson(object);
        userAvatar = gson.fromJson(objectAsString,Warrior.class);
        ElixirConsummation.consume(userAvatar,userAvatar.getBag().getElixirBag().get(itemPositionInBag));
        System.out.println("Is the object a warrior ");
        System.out.println(userAvatar instanceof Warrior);
        warriorDaoServiceImplementation.updateObject(userAvatar.getName(), userAvatar);
        InitializeResponse.initialize(res);
        String userAvatarJSON = gson.toJson(userAvatar);
        res.getWriter().write(userAvatarJSON);
    }

    @RequestMapping(value="check/elixir", method= RequestMethod.PUT)
    public void checkIfElixirAffectHasRunOut(@RequestBody Warrior warrior, HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("CHECK IF ELIXIR HAS RUN OUT");
//        Warrior warrior1 = new Warrior();
//        warrior1 = warrior;
        if (warrior.getElixirAmount() != 0) {
            System.out.println("WARRIOR HAS ELIXIR AMOUNT");
            CheckIfElixirHasRunOut.check(warrior);
        }

        warriorDaoServiceImplementation.updateObject(warrior.getName(), warrior);
        InitializeResponse.initialize(res);
        String userAvatarJSON = gson.toJson(warrior);
        System.out.println("WHAT WE ARE SENDING BACK FROM CHECK ELIXIR CONTROLLER " + userAvatarJSON);
        res.getWriter().write(userAvatarJSON);
    }

    //ok so equip armour end point
    //we will be given the the position of the armour in the armour bag and also the warrior object
    //assign that armour to an armour object
    //then use the equip armour in armour service
    //then update warrior in DB;

}
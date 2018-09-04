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
import java.util.Date;

@RestController
public class ItemServiceController {

    //CHECKED AND READY FOR HEROKU

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

        userAvatar = gson.fromJson(objectAsString, Warrior.class);

        Warrior warrior = (Warrior) warriorDaoServiceImplementation.getObject(userAvatar.getName());

        warrior.setLastActive(String.valueOf(new Date().getTime()));

        itemServiceImplemented.equipArmour(warrior, warrior.getBag().getArmourBag().get(itemPositionInBag));

        userAvatar = (Warrior)warriorDaoServiceImplementation.updateObject(warrior.getName(), warrior);

        InitializeResponse.initialize(res);

        String userAvatarJSON = gson.toJson(warrior);

        res.getWriter().write(userAvatarJSON);
    }

    @RequestMapping(value="equip/weapon/{itemPositionInBag}", method= RequestMethod.PUT)
    public void equipWeapon(@RequestBody Object object, HttpServletRequest req, HttpServletResponse res) throws IOException {

        LocationPrinter.printLocation("EQUIP WEAPON");

        servletPath = req.getServletPath().split("/");

        itemPositionInBag = Integer.parseInt(servletPath[3]);

        objectAsString = gson.toJson(object);

        userAvatar = gson.fromJson(objectAsString, Warrior.class);

        Warrior warrior = (Warrior) warriorDaoServiceImplementation.getObject(userAvatar.getName());

        warrior.setLastActive(String.valueOf(new Date().getTime()));

        itemServiceImplemented.equipWeapon(warrior, warrior.getBag().getWeaponBag().get(itemPositionInBag));

        warriorDaoServiceImplementation.updateObject(warrior.getName(), warrior);

        InitializeResponse.initialize(res);

        String userAvatarJSON = gson.toJson(warrior);

        res.getWriter().write(userAvatarJSON);
    }

    @RequestMapping(value="use/elixir/{itemPositionInBag}", method= RequestMethod.PUT)
    public void useElixir(@RequestBody Object object, HttpServletRequest req, HttpServletResponse res) throws IOException {

        LocationPrinter.printLocation("USE ELIXIR");

        servletPath = req.getServletPath().split("/");

        itemPositionInBag = Integer.parseInt(servletPath[3]);

        objectAsString = gson.toJson(object);

        userAvatar = gson.fromJson(objectAsString,Warrior.class);

        Warrior warrior = (Warrior) warriorDaoServiceImplementation.getObject(userAvatar.getName());

        warrior.setLastActive(String.valueOf(new Date().getTime()));

        ElixirConsummation.consume(warrior, warrior.getBag().getElixirBag().get(itemPositionInBag));

        warriorDaoServiceImplementation.updateObject(warrior.getName(), warrior);

        InitializeResponse.initialize(res);

        String userAvatarJSON = gson.toJson(warrior);

        res.getWriter().write(userAvatarJSON);
    }

    @RequestMapping(value="check/elixir", method= RequestMethod.PUT)
    public void checkIfElixirAffectHasRunOut(@RequestBody Warrior warrior, HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("CHECK IF ELIXIR HAS RUN OUT");

        Warrior warrior1 = (Warrior) warriorDaoServiceImplementation.getObject(warrior.getName());

        warrior1.setLastActive(String.valueOf(new Date().getTime()));

        if (warrior1.getElixirAmount() != 0) {

            System.out.println("WARRIOR HAS ELIXIR AMOUNT");

            CheckIfElixirHasRunOut.check(warrior1);

        }

        warriorDaoServiceImplementation.updateObject(warrior1.getName(), warrior1);

        InitializeResponse.initialize(res);

        String userAvatarJSON = gson.toJson(warrior1);

        System.out.println("WHAT WE ARE SENDING BACK FROM CHECK ELIXIR CONTROLLER " + userAvatarJSON);

        res.getWriter().write(userAvatarJSON);
    }

    //ok so equip armour end point
    //we will be given the the position of the armour in the armour bag and also the warrior object
    //assign that armour to an armour object
    //then use the equip armour in armour service
    //then update warrior in DB;

}

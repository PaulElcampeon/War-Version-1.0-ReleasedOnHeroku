package com.example.Controller;

import com.example.Models.Hospital.Hospital;
import com.example.Models.Other.InitializeResponse;
import com.example.Models.Other.LocationPrinter;
import com.example.Models.Warrior.Warrior;
import com.example.Services.DaoServices.WarriorDaoServiceImplementation;
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
public class HospitalController {

    //CHECKED AND READY FOR HEROKU
    private static WarriorDaoServiceImplementation warriorDaoServiceImplementation = new WarriorDaoServiceImplementation();
    private Gson gson = new Gson();

    @RequestMapping(value="/hospital/fullheal", method=RequestMethod.POST)
    public void healWarrior(@RequestBody Object object, HttpServletRequest req, HttpServletResponse res) throws IOException {

        LocationPrinter.printLocation("HOSPITAL");

        String userAvatarAsString = gson.toJson(object);

        Warrior userAvatarAsWarriorObject = gson.fromJson(userAvatarAsString, Warrior.class);

        Warrior warrior = (Warrior) warriorDaoServiceImplementation.getObject(userAvatarAsWarriorObject.getName());

        Hospital.healWarrior(warrior);

        System.out.println("JUST GOT HEALED IN HOSPITAL");

        //MAKE SURE WE UPDATE THE LAST ACTIVE TIME FOR WARRIOR
        warrior.setLastActive(String.valueOf(new Date().getTime()));

        warriorDaoServiceImplementation.updateObject(warrior.getName(), warrior);

        System.out.println("SAVED DATA");

        String jsonStringOfUserAvatar = gson.toJson(warrior);

        System.out.println(jsonStringOfUserAvatar);

        InitializeResponse.initialize(res);

        res.getWriter().write(jsonStringOfUserAvatar);

        System.out.println("SENDING DATA BACK FROM HOSPITAL");
    }
}

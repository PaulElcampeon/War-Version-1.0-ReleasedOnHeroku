package com.example.Controller;

import com.example.Models.Other.LocationPrinter;
import com.example.Models.Warrior.Warrior;
import com.example.Services.DaoServices.WarriorDaoServiceImplementation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
public class LogOutController {

    WarriorDaoServiceImplementation warriorDaoServiceImplementation = new WarriorDaoServiceImplementation();


    @RequestMapping(value = "/logout/{name}", method = RequestMethod.PUT)
    public void logout(@RequestBody Map<String, String> userDetails, HttpServletRequest req, HttpServletResponse res)throws IOException {
        LocationPrinter.printLocation("LOGOUT");
        String[] servletPath = req.getServletPath().split("/");
        String name = servletPath[2];
        warriorDaoServiceImplementation.logout(name);
//        Warrior warrior = (Warrior) warriorDaoServiceImplementation.getObject(name);
//        warrior.setOnline(false);
//        warriorDaoServiceImplementation.updateObject(name, warrior);
//        String clients_username = userDetails.get("username");
//        String clients_password = userDetails.get("password");
//        System.out.println("PASSWORD " + clients_password);
//        InitializeResponse.initialize(res);
//        String responseAsString =
//        System.out.println("DATA WE ARE SENDING AFTER LOGIN ATTEMPT");
//        System.out.println(responseAsString);
//        res.getWriter().write(responseAsString);
    }
}

package com.example.Controller;

import com.example.Models.Other.LocationPrinter;
import com.example.Services.DaoServices.WarriorDaoServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
public class LogOutController {

    WarriorDaoServiceImplementation warriorDaoServiceImplementation = new WarriorDaoServiceImplementation();


    @RequestMapping(value="/logout/{name}", method= RequestMethod.PUT)
    public void login(@RequestBody Map<String, String> userDetails, org.apache.catalina.servlet4preview.http.HttpServletRequest req, HttpServletResponse res)throws IOException {
        LocationPrinter.printLocation("LOGOUT");
        String[] servletPath = req.getServletPath().split("/");
        String name = servletPath[2];
        warriorDaoServiceImplementation.logout(name);
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

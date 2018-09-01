package com.example.Controller;


import com.example.Models.Other.InitializeResponse;
import com.example.Models.Other.LocationPrinter;
import com.example.Services.LoginServices.LoginServiceImplementation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@org.springframework.stereotype.Controller
public class LoginController {

    private static LoginServiceImplementation loginServiceImplementation = new LoginServiceImplementation();

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String homePage(){
        return "index.html";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public void login(@RequestBody Map<String, String> userDetails, org.apache.catalina.servlet4preview.http.HttpServletRequest req, HttpServletResponse res)throws IOException {
        LocationPrinter.printLocation("LOGIN");
        String clients_username = userDetails.get("username");
        String clients_password = userDetails.get("password");
        System.out.println("PASSWORD "+clients_password);
        InitializeResponse.initialize(res);
        String responseAsString = loginServiceImplementation.checkCredentials(clients_username, clients_password);
        System.out.println("DATA WE ARE SENDING AFTER LOGIN ATTEMPT");
        System.out.println(responseAsString);
        res.getWriter().write(responseAsString);
    }

}

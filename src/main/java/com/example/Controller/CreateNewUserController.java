package com.example.Controller;

import com.example.Models.LoginSection.Password.CheckIfPasswordsEqual.CheckIfPasswordsEqual;
import com.example.Models.LoginSection.Password.PasswordAdherence.PasswordAdherence;
import com.example.Models.LoginSection.Username.UsernameValidator;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CreateNewUserController {

    private static WarriorDaoServiceImplementation warriorDaoServiceImplementation = new WarriorDaoServiceImplementation();
    private Gson gson = new Gson();
    private String clientsUsername, password, passwordRepeat, clientsImageUrl;
    private HashMap<String, Object> userAvatarDetails = new HashMap<>();


    @RequestMapping(value="/createUser", method=RequestMethod.POST)
    public void accountCreation(@RequestBody Map<String, String> userDetails, HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("CREATE NEW USER");
        this.getUserDetails(userDetails);
        InitializeResponse.initialize(res);

        if (UsernameValidator.isValid(clientsUsername, (ArrayList<Warrior>) warriorDaoServiceImplementation.getAll())) {
            if (CheckIfPasswordsEqual.passwordChecker(password, passwordRepeat) && PasswordAdherence.isValid(password)) {
                this.createNewAvatar(res);
            } else {
                this.passwordFailure(res);
            }
        } else {
            this.usernameFailure(res);
        }
    }

    private void getUserDetails(Map<String, String> userDetails){
        clientsUsername = userDetails.get("username");
        password = userDetails.get("passwordX");
        passwordRepeat = userDetails.get("passwordY");
        clientsImageUrl= userDetails.get("image");
    }

    private void createNewAvatar(HttpServletResponse res) throws IOException {

        Warrior userAvatar;

        if (this.clientsImageUrl.equalsIgnoreCase("")) {
            userAvatar = new Warrior(clientsUsername, password);
            System.out.println("USER HAD NO IMAGE");
        } else {
             userAvatar = new Warrior(clientsUsername,100, password, clientsImageUrl);
        }
        setOnlineAndSetLastActive(userAvatar);
        warriorDaoServiceImplementation.addObject(userAvatar);
        System.out.println("WE HAVE JUST CREATED A NEW USER "+userAvatar);
        userAvatarDetails.put("message", "success");
        userAvatarDetails.put("clientData", userAvatar);
        String jsonStringOfUserAvatar = gson.toJson(userAvatarDetails);
        res.getWriter().write(jsonStringOfUserAvatar);
    }

    private void passwordFailure(HttpServletResponse res) throws IOException {
        System.out.println("PASSWORD FAILURE");
        userAvatarDetails.put("message", "passwordError");
        String jsonStringOfWrongMessage = gson.toJson(userAvatarDetails);
        res.getWriter().write(jsonStringOfWrongMessage);
    }

    private void usernameFailure(HttpServletResponse res) throws  IOException {
        System.out.println("USERNAME FAILURE");
        userAvatarDetails.put("message", "usernameError");
        String jsonStringOfWrongMessage = gson.toJson(userAvatarDetails);
        res.getWriter().write(jsonStringOfWrongMessage);
    }

    private void setOnlineAndSetLastActive(Warrior warrior) {
        warrior.setOnline(true);
        warrior.setLastActive(String.valueOf(Calendar.getInstance().getTime().getTime()));
    }
}

package com.example.Models.LoginSection.Login;

import com.example.Models.LoginSection.Password.CheckIfPasswordsEqual.CheckIfPasswordsEqual;
import com.example.Models.Warrior.Warrior;
import com.example.Services.DaoServices.WarriorDaoServiceImplementation;
import com.google.gson.Gson;
import java.util.HashMap;

public class Login {

    //CHECKED STILL DONT LIKE
    //NOT TESTED

    private static WarriorDaoServiceImplementation warriorDaoServiceImplementation = new WarriorDaoServiceImplementation();
    private static HashMap<Object,Object> response = new HashMap<>();
    private static Gson gson = new Gson();
    private static String jsonStringOfResponse;

    public static String checkCredentials(String username, String password) {

        if (username.equals("admin") && password.equals("admin")) {
            return createResponseIfAdminLoginRequest("admin");
        }

        Object user = warriorDaoServiceImplementation.getObjectWithCredentials(username);
        return createUserResponseForLoginRequest(password, user);
    }

    private static String createResponseIfAdminLoginRequest(String admin) {
        response.put("message", admin);
        jsonStringOfResponse = gson.toJson(response);
        return jsonStringOfResponse;
    }


    private static String createUserResponseForLoginRequest(String password, Object user) {
        if (user == null)  {
            return createResponseIfUserIsNull();
        } else {
            //SETTING WARRIOR ONLINE AND SAVING THIS DATA INTO THE DB SO THAT WHEN I CALL UPON THE WARRIOR DB THIS WARRIOR WILL BE SET ONLINE
            Warrior usersAvatar = (Warrior)user;
            if (CheckIfPasswordsEqual.passwordChecker(usersAvatar.getPassword(), password)) {
                usersAvatar.setOnline(true);
                warriorDaoServiceImplementation.updateObject(usersAvatar.getName(), usersAvatar);
                System.out.println("Users password is "+ usersAvatar.getPassword() + " password entered was " + password);
                //usersAvatar.setPassword("");//setting password to empty as this object will be sent to client side cant risk password being shown
                return createResponseIfUsersDetailsCorrect(usersAvatar);
            } else {
                return createResponseIfUserIsNull();
            }
        }
    }


    private static String createResponseIfUsersDetailsCorrect(Warrior usersAvatar) {
        response.put("message",usersAvatar);
        jsonStringOfResponse = gson.toJson(response);
        return jsonStringOfResponse;
    }


    private static String createResponseIfUserIsNull() {
        response.put("message","failure");
        jsonStringOfResponse = gson.toJson(response);
        return jsonStringOfResponse;
    }
}

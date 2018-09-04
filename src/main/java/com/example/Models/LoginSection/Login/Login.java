package com.example.Models.LoginSection.Login;

import com.example.Models.LoginSection.Password.CheckIfPasswordsEqual.CheckIfPasswordsEqual;
import com.example.Models.Warrior.Warrior;
import com.example.Services.DaoServices.WarriorDaoServiceImplementation;
import com.google.gson.Gson;

import java.util.Date;
import java.util.HashMap;

public class Login {

    //CHECKED ALL GOOD FOR HEROKU
    //CHECKED STILL DONT LIKE BEACUSE ITS UGLY
    //NOT TESTED

    private static WarriorDaoServiceImplementation warriorDaoServiceImplementation = new WarriorDaoServiceImplementation();
    private static HashMap<Object,Object> response = new HashMap<>();
    private static Gson gson = new Gson();
    private static String jsonStringOfResponse;

    public static String checkCredentials(String username, String password) {

        if (username.equals("admin") && password.equals("admin")) {

            return createResponseIfAdminLoginRequest("admin");

        }

        if (checkIfUserAndPasswordExistInDB(username, password)) {

            Object user = warriorDaoServiceImplementation.getObjectWithCredentials(username);

            return createUserResponseForLoginRequest(password, user);

        } else {

            return createResponseIfUserIsNull();
        }
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

                //MAKE SURE WE UPDATE THE LAST ACTIVE TIME FOR WARRIOR
                usersAvatar.setLastActive(String.valueOf(new Date().getTime()));

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

        response.put("message", usersAvatar);

        jsonStringOfResponse = gson.toJson(response);

        return jsonStringOfResponse;
    }



    private static String createResponseIfUserIsNull() {

        response.put("message", "failure");

        jsonStringOfResponse = gson.toJson(response);

        return jsonStringOfResponse;
    }


    private static boolean checkIfUserAndPasswordExistInDB(String username, String password) {

        Warrior warrior = (Warrior) warriorDaoServiceImplementation.getObjectWithCredentials(username);


        if (warrior.getName() == null) {

            return false;

        }

        if (!warrior.getPassword().equals(password)) {

            return false;

        }

        return true;

    }
}

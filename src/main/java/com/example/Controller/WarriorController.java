package com.example.Controller;

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
import java.util.HashMap;

@RestController
public class WarriorController {

    private static WarriorDaoServiceImplementation warriorDaoServiceImplementation = new WarriorDaoServiceImplementation();
    private String[] servletPath;
    String idOfOject;
    private Gson gson = new Gson();
    private HashMap<String, String> response = new HashMap<>();


    @RequestMapping(value="api/add/warrior", method=RequestMethod.POST)
    public void addWarrior(@RequestBody Warrior warrior, HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("ADD WARRIOR");
        System.out.println(warrior);
        warriorDaoServiceImplementation.addObject(warrior);
        InitializeResponse.initialize(res);
        response.put("message","success");
        res.getWriter().write(gson.toJson(response));
    }

    @RequestMapping(value="api/update/warrior", method=RequestMethod.PUT)
    public void updateWarrior(@RequestBody Warrior warrior, HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("UPDATE WARRIOR");
        System.out.println(warrior);
        System.out.println(warrior.getOperationList());

        System.out.println(warrior.isOnOperation());

        String ObjectAsString = gson.toJson(warrior);
        warriorDaoServiceImplementation.updateObject(warrior.getName(), warrior);
        InitializeResponse.initialize(res);
        res.getWriter().write(ObjectAsString);
    }

    @RequestMapping(value="/api/get/warrior/{id}", method=RequestMethod.GET)
    public void getWarrior(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("GET WARRIOR");
        servletPath = req.getServletPath().split("/");
        String name = servletPath[4];
        Warrior warrior = null;

        boolean success = false;
        try{
            idOfOject = servletPath[4];
            warrior = (Warrior)warriorDaoServiceImplementation.getObject(idOfOject);
            success = true;
        }catch(Exception e){};
        if(!success){
            warrior = (Warrior)warriorDaoServiceImplementation.getObject(name);
        }

        String jsonStringOfWarrior = gson.toJson(warrior);
        InitializeResponse.initialize(res);
        res.getWriter().write(jsonStringOfWarrior);
    }

    @RequestMapping(value="/api/getAll/warrior", method=RequestMethod.GET)
    public void getAllWarrior(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("GET ALL WARRIOR");
        ArrayList<Warrior> warriorList = (ArrayList<Warrior>)warriorDaoServiceImplementation.getAll();
        String jsonStringOfWarriorList = gson.toJson(warriorList);
        InitializeResponse.initialize(res);
        res.getWriter().write(jsonStringOfWarriorList);
    }

    @RequestMapping(value="/api/getAll/warrior/except/{you}", method=RequestMethod.GET)
    public void getAllWarriorExceptYou(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("GET ALL WARRIOR EXCEPT");
        servletPath = req.getServletPath().split("/");
        idOfOject = servletPath[5];
        ArrayList<Warrior> warriorList = (ArrayList<Warrior>)warriorDaoServiceImplementation.getAllExcept(idOfOject);
        String jsonStringOfWarriorList = gson.toJson(warriorList);
        InitializeResponse.initialize(res);
        res.getWriter().write(jsonStringOfWarriorList);
    }

    @RequestMapping(value="/api/warrior/battlereceipt/you}", method=RequestMethod.GET)
    public void getBattleReceipts(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("GET ALL WARRIOR EXCEPT");
        servletPath = req.getServletPath().split("/");
        idOfOject = servletPath[4];
        Warrior warrior = (Warrior) warriorDaoServiceImplementation.getObject(idOfOject);
        String jsonStringOfWarriorList = gson.toJson(warrior);
        InitializeResponse.initialize(res);
        res.getWriter().write(jsonStringOfWarriorList);
    }

    @RequestMapping(value="/api/getAll/warrior/{lowerLimit}", method=RequestMethod.GET)
    public void getWarriorsInBatchOf10s(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("GET ALL WARRIOR IN BATCHES 10");
        servletPath = req.getServletPath().split("/");
        int lowerLimit = Integer.parseInt(servletPath[4]);
        ArrayList<Warrior> warriorList = (ArrayList<Warrior>)warriorDaoServiceImplementation.getAll();
        ArrayList<Warrior> limitedArray = new ArrayList<>();

        if (lowerLimit > warriorList.size()) {
            System.out.println("LOWER LIMIT BIGGER THAN WARRIORLIST SIZE");
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "failure");
            String jsonStringOfWarriorListLimited = gson.toJson(response);
            InitializeResponse.initialize(res);
            res.getWriter().write(jsonStringOfWarriorListLimited);
        } else {
            System.out.println("LOWER LIMIT LOWER THAN WARRIORLIST SIZE");
            for (int i = lowerLimit; i < warriorList.size(); i++) {
                limitedArray.add(warriorList.get(i));
                if (i == 9) {
                    break;
                }
            }
            System.out.println(limitedArray.toString());
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "success");
            response.put("warriorListData", limitedArray);
            String jsonStringOfWarriorListLimited = gson.toJson(response);
            InitializeResponse.initialize(res);
            res.getWriter().write(jsonStringOfWarriorListLimited);
        }
    }

    @RequestMapping(value="api/remove/warrior/{id}", method=RequestMethod.DELETE)
    public void removeWarrior(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("REMOVE WARRIOR");
        servletPath = req.getServletPath().split("/");
        idOfOject = servletPath[4];
        warriorDaoServiceImplementation.removeObject(idOfOject);
        InitializeResponse.initialize(res);
        response.put("message","success");
        res.getWriter().write(gson.toJson(response));
    }

    @RequestMapping(value="api/removeAll/warrior", method=RequestMethod.DELETE)
    public void removeAllWarrior(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("REMOVE ALL WARRIOR");
        warriorDaoServiceImplementation.removeAll();
        InitializeResponse.initialize(res);
        response.put("message","success");
        res.getWriter().write(gson.toJson(response));
    }

    @RequestMapping(value="/api/logout/warrior/{you}", method=RequestMethod.PUT)
    public void logoutWarrior(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("LOGOUT WARRIOR");
        servletPath = req.getServletPath().split("/");
        idOfOject = servletPath[4];
        Warrior warrior = (Warrior) warriorDaoServiceImplementation.getObject(idOfOject);
        warrior.setOnline(false);
        warriorDaoServiceImplementation.updateObject(warrior.getName(), warrior);
//        warriorDaoServiceImplementation.logout(idOfOject);
        String jsonStringOfWarriorList = gson.toJson(warrior);
        InitializeResponse.initialize(res);
        res.getWriter().write(jsonStringOfWarriorList);
    }

    @RequestMapping(value="api/update/warrior/bag/money", method=RequestMethod.PUT)
    public void updateWarriorBagAndMoney(@RequestBody Warrior warrior, HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("UPDATE WARRIOR BAG MONEY");
        Warrior warriorInDB = (Warrior) warriorDaoServiceImplementation.getObject(warrior.getName());
        warriorInDB.setBag(warrior.getBag());
        warriorInDB.setMoney(warrior.getMoney());
        String ObjectAsString = gson.toJson(warriorInDB);
        warriorDaoServiceImplementation.updateObject(warriorInDB.getName(), warriorInDB);
        InitializeResponse.initialize(res);
        res.getWriter().write(ObjectAsString);
    }
}

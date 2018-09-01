package com.example.Controller;


import com.example.Models.OperationSection.Operation.Operation;
import com.example.Models.OperationSection.OperationGenerator.OperationGenerator;
import com.example.Models.Other.InitializeResponse;
import com.example.Models.Other.LocationPrinter;
import com.example.Services.DaoServices.OperationDaoServiceImplementation;
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
public class OperationController {

    private static OperationDaoServiceImplementation operationDaoServiceImplementation = new OperationDaoServiceImplementation();
    private String[] servletPath;
    String idOfOject;
    private Gson gson = new Gson();
    private HashMap<String, String> response = new HashMap<>();


    @RequestMapping(value="api/add/operation", method=RequestMethod.POST)
    public void addMission(@RequestBody Operation operation, HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("ADD OPERATION");
        operationDaoServiceImplementation.addObject(operation);
        InitializeResponse.initialize(res);
        response.put("message","success");
        res.getWriter().write(gson.toJson(response));
    }

    @RequestMapping(value="api/update/operation", method=RequestMethod.PUT)
    public void updateMission(@RequestBody Operation operation, HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("UPDATE OPERATION");
        operationDaoServiceImplementation.updateObject(operation.getName(), operation);
        InitializeResponse.initialize(res);
        response.put("message","success");
        res.getWriter().write(gson.toJson(response));
    }

    @RequestMapping(value="/api/get/operation/{id}", method=RequestMethod.GET)
    public void getMission(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("GET OPERATION");
        servletPath = req.getServletPath().split("/");
        idOfOject = servletPath[4];
        Operation operation = (Operation) operationDaoServiceImplementation.getObject(idOfOject);
        String jsonStringOfMission = gson.toJson(operation);
        InitializeResponse.initialize(res);
        res.getWriter().write(jsonStringOfMission);
    }
    @RequestMapping(value="/api/get/operation/random/{warriorlevel}", method=RequestMethod.GET)
    public void getDynamicallyGeneratedMission(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("GET RANDOM OPERATIONS");
        servletPath = req.getServletPath().split("/");
        int warriorLevel = Integer.parseInt(servletPath[5]);
        ArrayList<Operation> operationList = OperationGenerator.generateMissions(warriorLevel, 10);
        String jsonStringOfMissionList = gson.toJson(operationList);
        InitializeResponse.initialize(res);
        res.getWriter().write(jsonStringOfMissionList);
    }

    @RequestMapping(value="/api/getAll/operation", method=RequestMethod.GET)
    public void getAllMission(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("GET ALL OPERATION");
        ArrayList<Operation> operationList = (ArrayList<Operation>) operationDaoServiceImplementation.getAll();
        String jsonStringOfMissionList = gson.toJson(operationList);
        InitializeResponse.initialize(res);
        res.getWriter().write(jsonStringOfMissionList);
    }

    @RequestMapping(value="api/remove/operation/{id}", method=RequestMethod.DELETE)
    public void removeMission(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("REMOVE OPERATION");
        servletPath = req.getServletPath().split("/");
        idOfOject = servletPath[4];
        operationDaoServiceImplementation.removeObject(idOfOject);
        InitializeResponse.initialize(res);
        response.put("message","success");
        res.getWriter().write(gson.toJson(response));
    }

    @RequestMapping(value="api/removeAll/operation", method=RequestMethod.DELETE)
    public void removeAllMission(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocationPrinter.printLocation("REMOVE ALL OPERATION");
        operationDaoServiceImplementation.removeAll();
        InitializeResponse.initialize(res);
        response.put("message","success");
        res.getWriter().write(gson.toJson(response));
    }

}

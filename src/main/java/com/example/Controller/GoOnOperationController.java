package com.example.Controller;

import com.example.Models.OperationSection.OnOperation.OnOperation;
import com.example.Models.OperationSection.Operation.Operation;
import com.example.Models.OperationSection.OperationRewardIssuer.OperationRewardIssuer;
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
import java.util.HashMap;

@RestController
public class GoOnOperationController {

    private static WarriorDaoServiceImplementation warriorDaoServiceImplementation = new WarriorDaoServiceImplementation();
    private String[] servletPath;
    private Gson gson = new Gson();
    private String playerName;
    private String operationName;
    private String messageResponseJSON;


    @RequestMapping(value = "/GoOnOperation/{playerName}/{operationName}", method=RequestMethod.POST)
    public void goOnOperation(@RequestBody Operation operation, HttpServletResponse res, HttpServletRequest req) throws IOException {
        LocationPrinter.printLocation("GO ON OPERATION CONTROLLER");
        this.getPlayerAndOperationNameFromPath(req);
        Warrior playerAvatar = (Warrior) warriorDaoServiceImplementation.getObject(playerName);
        Operation playerOperation = operation;
        InitializeResponse.initialize(res);
        if(this.checkIfAlreadyOnAOperation(playerAvatar)){
            HashMap<String,Object> messageResponse = new HashMap<>();
            messageResponse.put("message","false");
            messageResponseJSON = gson.toJson(messageResponse);
            res.getWriter().write(messageResponseJSON);
        }else{
            HashMap<String,Object> messageResponse = new HashMap<>();
            this.initializeOperation(operation);
            OnOperation.goOnOperation(playerAvatar, playerOperation);
            OnOperation.calculateWhetherOperationWasSuccessfulOrNot();
            messageResponse.put("message", "success");
            messageResponse.put("userData", playerAvatar);
            messageResponseJSON = gson.toJson(messageResponse);
            System.out.println(messageResponse);
            System.out.println("data being sent back");
            warriorDaoServiceImplementation.updateObject(playerAvatar.getName(), playerAvatar);
            // make sure if user clicks again it updates the previous Operation
            res.getWriter().write(messageResponseJSON);
        }
    }

    @RequestMapping(value = "/operationComplete/{playerName}",method=RequestMethod.GET)
    public void checkIfOperationIsComplete(HttpServletResponse res, HttpServletRequest req) throws IOException {
        servletPath = req.getServletPath().split("/");
        String playerName = servletPath[2];
        System.out.println("Player name: "+playerName);
        Warrior playerAvatar = (Warrior)warriorDaoServiceImplementation.getObject(playerName);
        InitializeResponse.initialize(res);

        String endTimeString = playerAvatar.getOperationList().get(0).getEndTime();
        Long endTimeLong = Long.parseLong(endTimeString);
        if (new Date().getTime() > endTimeLong){

            System.out.println("OPERATION COMPLETE");//need to sort this out here so that user doesnt get duplicate items
            playerAvatar.setOnOperation(false);
            playerAvatar.getOperationList().get(0).setActive(false);

            if (playerAvatar.getOperationList().get(0).isSuccessful()){
                OperationRewardIssuer.issueOperationRewards(playerAvatar);
                playerAvatar.getOperationList().remove(0);
                warriorDaoServiceImplementation.updateObject(playerAvatar.getName(), playerAvatar);
                constructResponseMessage("completed",true, playerAvatar);
            } else {
                playerAvatar.getOperationList().remove(0);
                warriorDaoServiceImplementation.updateObject(playerAvatar.getName(), playerAvatar);
                constructResponseMessage("completed",false, playerAvatar);
            }
            res.getWriter().write(messageResponseJSON);
        }else{
            HashMap<String,Object> messageResponse = new HashMap<>();
            messageResponse.put("message","not completed");
            String messageResponseJSON = gson.toJson(messageResponse);
            res.getWriter().write(messageResponseJSON);
        }
    }

    private void initializeOperation(Operation operation) {
        //HAVE TO CONVERT LONG TO STRINGS AS GSONS STRUGGLES WITH IT WHEN CONVERTING TO JAVA OBJECT
        Long startTime = new Date().getTime();
        String startTimeString = startTime.toString();
        operation.setStartTime(startTimeString);

        Long endTime = startTime + (operation.getDuration() * 60000);
        String endTimeString = endTime.toString();
        operation.setEndTime(endTimeString);
        operation.setActive(true);
    }

    private boolean checkIfAlreadyOnAOperation(Warrior warrior) {
        return warrior.getOperationList().size() > 0;
    }

    private void getPlayerAndOperationNameFromPath(HttpServletRequest req) {
        servletPath = req.getServletPath().split("/");
        playerName = servletPath[2];
        operationName = servletPath[3];
        System.out.println("Player name: " + playerName + " Operation name: " + operationName);
    }

    private void constructResponseMessage(String message, Boolean success, Object playerAvatar) {
        HashMap<String,Object> response = new HashMap<>();
        response.put("message", message);
        response.put("success", success);
        response.put("avatar", playerAvatar);
        messageResponseJSON = gson.toJson(response);
    }
}


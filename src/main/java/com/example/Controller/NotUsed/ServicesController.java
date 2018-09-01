package WarVersion1.Controller.NotUsed;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServicesController {

//    private static WarriorServiceImplementation warriorServiceImplementation = new WarriorServiceImplementation();
//    private static WeaponServiceImplementation weaponServiceImplementation = new WeaponServiceImplementation();
//    private static OperationServiceImplementation missionServiceImplementation = new OperationServiceImplementation();
//    private String[] servletPath;
//    String typeOfObject;
//    int idOfOject;
//
//    @RequestMapping(value="api/add/{model}", method=RequestMethod.POST)
//    public void addObject(Object object, HttpServletRequest req, HttpServletResponse res){
//        servletPath = req.getServletPath().split("/");
//        typeOfObject = servletPath[3];
//
//        if(typeOfObject.equals("warrior")){
//            warriorServiceImplementation.addObject(object);
//        }
//
//        if(typeOfObject.equals("weapon")){
//            weaponServiceImplementation.addObject(object);
//        }
//
//        if(typeOfObject.equals("mission")){
//            missionServiceImplementation.addObject(object);
//        }
//    }
//
//    @RequestMapping(value="api/update/{model}", method=RequestMethod.PUT)
//    public void updateObject(Object object, HttpServletRequest req, HttpServletResponse res){
//        servletPath = req.getServletPath().split("/");
//        typeOfObject = servletPath[3];
//
//        if(typeOfObject.equals("warrior")){
//            warriorServiceImplementation.updateObject(((Warrior)object).getId(),object);
//        }
//
//        if(typeOfObject.equals("weapon")){
//            weaponServiceImplementation.updateObject(((Weapon)object).getId(),object);
//        }
//
//        if(typeOfObject.equals("mission")){
//            missionServiceImplementation.updateObject(((Operation)object).getId(),object);
//        }
//    }
//
//    @RequestMapping(value="/api/get/{model}/{id}", method=RequestMethod.GET)
//    public void getWarrior(HttpServletRequest req, HttpServletResponse res){
//        servletPath = req.getServletPath().split("/");
//        typeOfObject = servletPath[3];
//        idOfOject = Integer.parseInt(servletPath[4]);
//
//        if(typeOfObject.equals("warrior")){
//            warriorServiceImplementation.getObject(idOfOject);
//        }
//
//        if(typeOfObject.equals("weapon")){
//            weaponServiceImplementation.getObject(idOfOject);
//        }
//
//        if(typeOfObject.equals("mission")){
//            missionServiceImplementation.getObject(idOfOject);
//        }
//    }
//
//    @RequestMapping(value="/api/getAll/{model}", method=RequestMethod.GET)
//    public void getAllWarrior(HttpServletRequest req, HttpServletResponse res){
//        servletPath = req.getServletPath().split("/");
//        typeOfObject = servletPath[3];
//
//        if(typeOfObject.equals("warrior")){
//            warriorServiceImplementation.getAll();
//        }
//
//        if(typeOfObject.equals("weapon")){
//            weaponServiceImplementation.getAll();
//        }
//
//        if(typeOfObject.equals("mission")){
//            missionServiceImplementation.getAll();
//        }
//    }
//
//    @RequestMapping(value="api/remove/{model}/{id}", method=RequestMethod.DELETE)
//    public void removeObject(HttpServletRequest req, HttpServletResponse res){
//        servletPath = req.getServletPath().split("/");
//        typeOfObject = servletPath[3];
//        idOfOject = Integer.parseInt(servletPath[4]);
//
//
//        if(typeOfObject.equals("warrior")){
//            warriorServiceImplementation.removeObject(idOfOject);
//        }
//
//        if(typeOfObject.equals("weapon")){
//            weaponServiceImplementation.removeObject(idOfOject);
//        }
//
//        if(typeOfObject.equals("mission")){
//            missionServiceImplementation.removeObject(idOfOject);
//        }
//    }
//
//    @RequestMapping(value="api/removeAll/{model}", method=RequestMethod.DELETE)
//    public void removeAllObject(Object object, HttpServletRequest req, HttpServletResponse res){
//        servletPath = req.getServletPath().split("/");
//        typeOfObject = servletPath[3];
//
//        if(typeOfObject.equals("warrior")){
//            warriorServiceImplementation.removeAll();
//        }
//
//        if(typeOfObject.equals("weapon")){
//            weaponServiceImplementation.removeAll();
//        }
//
//        if(typeOfObject.equals("mission")){
//            missionServiceImplementation.removeAll();
//        }
//    }


}

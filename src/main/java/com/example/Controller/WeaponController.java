
//import WarVersion1.Models.Other.InitializeResponse;
//import WarVersion1.Models.Other.LocationPrinter;
//import WarVersion1.Models.ItemSection.Weapon.Weapon;
//import WarVersion1.Services.DaoServices.WeaponDaoServiceImplementation;
//import com.google.gson.Gson;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//@RestController
//public class WeaponController {
//
//    private static WeaponDaoServiceImplementation weaponDaoServiceImplementation = new WeaponDaoServiceImplementation();
//    private String[] servletPath;
//    private String idOfOject;
//    private Gson gson = new Gson();
//    private HashMap<String, String> response = new HashMap<>();
//
//
//    @RequestMapping(value="api/add/weapon", method=RequestMethod.POST)
//    public void addWeapon(Object object, HttpServletRequest req, HttpServletResponse res) throws IOException {
//        LocationPrinter.printLocation("ADD WEAPON");
//        weaponDaoServiceImplementation.addObject(object);
//        InitializeResponse.initialize(res);
//        response.put("message","success");
//        res.getWriter().write(gson.toJson(response));
//    }
//
//    @RequestMapping(value="api/update/weapon", method=RequestMethod.PUT)
//    public void updateWeapon(Object object, HttpServletRequest req, HttpServletResponse res) throws IOException {
//        LocationPrinter.printLocation("UPDATE WEAPON");
//        weaponDaoServiceImplementation.updateObject(((Weapon)object).getName(), object);
//        InitializeResponse.initialize(res);
//        response.put("message","success");
//        res.getWriter().write(gson.toJson(response));
//    }
//
//    @RequestMapping(value="/api/get/weapon/{id}", method=RequestMethod.GET)
//    public void getWeapon(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        LocationPrinter.printLocation("GET WEAPON");
//        servletPath = req.getServletPath().split("/");
//        idOfOject = servletPath[4];
//        Weapon weapon = (Weapon)weaponDaoServiceImplementation.getObject(idOfOject);
//        String jsonStringOfWeapon = gson.toJson(weapon);
//        InitializeResponse.initialize(res);
//        res.getWriter().write(jsonStringOfWeapon);
//    }
//
//    @RequestMapping(value="/api/getAll/weapon", method=RequestMethod.GET)
//    public void getAllWeapon(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        LocationPrinter.printLocation("GET ALL WEAPON");
//        ArrayList<Weapon> weaponList = (ArrayList<Weapon>)weaponDaoServiceImplementation.getAll();
//        String jsonStringOfWeaponList = gson.toJson(weaponList);
//        InitializeResponse.initialize(res);
//        res.getWriter().write(jsonStringOfWeaponList);
//    }
//
//    @RequestMapping(value="api/remove/weapon/{id}", method=RequestMethod.DELETE)
//    public void removeWeapon(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        LocationPrinter.printLocation("REMOVE WEAPON");
//        servletPath = req.getServletPath().split("/");
//        idOfOject = servletPath[4];
//        weaponDaoServiceImplementation.removeObject(idOfOject);
//        InitializeResponse.initialize(res);
//        response.put("message","success");
//        res.getWriter().write(gson.toJson(response));
//    }
//
//    @RequestMapping(value="api/removeAll/weapon", method=RequestMethod.DELETE)
//    public void removeAllWeapon(Object object, HttpServletRequest req, HttpServletResponse res) throws IOException {
//        LocationPrinter.printLocation("REMOVE ALL WEAPON");
//        weaponDaoServiceImplementation.removeAll();
//        InitializeResponse.initialize(res);
//        response.put("message","success");
//        res.getWriter().write(gson.toJson(response));
//    }
//}

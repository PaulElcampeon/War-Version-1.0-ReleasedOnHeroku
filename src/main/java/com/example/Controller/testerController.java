package com.example.Controller;

import org.springframework.web.bind.annotation.*;

@RestController//(value="/api")
public class testerController {

    @RequestMapping(value="/hello", method=RequestMethod.GET)
    public String getWarriors() {
        System.out.println("jjjeeeeeeeeeee");
        return "no";

    }

}

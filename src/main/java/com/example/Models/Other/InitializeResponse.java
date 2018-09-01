package com.example.Models.Other;

import javax.servlet.http.HttpServletResponse;

public class InitializeResponse {

    public static void initialize(HttpServletResponse res){
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
    }
}

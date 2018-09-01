package com.example.Services.LoginServices;

import com.example.Models.LoginSection.Login.Login;

public class LoginServiceImplementation implements LoginService {


    @Override
    public String checkCredentials(String username, String password) {
        return Login.checkCredentials(username,password);
    }
}

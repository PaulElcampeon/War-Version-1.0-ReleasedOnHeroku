package com.example.Models.LoginSection.Password.CheckIfPasswordsEqual;

public class CheckIfPasswordsEqual {


    public static boolean passwordChecker(String password1, String password2){
      if(password1.equals(password2)){
          return true;
      }
      return false;
    }
}

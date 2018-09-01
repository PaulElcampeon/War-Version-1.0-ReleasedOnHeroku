package com.example.Models.LoginSection.Password.PasswordAdherence;

import java.util.regex.Pattern;

public class PasswordAdherence {


    public static boolean isValid(String password) {
        return containsLetters(password) && containsDigits(password) && containsFiveCharacters(password) && !containsSpecialCharacters(password);
    }

    public static boolean containsFiveCharacters(String password) {
        return password.length() == 5;
    }

    public static boolean containsLetters(String password) {
        Pattern upperOrLowerCasePatten = Pattern.compile("[A-Za-z]");
        return upperOrLowerCasePatten.matcher(password).find();
    }

    public static boolean containsDigits(String password) {
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        return digitCasePatten.matcher(password).find();
    }

    public static boolean containsSpecialCharacters(String password) {
        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        return specialCharPatten.matcher(password).find();
    }
}

package com.example.Services.TitleServices;


import com.example.Models.Title.TitleIssuer;
import com.example.Models.Warrior.Warrior;

public class TitleServiceImplemented implements TitleService {

    @Override
    public void issueTitle(Warrior warrior) {
        TitleIssuer.issueTitle(warrior);
    }
}

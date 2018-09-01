package com.example.Models.BattleSection.BattleReceipt;

import com.example.Models.BattleSection.Battle.Battle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BattleReceipt {

    //CHECKED
    private String victor;
    private String loser;
    private String dateOfBattle;

    public BattleReceipt() {};

    public BattleReceipt(Battle battle) {
        this.victor = battle.getVictor().getName();
        this.loser = battle.getLoser().getName();
        this.dateOfBattle = battle.getDateAsString();
    }


    public String toString(){
        return "Victor: "+this.victor+", Loser: "+this.loser+", Date: "+this.dateOfBattle;
    }

}

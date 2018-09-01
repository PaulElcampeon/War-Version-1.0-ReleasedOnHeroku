package com.example.Comparator.WarriorComparator;

import com.example.Models.Warrior.Warrior;

import java.util.Comparator;

public class WarriorComparator implements Comparator<Warrior> {

    @Override
    public int compare(Warrior o1, Warrior o2) {
        int level1 = o1.getLevel();
        int level2 = o2.getLevel();
        int victories1 = o1.getVictories();
        int victories2 = o2.getVictories();

        if (level1 > level2) {
            return -1;
        }else if (level1 < level2){
            return 1;
        } else { //means there is a draw
            if (victories1 > victories2) { //could also have Integer.compare(victories1,vicotires2)
                return -1;
            } else if (victories1 < victories2) {
                return 1;
            }else {
                return 0;
            }
        }
    }
}

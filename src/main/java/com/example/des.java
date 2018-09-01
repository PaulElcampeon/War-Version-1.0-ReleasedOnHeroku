package WarVersion1;

import java.util.*;

public class des {
    public static void main(String[] args){
//        List<Warrior> listWarriors = new ArrayList<Warrior>();
//
//        listWarriors.add(new Warrior(TitleEnum.PRIVATE, 5, 200));
//        listWarriors.add(new Warrior(TitleEnum.CORPORAL, 4, 200));
//        listWarriors.add(new Warrior(TitleEnum.COMMANDER, 7, 200));
//        listWarriors.add(new Warrior(TitleEnum.PRIVATE, 4, 200));
//
//        Collections.sort(listWarriors,new WarriorComparator());
//
//        for (Warrior warrior : listWarriors){
//            System.out.println(warrior);
//        }

        Calendar cald1 = Calendar.getInstance();
        Long time1 = cald1.getTimeInMillis();
//        Long tim2 = new Date().getTime();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Calendar cald2 = Calendar.getInstance();
        Long time2 = cald2.getTimeInMillis();
//        Long tim3 = new Date().getTime();


        System.out.println(time1);
        System.out.println(time2);
        System.out.println(time2 - time1);
//        System.out.println(time2);



    }
}

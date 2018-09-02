package com.example;

import com.example.Models.CheckForInactiveUsers.CheckForInactiveUsers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
public class Main {

    public static void main(String args[]) throws IOException {
        SpringApplication.run(Main.class,args);

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable periodicTask = new Runnable() {
            public void run() {
                // Invoke method(s) to do the work
                CheckForInactiveUsers.setToOffLineInActiveUsers();
                Thread.currentThread().getName();
            }
        };

        executor.scheduleAtFixedRate(periodicTask, 0, 600, TimeUnit.SECONDS);//every 10 minutes

    }

    //elxir and weapons and armour creation were not random were not random
    //messaging service
    //combine armour
    //combine weapon
    //combine elixir

}

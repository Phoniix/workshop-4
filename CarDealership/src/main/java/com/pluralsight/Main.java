package com.pluralsight;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException, ParseException {
        Dealership dealership = new Dealership("Test", "123 Main Street", "(123) 456-7890");
        DealershipFileManager dPuller = new DealershipFileManager(01);
        dealership.getAllVehicles(01);

        for (Vehicle vehicle : dealership.getAllVehicles(01)) {
            System.out.println(vehicle.toString());
        }
    }



//    public static char welcomeScreen (Scanner scanner, String appWelcomeMessage, String actionTypeSplitByPipes) throws IOException, InterruptedException {
//        return MenuOptions.menuTemplate(scanner, appWelcomeMessage, actionTypeSplitByPipes);
//
//    }




}

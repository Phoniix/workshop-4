package com.pluralsight.design_and_ui;

import com.pluralsight.dealership.Dealership;
import com.pluralsight.dealership.DealershipFileManager;

import java.io.IOException;
import java.sql.PseudoColumnUsage;
import java.text.ParseException;
import java.util.Scanner;

public class MenuReferenceHandler {
    public static Scanner scanner = new Scanner(System.in);

    public static char loadDealership(Dealership currentDealer) throws IOException, InterruptedException {
        return MenuReference.menuTemplate(scanner, "Reset dealer.. Are you sure?", "ESSENTIAL|ENTRY_SCREEN");
    }
    public static char registerDealership (Dealership currentDealer) throws IOException, ParseException {
        return DealershipFileManager.registerDealership(scanner);
    }

    public static char startApp (Dealership currentDealer) throws IOException, ParseException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        currentDealer = null;
        char function = MenuReference.GENERAL_HOME.getMenuCode();
        boolean foundDealer = false;
        while (function != MenuReference.EXIT_APP.getMenuCode() || function !='X') {
            if (function == MenuReference.LOAD_DEALERSHIP_SCREEN.getMenuCode() || function == 'L') {
                currentDealer = DealershipFileManager.loadDealership(scanner);
                function = MenuReference.MAIN_MENU.getMenuCode();
            }
            if (function != 'X' || function != 'L' || function!= 'R') {
                function = MenuReference.fromCodeReturnAction(function, "ALL", currentDealer);
            }
            if (function == MenuReference.EXIT_APP.getMenuCode()) {
                function = MenuReference.fromCodeReturnAction('X', "ALL", currentDealer);
                break;
            }
        }
        return '0';
    }
    public static char mainMenu(Dealership currentDealer) throws IOException, InterruptedException {
        return MenuReference.mainMenu(scanner, "Welcome Dealer, " + currentDealer.getNAME() + "\n" +
                "What service do you need today?", "DEALER_ACTION|DEALER_MODIFY|ESSENTIAL|ENTRY_SCREEN");
    }
    public static char entryScreen (Dealership currentDealer) {
        return MenuReference.entryScreen(scanner, "Please select an option.", "ENTRY_SCREEN|REGISTER");
    }
    public static char exitApp(Dealership currentDealer) {
        MenuReference.exitApp("Thank you for using Dealership MGMT App");
        return 'X';
    }

    public static char displayTotalInventory (Dealership currentDealer) {
        return Dealership.processDisplayAllVehicles(scanner, currentDealer);
    }
    public static char displayByPriceRange (Dealership currentDealer) throws InterruptedException {
        return Dealership.processDisplayByPrice(scanner, currentDealer);
    }
    public static char displayByMakeModel (Dealership currentDealer) {
        return Dealership.processDisplayByMakeModel(scanner, currentDealer);
    }
    public static char displayByYear (Dealership currentDealer) {
        return Dealership.processDisplayByYear(scanner, currentDealer);
    }
    public static char displayByColor (Dealership currentDealer) {
        return Dealership.processDisplayByColor(scanner, currentDealer);
    }
    public static char displayByMileage (Dealership currentDealer) {
        return Dealership.processDisplayByMileage(scanner, currentDealer);
    }
    public static char displayByType (Dealership currentDealer) throws IOException, InterruptedException {
        return Dealership.processDisplayByType(scanner, currentDealer);
    }

    public static char addVehicleToInventory (Dealership currentDealer) throws IOException {
        return Dealership.processAddNewVehicle(scanner, currentDealer);
    }
    public static char removeVehicleFromInventory (Dealership currentDealer) {
        return Dealership.processRemoveVehicle(scanner, currentDealer);
    }
    public static char fillerFunctionDoesNotHaveValidReturn (Dealership currentDealer) {
        return '0';
    }

}

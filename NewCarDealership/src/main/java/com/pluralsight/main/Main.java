package com.pluralsight.main;

import com.pluralsight.dealership.Dealership;
import com.pluralsight.dealership.DealershipFileManager;
import com.pluralsight.dealership.dealershipUI.MenuReference;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static final String [] args = {"THE MAN"};

    public static void main(String[] args) throws InterruptedException, IOException, ParseException {
        Dealership currentDealer = null;
        Scanner scanner = new Scanner(System.in);
        currentDealer = null;
        char function = MenuReference.GENERAL_HOME.getMenuCode();
        boolean foundDealer = false;
        while (function != MenuReference.EXIT_APP.getMenuCode() || function !='X') {
            if (function == MenuReference.LOAD_DEALERSHIP_SCREEN.getMenuCode() || function == 'L') {
                currentDealer = DealershipFileManager.loadDealership(scanner);
                function = MenuReference.MAIN_MENU.getMenuCode();
            }
            if (function != 'X' || function != 'L') {
                function = MenuReference.fromCodeReturnAction(function, "ALL", currentDealer);
            }
            if (function == MenuReference.EXIT_APP.getMenuCode()) {
                function = MenuReference.fromCodeReturnAction('X', "ALL", currentDealer);
                break;
            }
        }
    }











}

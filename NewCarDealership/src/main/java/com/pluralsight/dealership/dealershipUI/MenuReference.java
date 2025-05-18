package com.pluralsight.dealership.dealershipUI;

import com.pluralsight.dealership.Dealership;
import com.pluralsight.design.Design;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public enum MenuReference {



    //FORBIDDEN CODES: 'X' '0' 'Z'

    // Essential Functions (Comment out GENERAL HOME as needed)
    MAIN_MENU("ESSENTIAL", '0', "MAIN MENU" , MenuReferenceHandler::mainMenu),
    EXIT_APP("ESSENTIAL", 'X', "EXIT APP", MenuReferenceHandler::exitApp),

    // Screens / Menu Grouping // -------------------------------------------------------------------------------------
    START_APP("START_APP", '0', "START APP", MenuReferenceHandler::startApp),
    LOAD_DEALERSHIP_SCREEN("ENTRY_SCREEN", 'L', "LOAD DEALERSHIP FROM ID", MenuReferenceHandler::loadDealership),
    REGISTER_DEALERSHIP("REGISTER", 'R', "REGISTER NEW DEALERSHIP", MenuReferenceHandler::registerDealership),
    GENERAL_HOME("FIRST_SCREEN", 'H', "CUSTOMER SCREEN", MenuReferenceHandler::entryScreen),

    // Screen: Dealership UI // ---------------------------------------------------------------------------------------
    DISPLAY_ALL_VEHICLES("DEALER_ACTION", 'A', "DISPLAY TOTAL INVENTORY", MenuReferenceHandler::displayTotalInventory),
    DISPLAY_BY_PRICE("DEALER_ACTION", 'P', "DISPLAY BY PRICE RANGE ENTRY", MenuReferenceHandler::displayByPriceRange),
    DISPLAY_BY_MAKE_MODEL("DEALER_ACTION", 'M', "DISPLAY BY MAKE / MODEL ENTRY", MenuReferenceHandler::displayByMakeModel),
    DISPLAY_BY_YEAR("DEALER_ACTION", 'Y', "DISPLAY BY YEAR ENTRY", MenuReferenceHandler::displayByYear),
    DISPLAY_BY_COLOR("DEALER_ACTION", 'C', "DISPLAY BY COLOR ENTRY", MenuReferenceHandler::displayByColor),
    DISPLAY_BY_MILEAGE("DEALER_ACTION", 'O', "DISPLAY BY ODOMETER READING ENTRY", MenuReferenceHandler::displayByMileage),
    DISPLAY_BY_VEHICLE_TYPE("DEALER_ACTION", 'T', "DISPLAY BY VEHICLE TYPE", MenuReferenceHandler::displayByType),

    // DEALER MODIFICATIONS // ----------------------------------------------------------------------------------------
    ADD_NEW_VEHICLE("DEALER_MODIFY", '1', "ADD NEW VEHICLE TO INVENTORY", MenuReferenceHandler::addVehicleToInventory),
    REMOVE_VEHICLE("DEALER_MODIFY", '2', "REMOVE VEHICLE", MenuReferenceHandler::removeVehicleFromInventory),

        // SUB TYPE-VehicleType Menu // Could Localize // -------------------------------------------------------------
    DISPLAY_ALL_SEDANS("VEHICLE_TYPE", '1', "SEDANS", MenuReferenceHandler::fillerFunctionDoesNotHaveValidReturn),
    DISPLAY_AL_COUPES("VEHICLE_TYPE", '2', "COUPES", MenuReferenceHandler::fillerFunctionDoesNotHaveValidReturn),
    DISPLAY_ALL_SUVs("VEHICLE_TYPE", '3', "SUVs", MenuReferenceHandler::fillerFunctionDoesNotHaveValidReturn),
    DISPLAY_ALL_TRUCKS("VEHICLE_TYPE", '4', "TRUCKS", MenuReferenceHandler::fillerFunctionDoesNotHaveValidReturn),
    DISPLAY_ALL_VANS("VEHICLE_TYPE", '5', "VANS", MenuReferenceHandler::fillerFunctionDoesNotHaveValidReturn),
    DISPLAY_ALL_MOTORCYCLES("VEHICLE_TYPE", '6', "MOTORCYCLES", MenuReferenceHandler::fillerFunctionDoesNotHaveValidReturn);



    // Enum Variables // ----------------------------------------------------------------------------------------------
    private final char MENU_CODE;
    private final ThrowingSupplier<Dealership, Character> ACTION;
    private final String MENU_TITLE;
    private final String ACTION_TYPE;
    public static Scanner scanner () {
        return new Scanner(System.in);
    }

    // Enum Constructor // --------------------------------------------------------------------------------------------
     MenuReference (String actionType, char menuCode, String menuTitle, ThrowingSupplier<Dealership, Character> action) {
        this.MENU_CODE = menuCode;
        this.ACTION = action;
        this.MENU_TITLE = menuTitle;
        this.ACTION_TYPE = actionType;
    }



    // Basic Getters for each variable // -----------------------------------------------------------------------------
    public char getMenuCode() {
        return MENU_CODE;
    } // Char enum reference
    public String getMenuTitle() {
        return MENU_TITLE;
    } // String menu name (EX: "DISPLAY ALL TRANSACTIONS")
    public String getACTION_TYPE() {
        return ACTION_TYPE;
    } // String menu item type (MAIN_DISPLAY || MAIN_FUNCTION)
    public char runAction (Dealership currentDealer) throws IOException, InterruptedException, ParseException {
        return ACTION.apply(currentDealer);
    } // Char return type, can pass other
    // variables using R in ThrowingSupplier to remote method (ONLY T = t.get() || T apply R (variableType) = t.apply(R) ).

    // Custom Functions for menu // -----------------------------------------------------------------------------------
    public static char fromCodeReturnAction (char code, String actionTypeSplitByPipe, Dealership currentDealer) throws IOException, InterruptedException, ParseException {
        for (MenuReference menuList : MenuReference.values()) {
            if (!actionTypeSplitByPipe.equalsIgnoreCase("ALL")) { // Only use ALL for MASTER MENU
                if (menuList.getMenuCode() == code && menuList.getACTION_TYPE().toUpperCase().contains(actionTypeSplitByPipe.toUpperCase())) {
                    return menuList.runAction(currentDealer); // Will find and run any menu action from code & code group name (code, actionSplitByPipe).
                }
            } else { // Master list Access (KEYWORD: "ALL")
                if (menuList.getMenuCode() == code) {
                    return menuList.runAction(currentDealer);
                }
            }
        }
        return '0';
    }
    public static String fromCodeReturnTitle (char code, String actionTypeSplitByPipe, Dealership currentDealer) {
       for (MenuReference menuList : MenuReference.values()) {
           if (menuList.getMenuCode() == code) {
               return menuList.getMenuTitle();
           }
       }
       return null;
    }
    public static char manualCallAction (MenuReference label, Dealership currentDealer) throws IOException, ParseException, InterruptedException {
        for (MenuReference menuList : MenuReference.values()) {
            if (menuList == label) {
                return menuList.runAction(currentDealer);
            }
        }
        return 'Z';
    }
    public static char getCodeFromUserInputString (String input, String checkActionTypeSplitByPipe) {
        if (Design.isEmpty(input)) {Design.thisFieldCantBeEmpty(); return 'Z';}
        if (input.matches("^[a-zA-Z\\d]$")) {
            char inputToChar = input.toUpperCase().charAt(0);
            if (!MenuReference.checkStringForCode(inputToChar, checkActionTypeSplitByPipe, input)) {
                return 'Z';
            }
            return inputToChar;
        } else return 'Z';
    }
    public static char screenChange (Scanner scanner, String messageToDisplay, String actionTypeSplitByPipe) {
        char userChoice = '0';
        boolean properChoiceMade = false;
        Scanner userInput = new Scanner(System.in);
        while (!properChoiceMade) {
            Design.titleNewLineTop();
            System.out.println(messageToDisplay + "\n");
            MenuReference.printMenu(actionTypeSplitByPipe);
            Design.titleLineBottom();
            String userChoiceInput = Design.getGeneralStringNoPrompt(scanner);
            userChoice = MenuReference.getCodeFromUserInputString(userChoiceInput, actionTypeSplitByPipe);
            return userChoice;
        }
        return userChoice;
    }
    public static boolean checkStringForCode (char code, String actionTypeSplitByPipe, String input) {
        for (MenuReference menuList : MenuReference.values()) {
            if (!actionTypeSplitByPipe.toUpperCase().equals("ALL")) {
                if (menuList.getMenuCode() == code && actionTypeSplitByPipe.toUpperCase().contains(menuList.getACTION_TYPE().toUpperCase())) {
                    return true;
                }
            } else {
                if (menuList.getMenuCode() == code) {
                    return true;
                }
            }
        }
        return false;
    }
    public static char menuTemplate (Scanner scanner, String menuMessage, String actionTypeSplitByPipes) {
        char userSelection = '0';
        boolean keepGoing = true;
        while (keepGoing) {
            Design.titleNewLineTop();
            System.out.println(menuMessage + "\n");
            MenuReference.printMenu(actionTypeSplitByPipes);
            Design.titleLineBottom();
            String userSelectionInput = Design.getGeneralStringNoPrompt(scanner);
            userSelection = MenuReference.getCodeFromUserInputString(userSelectionInput, actionTypeSplitByPipes);
            return userSelection;
        }
        return userSelection;
    }
    public static void printMenu (String actionTypeSplitByPipe) {
        String [] actions = actionTypeSplitByPipe.split("\\|");
        for (int i = 0; i < actions.length; i++) {
            for (MenuReference menuList : MenuReference.values()) {
                if (menuList.getACTION_TYPE().toUpperCase().contains(actions[i].trim().toUpperCase()))
                    System.out.println("(" + menuList.getMenuCode() + ") " + menuList.getMenuTitle());
            }
            System.out.println();
        }
    }

    //Custom Menus for this.Program // --------------------------------------------------------------------------------
    public static char mainMenu(Scanner scanner, String appWelcomeMessage, String actionTypeSplitByPipe) {
        return menuTemplate(scanner, appWelcomeMessage, actionTypeSplitByPipe);
    }
    public static char entryScreen (Scanner scanner, String appWelcomeMessage, String actionTypeSplitByPipe) {
        return menuTemplate(scanner, appWelcomeMessage, actionTypeSplitByPipe);
    }
    public static char exitApp (String message) {
        Design.titleNewLineTop();
        System.out.println(message + ", \n" +
                "Goodbye!");
        Design.titleLineBottom();
        Design.timer1000();
        return 'X';
    }

}

package mainMenu;

import java.io.IOException;
import java.util.Scanner;

public enum MenuOptions {
    MAIN_MENU("ESSENTIAL", '0', "MAIN MENU" , MenuHandler::showMainScreen),
    //GENERAL_HOME("ESSENTIAL", 'H', "LOAD DEALERSHIP BY ID", () ->MenuHandler.generalHome()),
    EXIT_APP("ESSENTIAL", 'X', "EXIT APP", MenuHandler::exitApp),

    // APP BASED CODES BELOW HERE // ----------------------------------------------------------------------------------
    GET_DEALERSHIP_BY_ID ("ENTRY_SCREEN", 'L', "LOAD DEALERSHIP BY ID", MenuHandler::);


    private final char MENU_CODE;
    private final ThrowingSupplier<Character> ACTION;
    private final String MENU_TITLE;
    private final String ACTION_TYPE;

    MenuOptions (String actionType, char menuCode, String menuTitle, ThrowingSupplier<Character> action) {
        this.ACTION_TYPE = actionType;
        this.MENU_CODE = menuCode;
        this.ACTION = action;
        this.MENU_TITLE = menuTitle;
    }

    public char getMenuCode() {
        return MENU_CODE;
    }
    public String getMenuTitle() {
        return MENU_TITLE;
    }
    public String getACTION_TYPE() {
        return ACTION_TYPE;
    }
    public char runAction () throws InterruptedException, NumberFormatException, IOException{
        return ACTION.get();
    }



    public static char fromCode (char code, String actionTypeSplitByPipe) throws InterruptedException, IOException, NumberFormatException {
        for (MenuOptions menuList : MenuOptions.values()) {
            if (!actionTypeSplitByPipe.equalsIgnoreCase("ALL")) {
                if (menuList.getMenuCode() == code && menuList.getACTION_TYPE().toUpperCase().contains(actionTypeSplitByPipe.toUpperCase())) {
                    return menuList.runAction();
                }
            } else {
                if (menuList.getMenuCode() == code) {
                    return menuList.runAction();
                }
            }
        }
        System.out.println("test fail");
        return '0';
    }
    public static char getMenuOptionInput (String input, String checkActionTypeSplitByPipe) throws InterruptedException, NumberFormatException, IOException {
        if (Design.isEmpty(input)) {
            Design.thisFieldCantBeEmpty(); return 'Z';}
        char inputToChar = input.toUpperCase().charAt(0);
        if (!MenuOptions.checkCode(inputToChar, checkActionTypeSplitByPipe, input)) {return 'Z';}
        return inputToChar;
    }
    public char screenChange (Scanner scanner, String messageToDisplay, String actionTypeSplitByPipe) throws IOException, InterruptedException {
        char userChoice = '0';
        boolean properChoiceMade = false;
        Scanner userInput = new Scanner(System.in);
        while (!properChoiceMade) {
            Design.titleNewLineTop();
            System.out.println(messageToDisplay + "\n");
            MenuOptions.printMenu(actionTypeSplitByPipe);
            Design.titleLineBottom();
            String userChoiceInput = Design.getGeneralStringNoPrompt(scanner);
            userChoice = MenuOptions.getMenuOptionInput(userChoiceInput, actionTypeSplitByPipe);
            return userChoice;
        }
        return userChoice;
    }
    public static boolean checkCode (char code, String actionTypeSplitByPipe, String input) {
        for (MenuOptions menuList : MenuOptions.values()) {
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
    public static char menuTemplate (Scanner scanner, String menuMessage, String actionTypeSplitByPipes) throws IOException, InterruptedException {
        char userSelection = '0';
        boolean keepGoing = true;
        while (keepGoing) {
            Design.titleNewLineTop();
            System.out.println(menuMessage + "\n");
            MenuOptions.printMenu(actionTypeSplitByPipes);
            Design.titleLineBottom();
            String userSelectionInput = Design.getGeneralStringNoPrompt(scanner);
            userSelection = MenuOptions.getMenuOptionInput(userSelectionInput, actionTypeSplitByPipes);
            return userSelection;
        }
        return userSelection;
    }
    public static void printMenu (String actionTypeSplitByPipe) {
        String [] actions = actionTypeSplitByPipe.split("\\|");
        for (int i = 0; i < actions.length; i++) {
            for (MenuOptions menuList : MenuOptions.values()) {
                if (menuList.getACTION_TYPE().toUpperCase().contains(actions[i].trim().toUpperCase()))
                    System.out.println("(" + menuList.getMenuCode() + ") " + menuList.getMenuTitle());
            }
            System.out.println();
        }
    }
}

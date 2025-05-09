import java.util.Scanner;

public class Design {
    Scanner scanner = new Scanner(System.in);
    public static void titleNewLineTop () {
        System.out.println("\n════════════════════════════════════════════════════════════════════════");
    }
    public static void titleLineBottom () {
        System.out.println("════════════════════════════════════════════════════════════════════════");
    }
    public static void newLineTop() {
        System.out.println("\n───────────────────────────────────────────────────────────────");
    }
    public static void lineBottom() {
        System.out.println("───────────────────────────────────────────────────────────────");
    }
    public static void timer(int millis) throws InterruptedException {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void timer1500 () {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void timer1000 () {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static String promptUserMenuReturn () {
        return "You can also enter (0) to return to menu, or (X) to exit app.";
    }
    public static String autoLineBreakAt100UpTo300 (String input) {
        String returnInput = "";
        if (input.length() > 100) {
            while (input.length() > 100) {
                int breaker = input.lastIndexOf(' ', 100);
                returnInput += input.substring(0, breaker) + "\n";
                input = input.substring(breaker + 1);
            }
            returnInput += input;
        } if (input.length() < 100) {
            returnInput = input;
        }
        return returnInput;
    }
    public static String autoCapitalizeFirstLetter (String input) {
        String [] inputParts = input.toLowerCase().split(" ");
        for (int i = 0; i < inputParts.length; i++) {
            inputParts [i] = inputParts [i].substring(0, 1).toUpperCase() + inputParts [i].substring(1);
        }
        input = String.join(" ", inputParts);
        return input;
    }
    public static boolean isEmpty (String input) {
        if (input.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    public static void thisFieldCantBeEmpty () {
        System.out.println("This Field Cannot Be Empty!");
    }
    public static void enterPrompt () {
        System.out.print("\n\nEnter:  ");
    }
    public static String getNounPrompt(Scanner scanner, boolean prompt ,String question, boolean autoCapitalize) {
        boolean keepGoing = true;
        String userInput = null;

        while (keepGoing) {
            if (prompt) {
                titleNewLineTop();
                System.out.println(question);
                titleLineBottom();
            }
            enterPrompt();
            userInput = scanner.nextLine().trim().replaceAll("\\s+", " ");
            if (isEmpty(userInput)) {thisFieldCantBeEmpty(); continue;}
            if (autoCapitalize) {autoCapitalizeFirstLetter(userInput);}
            keepGoing = false;
        }
        return userInput;
    }
    public static String getGeneralStringNoPrompt (Scanner scanner) {
        boolean keepGoing = true;
        String userInput = null;

        while (keepGoing) {
            enterPrompt();
            userInput = scanner.nextLine().trim().replaceAll("\\s+", " ");
            if (isEmpty(userInput)) {thisFieldCantBeEmpty(); continue;}
            keepGoing = false;

        }
        return userInput;
    }
    public static String getPasswordString (Scanner scanner) {
        boolean keepGoing = true;
        String userInput = null;

        while (keepGoing) {
            titleNewLineTop();
            System.out.println("Please enter a password. Spaces are not accepted.");
            titleLineBottom();
            enterPrompt();
            userInput = scanner.nextLine().trim().replaceAll("\\s+", "");
            if (isEmpty(userInput)) {thisFieldCantBeEmpty(); continue;}

            boolean confrim = false;
            while (!confrim) {
                titleNewLineTop();
                System.out.println("Please retype password to confirm");
                titleLineBottom();
                enterPrompt();
                String userConfirm = scanner.nextLine().trim().replaceAll("\\s+", "");
                if (isEmpty(userConfirm)) {thisFieldCantBeEmpty(); continue;}
                if (userConfirm.equals(userInput)) {
                    titleNewLineTop();
                    System.out.println("Password confirmed");
                    titleLineBottom();
                    keepGoing = false;
                    confrim = true;
                } else {
                    titleNewLineTop();
                    System.out.println("Incorrect password");
                    confrim = true;
                }
            }
        }
        return userInput;
    }
    public static String enterPassword (Scanner scanner) {
        boolean keepGoing = true;
        String userInput = null;

        while (keepGoing) {
            titleNewLineTop();
            System.out.println("Enter your password.");
            titleLineBottom();
            enterPrompt();
            userInput = scanner.nextLine().trim().replaceAll("\\s+", "");
            if (isEmpty(userInput)) {thisFieldCantBeEmpty(); continue;}
            keepGoing = false;

        }
        return userInput;
    }
    public static void pleaseChooseFromListedOptions() {
        Design.titleNewLineTop();
        System.out.println("Please choose from listed options.");
    }
    public static String getStringOrMenuAction (Scanner scanner, boolean autoCapitalize, boolean prompt, String promptMessage, String actionSplitByPipe) throws InterruptedException {
        boolean keepGoing = true;
        String userInput = null;

        while (keepGoing) {
            if (prompt) {
                titleNewLineTop();
                System.out.println(promptMessage);
                MenuOptions.printMenu(actionSplitByPipe);
                titleLineBottom();
            }
            enterPrompt();
            userInput = scanner.nextLine().trim().replaceAll("\\s+", " ");
            if (isEmpty(userInput)) {thisFieldCantBeEmpty(); continue;}
            char ifLeaving = MenuOptions.getMenuOptionInput(userInput, actionSplitByPipe);
            if (ifLeaving != 'Z') {userInput = String.valueOf(ifLeaving);}
            if (autoCapitalize) {userInput = Design.autoCapitalizeFirstLetter(userInput);}
            keepGoing = false;

        }
        return userInput;
    }
    public static boolean midAppReturnCheck (String input) {
        if (input.equalsIgnoreCase("X") || input.equalsIgnoreCase("0")) {
            return true;
        } else {
            return false;
        }
    }

}

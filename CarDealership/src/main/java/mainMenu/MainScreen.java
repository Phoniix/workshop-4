package mainMenu;

import java.io.IOException;
import java.util.Scanner;

public class MainScreen {

    public static char welcomeScreen(Scanner scanner, String appWelcomeMessage, String actionTypeSplitByPipes) throws IOException, InterruptedException {
        return MenuOptions.menuTemplate(scanner, appWelcomeMessage, actionTypeSplitByPipes);

    }

    public static void exitSequence() throws InterruptedException {
        Design.titleNewLineTop();
        System.out.println("Thank you for using Account Ledger App!");
        System.out.println("Goodbye!");
        Design.titleLineBottom();
        Design.timer(1500);
    }

}
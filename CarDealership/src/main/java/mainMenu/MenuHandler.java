package mainMenu;

import java.io.IOException;
import java.util.Scanner;

public class MenuHandler {
    static Scanner scanner = new Scanner(System.in);

    // ESSENTIAL FUNCTIONS
    public static char showMainScreen () throws IOException, InterruptedException {
        return MainScreen.welcomeScreen(scanner, "Welcome User! Use Dealership App To Seamlessly Run Your Services!\n" +
                "what service do you need today?", "ENTRY_SCREEN|ESSENTIAL");
    }
    public static char generalHome () {

        return '0';
    }
    public static char exitApp () throws InterruptedException {
        MainScreen.exitSequence();

        return '0';
    }

    public static char loadDealership () {

        return '0';
    }

    // APP BASED FUNCTIONS //
    // Screens // -----------------------------------------------------------------------------------------------------



}

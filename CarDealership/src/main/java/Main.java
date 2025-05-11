import com.pluralsight.MenuOptions;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    DealershipFileManager loadDealership;
    Dealership currentDealer;

    public static void main(String[] args) throws InterruptedException, IOException {
        boolean keepGoing = true;
        char function = MenuOptions.MAIN_MENU.getMenuCode();
        int dealerId = 0;


        while (function == '0') {
                function = MenuOptions.fromCode(function, "ALL");
                if (function == 'X') break;
        }

    }

    public static char welcomeScreen (Scanner scanner, String appWelcomeMessage, String actionTypeSplitByPipes) throws IOException, InterruptedException {
        return MenuOptions.menuTemplate(scanner, appWelcomeMessage, actionTypeSplitByPipes);

    }

    public char loadInventory (int dealerID) throws IOException, ParseException, InterruptedException {
        DealershipFileManager loadDealer = new DealershipFileManager(dealerID);
        ArrayList<Vehicle> allVehicles = loadDealer.getDealership(dealerID);

        return MenuOptions.screenChange(scanner,
                "Here Is the total inventory for: " +loadDealer.getDealerName() + "\n" +
                "What service do you need next?", "ESSENTIAL");
    }



}

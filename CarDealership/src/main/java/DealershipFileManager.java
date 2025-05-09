import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {

    private ArrayList<Dealership> dealerships;

    // Constructor // -------------------------------------------------------------------------------------------------
    private DealershipFileManager () {
        dealerships = new ArrayList<>();
    }



    public Dealership getDealership (String phoneNumber) throws  IOException {
        BufferedReader lilTim = new BufferedReader(new FileReader("dealership_" + phoneNumber + ".csv"));

        while (lilTim.readLine() != null) {
            String line = lilTim.readLine();

        }

        return  null;
    }

    public Dealership saveDealership () throws IOException {
        BufferedWriter lilJOn = new BufferedWriter(new FileWriter("dealership.csv"));



        return null;
    }



}

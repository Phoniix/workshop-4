import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {

    private ArrayList<Dealership> dealerships;

    // Constructor // -------------------------------------------------------------------------------------------------
    private DealershipFileManager () {
        dealerships = new ArrayList<>();
    } // TODO



    public Dealership getDealership (String phoneNumber) throws  IOException {
        BufferedReader lilTim = new BufferedReader(new FileReader("dealership_" + phoneNumber + ".csv"));

        while (lilTim.readLine() != null) {
            String line = lilTim.readLine();

        }

        return  null;//TODO
    }

    public Dealership saveDealership (String dealerID) throws IOException {

        try(BufferedWriter lilJOn = new BufferedWriter(new FileWriter("dealership_"+ dealerID +".csv", true));) {
         lilJOn .write("");
         lilJOn.flush();
        } catch (IOException e) {
            System.out.println("foobar.");
        }



        return null; //TODO
    }



}

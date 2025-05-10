import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {

    private ArrayList<Dealership> dealerships;

    // Constructor // -------------------------------------------------------------------------------------------------
    private DealershipFileManager () {
        dealerships = new ArrayList<>();
        Dealership dealership = new Dealership("testDealer", "123 Main St. Dallas, Tx", "(123) 456-789");
        dealerships.add(dealership);
    } // TODO



    public Dealership getDealership (String dealerID, ArrayList<Dealership> dealerships) throws  IOException {
        BufferedReader dealershipPuller = new BufferedReader(new FileReader("dealershipPuller.csv"));
        BufferedReader inventoryPuller = new BufferedReader(new FileReader("ID_inventoryReferenceNum.csv"));

        while (dealershipPuller.readLine() != null) {
            String dealerLine = dealershipPuller.readLine();
            String [] dealerParts = dealerLine.split("\\|");
            while (inventoryPuller.readLine() != null) {
                String inventoryLine = inventoryPuller.readLine();
                String [] inventoryParts = inventoryLine.split("\\|");
                if (dealerParts[0].equals(inventoryParts[0])) {
                    //TODO
                    // Need to construct a way to get inventory for the dealer and set the vehicle inventory for that dealer
                }
            }

        }

        return  null;//TODO
    }

    public ArrayList<Dealership> saveDealership (String dealerID, ArrayList<Dealership> dealerships) throws IOException {
        String dealerReferenceInfo ="";

        try(BufferedWriter lilJOn = new BufferedWriter(new FileWriter("dealership_Puller.csv", true));) {

            for (Dealership getDealerID : dealerships) { //
                String [] parts = getDealerID.toString().split("\\|");
                if (parts.length > 1) {
                    String ID = parts[0];
                    String dealerName = parts[1];
                    dealerReferenceInfo = ID + "\\|" + dealerName;
                }
            }

         lilJOn.write(dealerID + "\\|" + dealerships);
         lilJOn.flush();
        } catch (IOException e) {
            System.out.println("foobar.");
        }



        return null; //TODO
    }



}

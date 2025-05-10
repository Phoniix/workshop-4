import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;

public class DealershipFileManager {

    private ArrayList<Dealership> dealerships;

    // Constructor // -------------------------------------------------------------------------------------------------
    private DealershipFileManager () {
        dealerships = new ArrayList<>();
        Dealership dealership = new Dealership("testDealer", "123 Main St. Dallas, Tx", "(123) 456-789");
        dealerships.add(dealership);
    } // TODO



    public Dealership getDealership (String dealerID, ArrayList<Dealership> dealerships) throws  IOException, NumberFormatException, ParseException {
        BufferedReader dealershipPuller = new BufferedReader(new FileReader("dealershipPuller.csv"));
        BufferedReader inventoryPuller = new BufferedReader(new FileReader("ID_inventoryReferenceNum.csv"));
        String dealerLine = null;
        String inventoryLine = null;


        while ((dealerLine = dealershipPuller.readLine()) != null) {
            int dealerLineCounter = 0;
            String [] dealerParts = dealerLine.split("\\|");
            if (dealerParts.length != 4 && !dealerLine.equals("ID|DEALERSHIP_NAME|DEALERSHIP_ADDRESS|DEALER_PHONE_NUMBER")) {
                throw new ParseException("File Formatting Error In: dealershipPuller.csv At Line: " + dealerLineCounter, dealerLineCounter);
            }
            Dealership temp = new Dealership(dealerParts[1], dealerParts[2], dealerParts[3]);
            dealerships.add(temp);
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

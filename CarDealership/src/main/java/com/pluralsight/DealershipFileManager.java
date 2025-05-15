package com.pluralsight;

import com.pluralsight.Dealership;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;

public class DealershipFileManager {
    private int dealerID;
    private String dealerName;
    private String dealerAddress;
    private String dealerPhone;
    private Dealership currentDealer;
    private ArrayList<Vehicle> dealerInventory;

    public DealershipFileManager(int dealerID) {
        this.dealerID = dealerID;
        this.currentDealer = new Dealership(this.dealerName, this.getDealerAddress(), this.dealerPhone);
    }

    public ArrayList<Vehicle> getDealership (int dealerID) throws  IOException, ParseException {
        ArrayList<Vehicle> allVehicles = new ArrayList<Vehicle>();
        String dealerLine = null;
        String vehicleLine = null;
        int lineCounter = 0;
        boolean isInventory = false;
        boolean foundDealer = false;
        boolean foundDealership = true;

        try (BufferedReader dealershipPuller = new BufferedReader(new FileReader("allInventories.csv"))) {

            while ((dealerLine = dealershipPuller.readLine()) != null) {
                lineCounter++;
                dealerLine = dealerLine.trim();

                if (dealerLine.equals("DEALER_ID|DEALER_NAME|DEALER_ADDRESS|PHONE_NUMBER")) continue;
                if (dealerLine.equals("VIN|YEAR|MAKE|MODEL|TYPE|COLOR|MILEAGE|PRICE")) continue;
                if (dealerLine == null || dealerLine.isBlank()) continue;

                if (!foundDealer && dealerLine.split("\\|").length == 4) {
                    String[] dealerParts = dealerLine.split("\\|");
                    int fileID = Integer.parseInt(dealerParts[0]);
                    foundDealer = (fileID == dealerID);
                    this.dealerID = fileID;
                    this.dealerName = dealerParts[1];
                    this.dealerAddress = dealerParts[2];
                    continue;
                }

                if (isInventory && dealerLine.equals("|INVENTORY_END|")) {
                    break;
                }

                if (foundDealer && dealerLine.equals("|INVENTORY_START|")) {
                    isInventory = true;
                    continue;
                }

                if (isInventory && foundDealer) {
                    String[] vehicleParts = dealerLine.split("\\|");
                    if (vehicleParts.length != 8) {
                        throw new ParseException("File Formatting Error In: allInventories.csv // At ID: " + dealerLine + " \\| " + lineCounter, lineCounter);
                    }

                    int vin = Integer.parseInt(vehicleParts[0]);
                    int year = Integer.parseInt(vehicleParts[1]);
                    String make = vehicleParts[2];
                    String model = vehicleParts[3];
                    String type = vehicleParts[4];
                    String color = vehicleParts[5];
                    int odometer = Integer.parseInt(vehicleParts[6]);
                    double price = Double.parseDouble(vehicleParts[7]);
                    allVehicles.add(new Vehicle(vin, year, make, model, type, color, odometer, price));

                }
            }
        }
        this.dealerInventory = allVehicles;
        return allVehicles;
    }

    public ArrayList<Dealership> saveDealership (String dealerID, ArrayList<Dealership> allDealerships) throws IOException {


        return null; //TODO
    }

    public int getDealerID() {
        return dealerID;
    }

    public String getDealerName() {
        return dealerName;
    }

    public String getDealerAddress() {
        return dealerAddress;
    }

    public String getDealerPhone() {
        return dealerPhone;
    }

    public Dealership getCurrentDealer() {
        return currentDealer;
    }

    public ArrayList<Vehicle> getDealerInventory() {
        return dealerInventory;
    }
}

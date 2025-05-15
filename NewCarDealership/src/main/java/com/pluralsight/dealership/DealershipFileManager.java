package com.pluralsight.dealership;

import com.pluralsight.roadVehicle.Vehicle;
import com.pluralsight.design_and_ui.Design;
import com.pluralsight.design_and_ui.MenuReference;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class DealershipFileManager {
    private int dealerID;
    private String dealerName;
    private String dealerAddress;
    private String dealerPhone;
    private Dealership currentDealer;
    private ArrayList<Vehicle> dealerInventory;
    private static final String FILE_PATH = "allInventories.csv";

    // Constructor // -------------------------------------------------------------------------------------------------
    public DealershipFileManager(int dealerID) {
        this.dealerID = dealerID;
    }

    // OL Constructor // ----------------------------------------------------------------------------------------------
    public DealershipFileManager (Dealership currentDealer) {
        this.currentDealer = currentDealer;
    }

    // Calling dealership from file // --------------------------------------------------------------------------------
    public Dealership getDealership (int dealerID) throws  IOException, ParseException {
        ArrayList<Vehicle> allVehicles = new ArrayList<Vehicle>();
        String dealerLine = null;
        int lineCounter = 0;
        boolean isInventory = false;
        boolean foundDealer = false;

        try (BufferedReader dealershipPuller = new BufferedReader(new FileReader(FILE_PATH))) {

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
                    if (foundDealer) {
                        this.dealerID = fileID;
                        this.dealerName = dealerParts[1];
                        this.dealerAddress = dealerParts[2];
                        this.dealerPhone = dealerParts[3];
                    }
                    continue;
                }
                if (isInventory && dealerLine.equals("|INVENTORY_END|")) {break;}
                if (foundDealer && dealerLine.equals("|INVENTORY_START|")) {
                    isInventory = true;
                    continue;
                }
                if (isInventory && foundDealer) {
                    String[] vehicleParts = dealerLine.split("\\|");
                    if (vehicleParts.length != 8) {
                        throw new ParseException("File Formatting Error In: " + FILE_PATH + " // At ID: " + dealerLine + " \\| " + lineCounter, lineCounter);
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
        this.currentDealer = new Dealership(this.dealerID, this.dealerName, this.dealerAddress, this.dealerPhone, this.dealerInventory);
        return this.currentDealer;
    }
    // Saving new dealership to file
    public static char saveDealership (Scanner scanner, String name, String address, String phoneNum) throws IOException {
        boolean foundDealer = false;
        String dealerLine = null;
        int fileID = 0;
        int dealerID = 0;
        int dealerCounter = 0;
        Dealership newDealer;

        try (BufferedReader dealershipPuller = new BufferedReader(new FileReader(FILE_PATH))) {
            while ((dealerLine = dealershipPuller.readLine()) != null) {
                dealerLine = dealerLine.trim();
                if (dealerLine.equals("DEALER_ID|DEALER_NAME|DEALER_ADDRESS|PHONE_NUMBER")) continue;
                if (dealerLine.equals("VIN|YEAR|MAKE|MODEL|TYPE|COLOR|MILEAGE|PRICE")) continue;
                if (dealerLine.split("\\|").length == 4) {
                    dealerCounter++;
                }
            }
            dealerID = dealerCounter + 1;
        } catch (IOException e) {
            System.out.println("No File");
        }

        try (BufferedWriter dealerSaver = new BufferedWriter((new FileWriter(FILE_PATH, true)))) {
            ArrayList<Vehicle> inventory = new ArrayList<>();
            newDealer = new Dealership(dealerID, name, address, phoneNum, inventory);
            dealerSaver.write(dealerID + "|" + newDealer.toString() +"\n|INVENTORY_START|" + "\n|INVENTORY_END|\n");
            dealerSaver.flush();
        } catch (IOException e) {
            System.out.println("No File");
        }
        return  (char)(dealerID);
    }

    // Custom functions to assist dealership class // -----------------------------------------------------------------
    public static Dealership loadDealership (Scanner scanner) throws IOException, ParseException, InterruptedException {
        boolean foundDealer = false;
        Dealership currentDealership = null;
        while (!foundDealer) {
            int dealerID = Design.getIntFromPrompt(scanner, true, "Please Enter your dealership ID", true);
            DealershipFileManager dealerPuller = new DealershipFileManager(dealerID);
            currentDealership = dealerPuller.getDealership(dealerID);
            currentDealership.loadVehicleArray(dealerPuller);
            try {
                if (!currentDealership.getNAME().equals("null")) {
                    System.out.println("Dealership Loaded.\n" + currentDealership.toString());
                    foundDealer = true;
                    if (currentDealership.getINVENTORY().size() < 1) {
                        Design.continueEnter(scanner, true, "You are a new dealership. Please add a vehicle to be able to use functions.");
                        MenuReference.manualCallAction(MenuReference.ADD_NEW_VEHICLE, currentDealership);
                        MenuReference.manualCallAction(MenuReference.START_APP, currentDealership);
                        return currentDealership;
                    }

                }
            } catch (NullPointerException ignored) {
                System.out.println("Invalid Dealer Number");
                continue;
            }



        }
        return currentDealership;
    }
    public static char registerDealership (Scanner scanner) throws IOException, ParseException {
        String name =  Design.getNounPrompt(scanner, true, "Please enter dealership name.", true);
        String address = Design.getNounPrompt(scanner, true, "Please enter dealership address", true);
        String phoneNum = Design.getPhoneNumString(scanner, true, "Please enter dealership phone number.");
        int dealerId = saveDealership(scanner, name, address, phoneNum);
        System.out.print("Your DEALERSHIP ID IS: " + dealerId + "\n" +
                "Save this, you will need it to login.");
        Design.continueEnter(scanner, true, "Your dealer ID is: " + dealerId + "\n" +
                "Make sure to dave or write it down, you will need it on the next screen.");
        return MenuReference.screenChange(scanner, "Congratulations on registering your dealership!\n" +
                "please use [" + dealerId + "] to sign in on next screen", "ENTRY_SCREEN");
    }
    public void writeVehicleToFile (Dealership currentDealer, Vehicle newVehicle) throws IOException {
        String readLine = null;
        ArrayList<String> lines = new ArrayList<>();
        boolean foundDealer = false;
        boolean isInventory = false;
        String [] parts;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            while ((readLine = reader.readLine()) != null) {
                lines.add(readLine);
                if (readLine.equals("DEALER_ID|DEALER_NAME|DEALER_ADDRESS|PHONE_NUMBER")) continue;
                parts = readLine.split("\\|");
                if (readLine.startsWith(currentDealer.getDealerID() + "|") && Integer.parseInt(parts[0]) == currentDealer.getDealerID() && parts.length == 4) {
                    foundDealer = true;
                    System.out.println("Found dealer: " + readLine);
                }
                if (foundDealer && readLine.equals("|INVENTORY_START|")) {
                    isInventory = true;
                }

                if (foundDealer && isInventory) {
                    System.out.println("Is Inventory true");
                    if (readLine.equals("|INVENTORY_END|")) {
                        lines.add(lines.size() - 1, newVehicle.toString());
                        System.out.println("Inserted Vehicle 2nd check: " + newVehicle.toString());
                        foundDealer = false;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Read Error");
        }
        try (BufferedWriter carWriter = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0;i < lines.size(); i ++) {
                carWriter.write(lines.get(i));
                if (i < lines.size() - 1)
                    carWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("No File");
        }

    }
    public void removeVehicleFromFile (Dealership currentDealer, Vehicle remVehicle) {
        String readLine = null;
        ArrayList<String> lines = new ArrayList<>();
        boolean foundDealer = false;
        boolean isInventory = false;
        String [] parts;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            while ((readLine = reader.readLine()) != null) {
                if (readLine.equals("DEALER_ID|DEALER_NAME|DEALER_ADDRESS|PHONE_NUMBER")) {
                    lines.add(readLine);
                    continue;
                }
                if (readLine.equals("VIN|YEAR|MAKE|MODEL|TYPE|COLOR|MILEAGE|PRICE")) {
                    lines.add(readLine);
                    continue;
                }
                parts = readLine.split("\\|");
                if (readLine.startsWith(currentDealer.getDealerID() + "|") && Integer.parseInt(parts[0]) == currentDealer.getDealerID() && parts.length == 4) {
                    lines.add(readLine);
                    foundDealer = true;
                    System.out.println("Found dealer: " + readLine);
                    continue;
                }
                if (foundDealer && readLine.equals("|INVENTORY_START|")) {
                    lines.add(readLine);
                    isInventory = true;
                    continue;
                }
                if (foundDealer && readLine.equals("|INVENTORY_END|")) {
                    isInventory = false;
                    foundDealer = false;
                    lines.add(readLine);
                    continue;
                }

                if (foundDealer && isInventory) {
                    System.out.println("Is Inventory true");
                    if (readLine.equalsIgnoreCase(remVehicle.toString())) {
                        System.out.println("Removed Vehicle 2nd check: " + remVehicle.toString());
                    } else {
                        lines.add(readLine);
                    }
                } else {
                    lines.add(readLine);
                }
            }
        } catch (IOException e) {
            System.out.println("Read Error");
        }
        try (BufferedWriter carWriter = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0;i < lines.size(); i ++) {
                carWriter.write(lines.get(i));
                if (i < lines.size() - 1)
                    carWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("No File");
        }
    }


    // Getters // -----------------------------------------------------------------------------------------------------
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

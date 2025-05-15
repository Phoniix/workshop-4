package com.pluralsight;

import com.pluralsight.dealership.Dealership;
import com.pluralsight.roadVehicle.Vehicle;

import java.io.*;
import java.util.ArrayList;

public class test {

    public static void main(String[] args) throws IOException {
        int dealerID = 1;
        ArrayList<Vehicle> invenotry = new ArrayList<>();
        Dealership currentDealer = new Dealership(dealerID, "test", "test", "test", invenotry);
        Vehicle vehicle = new Vehicle(10112, 1993, "Ford", "Explorer", "SUV", "Red", 525123, 995.00);
        removeVehicleFromFile(currentDealer, vehicle);



    }




    public static void removeVehicleFromFile (Dealership currentDealer, Vehicle remVehicle) {
    final String FILE_PATH = "allInventories1.csv";
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

}

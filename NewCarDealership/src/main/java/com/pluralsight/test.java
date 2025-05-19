package com.pluralsight;

import com.pluralsight.dealership.Dealership;
import com.pluralsight.dealership.DealershipFileManager;
import com.pluralsight.dealership.contracts.SalesContract;
import com.pluralsight.design.Design;
import com.pluralsight.roadVehicle.Vehicle;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class test {

    public static void main(String[] args) throws IOException, ParseException {

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
                if (readLine.startsWith(currentDealer.getID() + "|") && Integer.parseInt(parts[0]) == currentDealer.getID() && parts.length == 4) {
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
    public static HashMap<String, Object> confirmInputs (Scanner scanner, HashMap<String, Object> actionInput, boolean posInt, boolean posDouble, boolean autoCap) {
        boolean keepGoing = true;
        int reset = 0;
        int i = reset;
        while (keepGoing) {
            i = reset;
            Design.titleNewLineTop();
            Design.message("Is this info correct? Yes or no? (Y) or (N).", 0);
            actionInput.entrySet().stream()
                    .forEach(AI -> System.out.println
                            (Design.autoCap(AI.getKey()) + " Entered: " + (AI.getValue())));
            Design.titleLineBottom();

            if (Design.getYesNo(scanner, false, "")) {
                Design.systemMessage("Thank you for confirming", true);
                return actionInput;
            } else {
                Design.titleNewLineTop();
                Design.message("Select which part to change.", 2);
                for (Map.Entry<String, Object> AI : actionInput.entrySet()) {
                    System.out.println(Design.autoCap("(" + (i + 1) + ") " + AI.getKey()) + " Entered: " + (AI.getValue()));
                    i++;
                } i = reset;
                Design.titleLineBottom();
                int fixNum = Design.getIntFromPrompt(scanner, false, "", true);

                boolean found = false;
                String type = null;
                for (Map.Entry<String, Object> AI : actionInput.entrySet()) {
                    if (i + 1 == fixNum) {
                        if (AI.getValue() instanceof Integer)
                            actionInput.put(AI.getKey(), Design.getIntFromPrompt(scanner, true, "Please enter " + AI.getKey() + ".", posInt));
                        if (AI.getValue() instanceof Double)
                            actionInput.put(AI.getKey(), Design.getDoubleFromPrompt(scanner, true, "Please enter " + AI.getKey() + ".", posDouble));
                        if (AI.getValue() instanceof String)
                            actionInput.put(AI.getKey(), Design.getNounPrompt(scanner, true, "Please enter " + AI.getKey() + ".", autoCap ));
                    }
                    i++;
                }
            }
        }
        return null;
    }


}



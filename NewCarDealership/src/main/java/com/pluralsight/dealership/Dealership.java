package com.pluralsight.dealership;

import com.pluralsight.design_and_ui.Design;
import com.pluralsight.design_and_ui.MenuReference;
import com.pluralsight.roadVehicle.Vehicle;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dealership extends DealershipFileManager {
    private String NAME;
    private String ADDRESS;
    private String PHONE_NUMBER;
    private ArrayList<Vehicle> INVENTORY;

    // Constructor // -------------------------------------------------------------------------------------------------
    public Dealership(int dealerID, String name, String address, String phoneNumber, ArrayList<Vehicle> inventory) {
        super(dealerID);
        this.NAME = name;
        this.ADDRESS = address;
        this.PHONE_NUMBER = phoneNumber;
        this.INVENTORY = new ArrayList<Vehicle>();
    }

    //Getters // ------------------------------------------------------------------------------------------------------
    public String getNAME() {
        return NAME;
    }
    public String getADDRESS() {
        return ADDRESS;
    }
    public String getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }
    public ArrayList<Vehicle> getINVENTORY() {
        return INVENTORY;
    }

    // Setters // -----------------------------------------------------------------------------------------------------
    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }
    public void setPHONE_NUMBER(String PHONE_NUMBER) {
        this.PHONE_NUMBER = PHONE_NUMBER;
    }

    // Methods // -----------------------------------------------------------------------------------------------------
    public ArrayList<Vehicle> dealership () {
        return null;
    }

    // Custom Methods For UI // ---------------------------------------------------------------------------------------
    public void getVehicleByPrice (double min, double max) throws InterruptedException {
        Design.newLineTop();
        boolean showVehicle = true;
        for (Vehicle vehicle :this.INVENTORY) {
            showVehicle = true;
            if (vehicle.getPrice() < min) showVehicle = false;
            if (vehicle.getPrice() > max) showVehicle = false;
            if (showVehicle) {
                Design.timer(150);
                System.out.println(vehicle.toString());
                Design.lineBottom();
            }
        }
    }
    public void getVehicleByMake (String make, String model) {
        Design.newLineTop();
        for (Vehicle vehicle : this.INVENTORY) {
            if (vehicle.getMake().toLowerCase().contains(make.toLowerCase()) || vehicle.getModel().toLowerCase().contains(model.toLowerCase())) {
                System.out.println(vehicle.toString());
                Design.lineBottom();
            }
        }
    }
    public void getVehicleByYear (int year) {
        Design.titleNewLineTop();
        for (Vehicle vehicle : this.INVENTORY) {
            if (vehicle.getYear() == year) {
                System.out.println(vehicle.toString());
                Design.titleLineBottom();
            }
        }
    }
    public void getVehicleByColor (String color) {
        Design.titleNewLineTop();
        for (Vehicle vehicle : this.INVENTORY) {
            if (vehicle.getColor().toLowerCase().contains(color.toLowerCase())) {
                System.out.println(vehicle);
                Design.titleLineBottom();
            }
        }
    }
    public void getVehicleByMileage (int min, int max) {
        Design.newLineTop();
        boolean showVehicle = true;
        for (Vehicle vehicle :this.INVENTORY) {
            showVehicle = true;
            if (vehicle.getOdometer() < min) showVehicle = false;
            if (vehicle.getOdometer() > max) showVehicle = false;
            if (showVehicle) {
                System.out.println(vehicle.toString());
                Design.lineBottom();
            }
        }
    }
    public void getVehicleByType (String vehicleType) {
        vehicleType = vehicleType.replaceAll("(?i)\\b(\\w+?)s\\b", "$1");
        Design.titleNewLineTop(); // Design Visual Separator - Makes new Line
        for (Vehicle vehicle : this.INVENTORY) {
            if (vehicle.getVehicleType().toLowerCase().contains(vehicleType.toLowerCase())) {
                System.out.println(vehicle);
                Design.titleLineBottom(); // Design Visual Separator
            }
        }
    }
    public ArrayList<Vehicle> getAllVehicles () {
        return this.INVENTORY;
    }
    public ArrayList<Vehicle> loadVehicleArray (DealershipFileManager dealerPuller) throws IOException, ParseException {
        for (Vehicle vehicle : dealerPuller.getDealerInventory()) {
            this.INVENTORY.add(vehicle);
        }

        return this.INVENTORY;
    }
    public void addVehicle (Vehicle vehicle) {
        this.INVENTORY.add(vehicle);
    }
    public void removeVehicle (Vehicle vehicle) {
        this.INVENTORY.remove(vehicle);
    }
    @Override
    public String toString() {

        return  NAME + "|" + ADDRESS + "|" + PHONE_NUMBER;
    }

    // Methods With UI Interlaced // ----------------------------------------------------------------------------------
    public static char processDisplayByPrice (Scanner scanner, Dealership currentDealer) throws InterruptedException {
        double min = Design.getDoubleFromPrompt(scanner, true, "Please enter minimum price to start.", true);
        double max = Design.getDoubleFromPrompt(scanner, true, "Please enter maximum price.", true);
        currentDealer.getVehicleByPrice(min, max);
        return MenuReference.screenChange(scanner, "Here is all the entries matching\n" +
                "Minimum Price: $" + min + "\n" +
                "Maximum Price: $" + max + "\n", "DEALER_ACTION|ESSENTIAL");
    }
    public static char processDisplayByMakeModel (Scanner scanner, Dealership currentDealer) {
        String make = Design.getNounPrompt(scanner, true, "Please enter the make of vehicle.", true);
        String model = Design.getNounPrompt(scanner, true, "Please enter the model of vehicle.", true);
        currentDealer.getVehicleByMake(make, model);
        return MenuReference.screenChange(scanner, "Here are all the entries matching\n" +
                "Make: " + make + "\n" +
                "Model: " + model, "DEALER_ACTION|ESSENTIAL");
    }
    public static char processDisplayByYear (Scanner scanner, Dealership currentDealer) {
        int year = Design.getIntFromPrompt(scanner, true, "Enter the year of vehicle.", true);
        currentDealer.getVehicleByYear(year);
        return MenuReference.screenChange(scanner, "Here are all the entries matching\n" +
                "Year: " + year, "DEALER_ACTION|ESSENTIAL");
    }
    public static char processDisplayByColor (Scanner scanner, Dealership currentDealer) {
        String color = Design.getNounPrompt(scanner, true, "Enter the color of vehicle", true);
        currentDealer.getVehicleByColor(color);
        return MenuReference.screenChange(scanner, "Here are all the entries matching\n" +
                "Color: " + color, "DEALER_ACTION|ESSENTIAL");
    }
    public static char processDisplayByMileage (Scanner scanner, Dealership currentDealer) {
        int min = Design.getIntFromPrompt(scanner, true, "Enter the minimum mileage of vehicle.", true);
        int max = Design.getIntFromPrompt(scanner, true, "Enter the maximum mileage of vehicle", true);
        currentDealer.getVehicleByMileage(min, max);
        return MenuReference.screenChange(scanner, "Here are all the entries matching\n" +
                "Minimum range: " + min + " miles.\n" +
                "Maximum range: " + max + " miles.", "DEALER_ACTION|ESSENTIAL");
    }
    public static char processDisplayByType (Scanner scanner, Dealership currentDealer) throws IOException, InterruptedException {
        char function = MenuReference.menuTemplate(scanner, "What type of vehicle would you like to see?", "VEHICLE_TYPE");
        String type = MenuReference.fromCodeReturnTitle(function, "VEHICLE_TYPE", currentDealer);
        currentDealer.getVehicleByType(type);
        return MenuReference.screenChange(scanner, "Here are all of your entries matching\n" +
                "Type: " + type, "DEALER_ACTION|ESSENTIAL");
    }
    public static char processDisplayAllVehicles (Scanner scanner, Dealership currentDealer) {
        for (Vehicle vehicle : currentDealer.getAllVehicles()) {
            System.out.println(vehicle.toString());
        }
        return MenuReference.screenChange(scanner, "Here is the total inventory for: " +currentDealer.getNAME() + "\n" +
                "What service do you need next?", "DEALER_ACTION|ESSENTIAL");
    }
    public static char processAddNewVehicle (Scanner scanner, Dealership currentDealer) throws IOException {
        int vin = Design.getIntFromPrompt(scanner, true, "Please enter vin of the vehicle.", true);
        int year = Design.getIntFromPrompt(scanner, true, "Please enter year of the vehicle.", true);
        String make = Design.getNounPrompt(scanner, true, "Please enter make (brand) of the vehicle.", true);
        String model = Design.getNounPrompt(scanner, true, "Please enter model of the vehicle.", true);
        String type = Design.getNounPrompt(scanner, true, "Please enter vehicle type.\n" +
                "(EX: SEDAN, COUPE, MINIVAN, ETC.", true);
        String color = Design.getNounPrompt(scanner, true, "Please enter color of the vehicle", true);
        int odometer = Design.getIntFromPrompt(scanner, true, "Please enter the vehicle's odometer reading in whole numbers.", true);
        double price = Design.getDoubleFromPrompt(scanner, true, "Please enter the original price of the vehicle.", true);
        Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        currentDealer.addVehicle(newVehicle);
        currentDealer.writeVehicleToFile(currentDealer, newVehicle);
        return MenuReference.screenChange(scanner, "Vehicle successfully added to file.\n" +
                "What next?", "DEALER_ACTION|DEALER_MODIFY|ESSENTIAL");
    }
    public static char processRemoveVehicle (Scanner scanner, Dealership currentDealer) {
        int vin = Design.getIntFromPrompt(scanner, true, "Please enter vin of the vehicle.", true);
        int year = Design.getIntFromPrompt(scanner, true, "Please enter year of the vehicle.", true);
        String make = Design.getNounPrompt(scanner, true, "Please enter make (brand) of the vehicle.", true);
        String model = Design.getNounPrompt(scanner, true, "Please enter model of the vehicle.", true);
        String type = Design.getNounPrompt(scanner, true, "Please enter vehicle type.\n" +
                "(EX: SEDAN, COUPE, MINIVAN, ETC.", true);
        String color = Design.getNounPrompt(scanner, true, "Please enter color of the vehicle", true);
        int odometer = Design.getIntFromPrompt(scanner, true, "Please enter the vehicle's odometer reading in whole numbers.", true);
        double price = Design.getDoubleFromPrompt(scanner, true, "Please enter the original price of the vehicle.", true);
        Vehicle remVehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        currentDealer.removeVehicle(remVehicle);
        currentDealer.removeVehicleFromFile(currentDealer, remVehicle);
        return MenuReference.screenChange(scanner, "Vehicle successfully removed from file.\n" +
                "What next?", "DEALER_ACTION|DEALER_MODIFY|ESSENTIAL");
    }

}

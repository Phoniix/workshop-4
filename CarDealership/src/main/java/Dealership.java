import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class Dealership {
    private String NAME;
    private String ADDRESS;
    private String PHONE_NUMBER;
    private final ArrayList<Vehicle> INVENTORY;

    // Constructor // -------------------------------------------------------------------------------------------------
    public Dealership(String name, String address, String phoneNumber) {
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

    public ArrayList<Vehicle> getVehicleByPrice (double min, double max) {

        return null;
    }

    public ArrayList<Vehicle> getVehicleByMake (String make, String model) {

        return null;
    }

    public ArrayList<Vehicle> getVehicleByYear (int year) {

        return null;
    }

    public ArrayList<Vehicle> getVehicleByColor (String color) {

        return null;
    }

    public ArrayList<Vehicle> getVehicleByMileage (int min, int max) {

        return null;
    }

    public ArrayList<Vehicle> getVehicleByType (String vehicleType) {

        return null;
    }

    public ArrayList<Vehicle> getAllVehicles (int dealerID) throws IOException, ParseException {
        this.INVENTORY.clear();
        ArrayList<Vehicle> currentInventory = DealershipFileManager.getDealership(dealerID);
        for (Vehicle vehicle : currentInventory) {
            this.INVENTORY.add(vehicle);
        }
        return currentInventory;
    }

    public void addVehicle (Vehicle vehicle) {
        this.INVENTORY.add(vehicle);
    }

    public void removeVehicle (Vehicle vehicle) {
        this.INVENTORY.remove(vehicle);
    }

    @Override
    public String toString() {
        return NAME + "\\|" + ADDRESS + "\\|" + PHONE_NUMBER +"\\|" + INVENTORY;
    }

}

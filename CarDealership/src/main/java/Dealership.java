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

    public ArrayList<Vehicle> getAllVehicles (double min, double max) {

        return null;
    }

    public void addVehicle (Vehicle vehicle) {INVENTORY.add(vehicle);
    }

    public void removeVehicle (Vehicle vehicle) {

    }

    @Override
    public String toString() {
        return NAME + "\\|" + ADDRESS + "\\|" + PHONE_NUMBER +"\\|" + INVENTORY;
    }

}

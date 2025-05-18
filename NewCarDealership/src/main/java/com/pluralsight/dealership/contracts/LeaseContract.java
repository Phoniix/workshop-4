package com.pluralsight.dealership.contracts;

import com.pluralsight.dealership.Dealership;
import com.pluralsight.roadVehicle.Vehicle;

public class LeaseContract extends Contract {
    double expectedEnding = 0;
    double leaseFee = this.vehicle.getPrice() * .07;
    double monthlyPayment = getTotalPrice() / 36;


    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean sold, int numberOfPayment) {
        super(date, customerName, customerEmail, vehicle, sold, numberOfPayment);
    }

    protected boolean getSoldStatus() {
        return this.sold;
    }
    protected double getTotalPrice() {
        return 0;
    }
    protected double getMonthlyPayment() {
        return this.monthlyPayment;
    }

    @Override
    protected Vehicle getVehicle(int vin, Dealership currentDealer) {
        return null;
    }

    // UI Custom Functions // -----------------------------------------------------------------------------------------

    public Vehicle getVehicle(Vehicle vehicle, Dealership currentDealer) {
        boolean found = false;
        for (Vehicle car : currentDealer.getINVENTORY()) {
            if (car.getVin() == vehicle.getVin()) {
                found = true;
                return car;
            }
        } if (!found) System.out.println("Invalid Vin"); return null;
    }
}

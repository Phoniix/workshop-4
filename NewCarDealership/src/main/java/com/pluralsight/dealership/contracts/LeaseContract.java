package com.pluralsight.dealership.contracts;

import com.pluralsight.dealership.Dealership;
import com.pluralsight.design.Design;
import com.pluralsight.roadVehicle.Vehicle;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class LeaseContract extends Contract {
    static Scanner SCANNER = new Scanner(System.in);
    double EXPECTED_ENDING = 0;
    double LEASE_FEE = this.VEHICLE.getPrice() * .07;


    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean sold, int numberOfPayment) {
        super(date, customerName, customerEmail, vehicle, sold, numberOfPayment);
        this.EXPECTED_ENDING = .5 * this.VEHICLE.getPrice();
        this.MONTHLY_PAYMENT = this.TOTAL_PRICE / this.NUMBER_OF_PAYMENTS;
        this.TOTAL_PRICE = getTotalPrice();
    }

    protected boolean getSoldStatus() {
        return this.SOLD;
    }
    protected double getTotalPrice() {
        return this.VEHICLE.getPrice() + this.EXPECTED_ENDING + this.LEASE_FEE;
    }
    @Override
    public String toString() {
        return "Lease|" + Design.getDateStamp() + "|" + this.CUSTOMER_NAME + "|"
                + this.CUSTOMER_EMAIL + "|" + this.VEHICLE.getVin() + "|\n"
                + this.VEHICLE.toString() + "|\n" + this.LEASE_FEE + "|"
                + this.EXPECTED_ENDING + "|" + this.TOTAL_PRICE + (this.SOLD ? "SOLD" : "AVAILABLE") + "|"
                + String.format("%.2f", this.MONTHLY_PAYMENT);
    }


    // UI Custom Functions // -----------------------------------------------------------------------------------------
    public LeaseContract makeSale(Dealership currentDealer) {
        int vin = 0;
        LinkedHashMap<String, Object> inputs = new LinkedHashMap<>();
        inputs.put("!date", this.DATE = Design.getDateStamp());
        inputs.put("client name", this.CUSTOMER_NAME = Design.getNounPrompt(SCANNER, true, "Please enter client name.", true));
        inputs.put("client email", this.CUSTOMER_EMAIL = Design.getEmailPrompt(SCANNER, true, "Please enter client email."));
        inputs.put("vin", vin = Design.getIntFromPrompt(SCANNER, true, "Please enter the vin of vehicle to load.", true));
        inputs.put("!vehicle", this.VEHICLE = getVehicle(vin, currentDealer));
        inputs.put("!sale price", this.TOTAL_PRICE);
        inputs.put("number of payments", this.NUMBER_OF_PAYMENTS = Design.getIntWithMaxMin(SCANNER, true,
                "Vehicle Price: $" + this.VEHICLE.getPrice() + "\n" +
                        "Expected Ending Value: $" + this.EXPECTED_ENDING + "\n" +
                        "Leasing Fee: $" + this.LEASE_FEE + "\n\n" +
                        "Total Sale Price: $" + this.TOTAL_PRICE + "\n\n" +
                        "Is client financing?\n" +
                        "If Yes, enter how many payments (12 * years to pay). If no enter '1",
                true, 1, 84));
        inputs = Design.confirmInputs(SCANNER, inputs, true, true, true);


        return new LeaseContract(this.DATE, this.CUSTOMER_NAME, this.CUSTOMER_EMAIL, this.VEHICLE, true, this.NUMBER_OF_PAYMENTS);
    }
}

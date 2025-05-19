package com.pluralsight.dealership.contracts;

import com.pluralsight.dealership.Dealership;
import com.pluralsight.design.Design;
import com.pluralsight.roadVehicle.Vehicle;

import java.util.*;

public class SalesContract extends Contract {
    private static Scanner SCANNER = new Scanner(System.in);
    private final double SALES_TAX = this.VEHICLE.getPrice() * .05;
    private final double RECORDING_FEE = 100.00;
    private final double PROCESSING_FEE = this.VEHICLE.getPrice() < 10000 ? 295.00 : 495.00;


    public SalesContract(String date, String customerName, String customerEmail, Vehicle soldVehicle, boolean sold, int numberOfPayments) {
        super(date, customerName, customerEmail, soldVehicle, sold, numberOfPayments);
        this.TOTAL_PRICE = getTotalPrice();
    }

    protected boolean getSoldStatus() {
        return this.SOLD;
    }
    protected double getTotalPrice() {
        return this.VEHICLE.getPrice() + SALES_TAX + RECORDING_FEE + PROCESSING_FEE;
    }


    public SalesContract makeSale(Dealership currentDealer) {
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
                        "Recording Fee: $" + this.RECORDING_FEE + "\n" +
                        "Processing Fee: $" + this.PROCESSING_FEE + "\n" +
                        "Sales Tax: $" + this.SALES_TAX + "\n\n" +
                        "Total Sale Price: $" + this.TOTAL_PRICE + "\n\n" +
                        "Is client financing?\n" +
                        "If Yes, enter how many payments (12 * years to pay). If no enter '1",
                true, 1, 84));
        inputs = Design.confirmInputs(SCANNER, inputs, true, true, true);


        return new SalesContract(this.DATE, this.CUSTOMER_NAME, this.CUSTOMER_EMAIL, this.VEHICLE, true, this.NUMBER_OF_PAYMENTS);
    }

    @Override
    public String toString() {
        return "Sale|" + Design.getDateStamp() + "|" + this.CUSTOMER_NAME + "|"
                + this.CUSTOMER_EMAIL + "|" + this.VEHICLE.getVin() + "|\n"
                + this.VEHICLE.toString() + "|\n" + this.RECORDING_FEE + "|"
                + this.PROCESSING_FEE + "|" + this.TOTAL_PRICE + (this.SOLD ? "SOLD" : "AVAILABLE") + "|"
                + String.format("%.2f", this.MONTHLY_PAYMENT);
    }
}
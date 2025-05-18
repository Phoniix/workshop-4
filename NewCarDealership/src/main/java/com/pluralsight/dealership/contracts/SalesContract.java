package com.pluralsight.dealership.contracts;

import com.pluralsight.dealership.Dealership;
import com.pluralsight.design.Design;
import com.pluralsight.roadVehicle.Vehicle;

import java.util.*;

public class SalesContract extends Contract {
    private static Scanner scanner = new Scanner(System.in);
    private final double SALES_TAX = this.vehicle.getPrice() * .05;
    private final double RECORDING_FEE = 100.00;
    private final double PROCESSING_FEE = this.vehicle.getPrice() < 10000 ? 295.00 : 495.00;


    public SalesContract(String date, String customerName, String customerEmail, Vehicle soldVehicle, boolean sold, double totalPrice, int numberOfPayments) {
        super(date, customerName, customerEmail, soldVehicle, sold, numberOfPayments);
        this.totalPrice = getTotalPrice();
    }

    protected boolean getSoldStatus() {
        return this.sold;
    }
    protected double getTotalPrice() {
        this.totalPrice = this.vehicle.getPrice() + SALES_TAX + RECORDING_FEE + PROCESSING_FEE;
        return this.totalPrice;
    }


    protected Vehicle getVehicle(int vin, Dealership currentDealer) {
        boolean found = false;
        for (Vehicle car : currentDealer.getINVENTORY()) {
            if (car.getVin() == vin) {
                return car;
            }
        } if (!found) System.out.println("Invalid Vin"); return null;
    }

    public SalesContract makeSale(Dealership currentDealer) {
        int vin = 0;
        LinkedHashMap<String, Object> inputs = new LinkedHashMap<>();
        inputs.put("!date", this.date = Design.getDateStamp());
        inputs.put("client name", this.customerName = Design.getNounPrompt(scanner, true, "Please enter client name.", true));
        inputs.put("client email", this.customerEmail = Design.getEmailPrompt(scanner, true, "Please enter client email."));
        inputs.put("vin", vin = Design.getIntFromPrompt(scanner, true, "Please enter the vin of vehicle to load.", true));
        inputs.put("!vehicle", this.vehicle = getVehicle(vin, currentDealer));
        inputs.put("!sale price", this.totalPrice);
        inputs.put("number of payments", this.numberOfPayments = Design.getIntWithMaxMin(scanner, true,
                "Vehicle Price: $" + this.vehicle.getPrice() + "\n" +
                        "Recording Fee: $" + this.RECORDING_FEE + "\n" +
                        "Processing Fee: $" + this.PROCESSING_FEE + "\n" +
                        "Sales Tax: $" + this.SALES_TAX + "\n\n" +
                        "Total Sale Price: $" + this.totalPrice + "\n\n" +
                        "Is client financing?\n" +
                        "If Yes, enter how many payments (12 * years to pay). If no enter '1",
                true, 1, 84));
        inputs = Design.confirmInputs(scanner, inputs, true, true, true);


        return new SalesContract(this.date, this.customerName, this.customerEmail, this.vehicle, true, this.totalPrice, this.numberOfPayments);
    }

    @Override
    public String toString() {
        return "SalesContract{" +
                "date='" + date + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", vehicle=" + vehicle +
                ", sold=" + sold +
                ", totalPrice=" + totalPrice +
                ", numberOfPayments=" + numberOfPayments +
                ", monthlyPayment=" + monthlyPayment +
                ", PROCESSING_FEE=" + PROCESSING_FEE +
                ", RECORDING_FEE=" + RECORDING_FEE +
                ", SALES_TAX=" + SALES_TAX +
                '}';
    }
}
package com.pluralsight.dealership.contracts;

import com.pluralsight.dealership.Dealership;
import com.pluralsight.roadVehicle.Vehicle;

public abstract class Contract {
    protected String date = null;
    protected String customerName = null;
    protected String customerEmail = null;
    protected Vehicle vehicle = null;
    protected boolean sold = false;
    protected double totalPrice = 0;
    protected int numberOfPayments = 0;
    protected double monthlyPayment = 0;

    protected Contract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean sold, int numberOfPayments) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicle = vehicle;
        this.sold = sold;
        this.totalPrice = getTotalPrice();
        this.numberOfPayments = numberOfPayments;
        this.monthlyPayment = this.totalPrice / this.numberOfPayments;
    }

    protected String getDate() {
        return this.date;
    };
    protected String getCustomerName() {
        return this.customerName;
    };
    protected String getCustomerEmail() {
        return this.customerEmail;
    };
    protected double getMonthlyPayment() {
        return this.totalPrice / this.numberOfPayments;
    }

    protected abstract boolean getSoldStatus ();
    protected abstract double getTotalPrice();

    protected abstract Vehicle getVehicle(int vin, Dealership currentDealer);


}

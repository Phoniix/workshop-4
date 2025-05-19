package com.pluralsight.dealership.contracts;

import com.pluralsight.dealership.Dealership;
import com.pluralsight.roadVehicle.Vehicle;

public abstract class Contract extends Dealership {
    protected String DATE = null;
    protected String CUSTOMER_NAME = null;
    protected String CUSTOMER_EMAIL = null;
    protected Vehicle VEHICLE = null;
    protected boolean SOLD = false;
    protected double TOTAL_PRICE = 0;
    protected int NUMBER_OF_PAYMENTS = 0;
    protected double MONTHLY_PAYMENT = 0;

    protected Contract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean sold, int numberOfPayments) {
        this.DATE = date;
        this.CUSTOMER_NAME = customerName;
        this.CUSTOMER_EMAIL = customerEmail;
        this.VEHICLE = vehicle;
        this.SOLD = sold;
        this.TOTAL_PRICE = getTotalPrice();
        this.NUMBER_OF_PAYMENTS = numberOfPayments;
        this.MONTHLY_PAYMENT = this.TOTAL_PRICE / this.NUMBER_OF_PAYMENTS;
    }

    protected String getdate() {
        return this.DATE;
    };
    protected String getCustomerName() {
        return this.CUSTOMER_NAME;
    };
    protected String getCustomerEmail() {
        return this.CUSTOMER_EMAIL;
    };
    protected double getMonthlyPayment() {
        return this.TOTAL_PRICE / this.NUMBER_OF_PAYMENTS;
    }

    protected abstract boolean getSoldStatus ();
    protected abstract double getTotalPrice();



}

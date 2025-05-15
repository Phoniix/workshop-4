package com.pluralsight.roadVehicle;

public class Vehicle {

    private final int vin;
    private final int year;
    private final String make;
    private final String model;
    private final String vehicleType;
    private final String color;
    private final int odometer;
    private final double price;

    // Constructor // -------------------------------------------------------------------------------------------------
    public Vehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color=color;
        this.odometer = odometer;
        this.price = price;
        }

    // Getters // Don't need setters because vehicle info will not change one input into system. ----------------------
    public int getVin() {
        return vin;
    }
    public int getYear() {
        return year;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public String getVehicleType() {
        return vehicleType;
    }
    public String getColor() {
        return color;
    }
    public int getOdometer() {
        return odometer;
    }
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        String format = "%-10s|%-6s|%-14s|%-10s|%-12s|%-10s|%-8s|%-10s";
        return vin + "|" + year + "|" +  make + "|" + model + "|" + vehicleType + "|" + color + "|" + odometer + "|" + String.format("%.2f", price);
    }
}

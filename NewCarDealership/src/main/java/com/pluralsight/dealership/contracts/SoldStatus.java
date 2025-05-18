package com.pluralsight.dealership.contracts;

public enum SoldStatus {
    SOLD("Print A"),
    LEASED("Print B"),
    NOT_SOLD("Print C");

    final String action;

    SoldStatus(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}

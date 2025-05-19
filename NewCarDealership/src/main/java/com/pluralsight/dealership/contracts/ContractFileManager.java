package com.pluralsight.dealership.contracts;

import com.pluralsight.dealership.Dealership;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;

public class ContractFileManager {
    private final String FILE_PATH = "allContracts.csv";
    private int dealerID = 0;

    public ContractFileManager(int dealerID) {
        this.dealerID = dealerID;
    }

    public void writeSale (Contract contract, Dealership currentDealer) throws IOException {

        try (BufferedWriter saleWriter = new BufferedWriter(new FileWriter(FILE_PATH))) {
            
        }
    }
}

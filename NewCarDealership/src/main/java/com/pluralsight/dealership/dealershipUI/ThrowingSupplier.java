package com.pluralsight.dealership.dealershipUI;

import java.io.IOException;
import java.text.ParseException;

public interface ThrowingSupplier<T, R> {
    R apply(T t) throws IOException, InterruptedException, ParseException;
}

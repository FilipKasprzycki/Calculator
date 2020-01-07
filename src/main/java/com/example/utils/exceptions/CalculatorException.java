package com.example.utils.exceptions;

import lombok.extern.java.Log;

import java.util.Arrays;

@Log
public final class CalculatorException extends Exception {

    private static final String LOG_MESSAGE = "[!] Exception occured.\n[!] Exception message: %s\n[!] Cause: %s\n[!] Stack trace:\n%s";

    public CalculatorException(Throwable cause) {
        super(cause);
        log.severe(String.format(LOG_MESSAGE,
                cause.getMessage(),
                cause.toString(),
                Arrays.toString(cause.getStackTrace()).replace(",", "\n")));
    }
}

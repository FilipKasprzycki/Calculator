package com.example;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class Converter {

    static String dmsToDm(@NonNull String degrees, @NonNull String minutes, @NonNull String seconds) {
        return dmsToDm(stringToDouble(degrees), stringToDouble(minutes), stringToDouble(seconds));
    }

    static String dmToDms(@NonNull String degrees, @NonNull String minutes) {
        return dmToDms(stringToDouble(degrees), stringToDouble(minutes));
    }

    private static String dmsToDm(double degrees, double minutes, double seconds) {
        minutes += seconds / 60;
        return degrees + "*   " + minutes + "'";
    }

    private static String dmToDms(double degrees, double minutes) {
        double seconds = (minutes - (int) minutes) * 60;
        return degrees + "*   " + (int) minutes + "'   " + seconds + "\"";
    }

    private static double stringToDouble(String value) {
        return Double.parseDouble(value);
    }
}
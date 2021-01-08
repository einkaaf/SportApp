package com.erfankazemi.drtarmast.Util;

public class BmiUtil {

    public static boolean isMan;
    public static double height;
    public static double weight;

    public static double Bmi() {
        if (height > 0 && weight > 0) {

            double heightMeter = height / 100d;
            double result = (weight) / (heightMeter * heightMeter);
            return Util.round(result, 2);
        } else {
            return 0;
        }
    }

    public static double getBurnedCaleries(double step) {
        double burnedCaleries = step * 0.5263;
        return burnedCaleries;
    }

    public static double getCoverdDistance(double step) {
        double coverdDistance = step * 0.0008;
        return coverdDistance;
    }

}

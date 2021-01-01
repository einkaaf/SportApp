package com.erfankazemi.drtarmast.Util;

public class BmiCalculator {

  public static boolean isMan;
  public static double height;
  public static double weight;

  public static double Bmi() {
    if (height > 0 && weight > 0) {

      double heightMeter = height / 100d;
      double result = (weight) / (heightMeter * heightMeter);
      return round(result, 2);
    } else {
      return 0;
    }
  }

  public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
  }
}

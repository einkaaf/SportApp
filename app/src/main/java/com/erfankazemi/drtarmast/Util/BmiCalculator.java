package com.erfankazemi.drtarmast.Util;

public class BmiCalculator {

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


}

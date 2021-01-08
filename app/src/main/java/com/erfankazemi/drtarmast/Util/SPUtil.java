package com.erfankazemi.drtarmast.Util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SPUtil {
  public static void saveStep(float step, Context context) {
    SharedPreferences pref = context.getSharedPreferences("EK", MODE_PRIVATE);
    SharedPreferences.Editor editor = pref.edit();
    editor.putFloat("EK-Step", step);
    editor.apply();
  }
  public static float getStep(Context context) {
    SharedPreferences pref = context.getSharedPreferences("EK", MODE_PRIVATE);
    return pref.getFloat("EK-Step", 0);
  }
}

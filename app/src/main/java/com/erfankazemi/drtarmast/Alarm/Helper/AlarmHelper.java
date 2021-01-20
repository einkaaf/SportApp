package com.erfankazemi.drtarmast.Alarm.Helper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.erfankazemi.drtarmast.Alarm.Service.AlarmService;

import java.util.Calendar;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;

public class AlarmHelper {

  // status is what kind of notification we want to create for
  // 10001 for bmi
  // 10002 for idealWeight
  // 10003 for distance
  // 10004 for exercise time

  public static void SetAlarm(Context context, Calendar calendar, int status) {
    Intent intent = new Intent(context, AlarmService.class);
    PendingIntent sender = PendingIntent.getBroadcast(context, 2, intent, FLAG_CANCEL_CURRENT);
    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    if (alarmManager != null) {
      if (calendar.before(Calendar.getInstance())) {
        calendar.add(Calendar.DATE, 1);
      }
      alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
    }
  }
}

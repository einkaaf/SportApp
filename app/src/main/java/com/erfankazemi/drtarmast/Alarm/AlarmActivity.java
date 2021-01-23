package com.erfankazemi.drtarmast.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.erfankazemi.drtarmast.Alarm.Service.AlarmNotificationReciver;
import com.erfankazemi.drtarmast.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class AlarmActivity extends AppCompatActivity {

  AlarmManager alarmManager;
  PendingIntent pendingIntent;
  SwitchCompat switchWeight, switchExercise;

  boolean weightState = false, exerciseState = false;

  int stateIntent = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //-------------------------------------------------------------------------
    getSupportActionBar().hide();
    //-------------------------------------------------------------------------
    ViewPump.init(ViewPump.builder()
      .addInterceptor(new CalligraphyInterceptor(
        new CalligraphyConfig.Builder()
          .setDefaultFontPath("fonts/vazir.ttf")
          .setFontAttrId(R.attr.fontPath)
          .build()))
      .build());
    setContentView(R.layout.activity_alarm);
    //--------------Time Picker-----------------------------------------------------------
    alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    Intent intent = new Intent(this, AlarmNotificationReciver.class);

    switchWeight = findViewById(R.id.switchWeight);

    switchWeight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        weightState = isChecked;

        if (weightState && exerciseState) {

          intent.putExtra("notificationState", "1");
          pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 10, intent, 0);
          alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, 3000, pendingIntent);
        }
        if (!weightState && exerciseState) {
          intent.putExtra("notificationState", "2");
          pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 10, intent, 0);
          alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, 3000, pendingIntent);
        }
        if (weightState && !exerciseState) {
          intent.putExtra("notificationState", "3");
          pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 10, intent, 0);
          alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, 3000, pendingIntent);
        }
        if (!weightState && !exerciseState) {
          intent.putExtra("notificationState", "4");
          pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 10, intent, 0);
          alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, 3000, pendingIntent);
        }


      }
    });

    switchExercise = findViewById(R.id.switchExercise);

    switchExercise.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        exerciseState = isChecked;

        if (weightState && exerciseState) {

          intent.putExtra("notificationState", "1");
          pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 10, intent, 0);
          alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, 3000, pendingIntent);
        }
        if (!weightState && exerciseState) {
          intent.putExtra("notificationState", "2");
          pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 10, intent, 0);
          alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, 3000, pendingIntent);
        }
        if (weightState && !exerciseState) {
          intent.putExtra("notificationState", "3");
          pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 10, intent, 0);
          alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, 3000, pendingIntent);
        }
        if (!weightState && !exerciseState) {
          intent.putExtra("notificationState", "4");
          pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 10, intent, 0);
          alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, 3000, pendingIntent);
        }
      }
    });


    intent.putExtra("notificationState", "4");
    pendingIntent = PendingIntent.getBroadcast(this, 10, intent, 0);


    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, 3000, pendingIntent);


  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
  }

}
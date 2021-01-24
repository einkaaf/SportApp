package com.erfankazemi.drtarmast.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.CompoundButton;

import com.erfankazemi.drtarmast.Alarm.Service.AlarmNotificationReciver;
import com.erfankazemi.drtarmast.MainActivity;
import com.erfankazemi.drtarmast.R;
import com.erfankazemi.drtarmast.Util.DB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import static com.erfankazemi.drtarmast.Config.TimeConfig.startTimeInterval;
import static com.erfankazemi.drtarmast.Config.TimeConfig.timeInterval;

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
    intent.putExtra("notificationState", "4");

    pendingIntent = PendingIntent.getBroadcast(this, 10, intent, 0);

    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + startTimeInterval, timeInterval, pendingIntent);


    switchWeight = findViewById(R.id.switchWeight);

    switchWeight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        weightState = isChecked;

        if (weightState && exerciseState) {

          CancelAlarm();
          StartAlarm(1);

        }
        if (!weightState && exerciseState) {

          CancelAlarm();
          StartAlarm(2);

        }
        if (weightState && !exerciseState) {

          CancelAlarm();
          StartAlarm(3);

        }
        if (!weightState && !exerciseState) {

          CancelAlarm();
          StartAlarm(4);

        }

      }
    });

    switchExercise = findViewById(R.id.switchExercise);

    switchExercise.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        exerciseState = isChecked;

        if (weightState && exerciseState) {

          CancelAlarm();
          StartAlarm(1);

        }
        if (!weightState && exerciseState) {

          CancelAlarm();
          StartAlarm(2);

        }
        if (weightState && !exerciseState) {

          CancelAlarm();
          StartAlarm(3);

        }
        if (!weightState && !exerciseState) {

          CancelAlarm();
          StartAlarm(4);

        }
      }
    });


  }

  private void CancelAlarm() {
    Intent intent = new Intent(AlarmActivity.this, AlarmNotificationReciver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 10, intent, 0);
    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    alarmManager.cancel(pendingIntent);
  }

  private void StartAlarm(int state) {
    if (state == 0) {
      state = 5;
    }

    DB.saveData(AlarmActivity.this, "NOTIF", String.valueOf(state));

    AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
    Intent intent = new Intent(this, AlarmNotificationReciver.class);
    pendingIntent = PendingIntent.getBroadcast(this, 10, intent, 0);
    am.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + startTimeInterval, timeInterval, pendingIntent);

  }

  @Override
  public void onBackPressed() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    finishAffinity();

  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
  }

}
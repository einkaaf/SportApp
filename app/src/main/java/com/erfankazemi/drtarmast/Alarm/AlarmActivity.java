package com.erfankazemi.drtarmast.Alarm;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

import com.erfankazemi.drtarmast.Alarm.Helper.AlarmHelper;
import com.erfankazemi.drtarmast.R;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class AlarmActivity extends AppCompatActivity {


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

    TimePickerDialog dialog = new TimePickerDialog(this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
      @Override
      public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        AlarmHelper.SetAlarm(AlarmActivity.this, c, 1);
        Toast.makeText(AlarmActivity.this, "ok" + hourOfDay + minute, Toast.LENGTH_SHORT).show();
      }
    }, 1, 0, true);


    dialog.setButton(DialogInterface.BUTTON_POSITIVE, "تایید", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {

      }
    });

    dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "بیخیال", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {

      }
    });

    dialog.setCancelable(false);

    dialog.show();

    //--------------Time Picker-----------------------------------------------------------
  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
  }

}
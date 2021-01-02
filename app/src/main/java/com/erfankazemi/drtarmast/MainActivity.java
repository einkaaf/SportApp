package com.erfankazemi.drtarmast;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.erfankazemi.drtarmast.BMI.SexActivity;
import com.erfankazemi.drtarmast.SpeakTest.SpeakTestActivity;
import com.erfankazemi.drtarmast.Util.Util;

import androidx.appcompat.app.AppCompatActivity;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

  private int numSteps;
  double coveredDistanceMeter;
  TextView stepCounter;
  TextView coveredDistance;
  SensorManager sensorManager;
  Boolean running = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //-------------------------------------------------------------------------
    getSupportActionBar().hide();
    //-------------------------------------------------------------------------
    ViewPump.init(ViewPump.builder()
      .addInterceptor(new CalligraphyInterceptor(
        new CalligraphyConfig.Builder()
          .setDefaultFontPath("fonts/Avtheme.ttf")
          .setFontAttrId(R.attr.fontPath)
          .build()))
      .build());
    setContentView(R.layout.activity_main);
    //-------------------------------------------------------------------------

    sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    //-------------------------------------------------------------------------
    stepCounter = findViewById(R.id.stepCounter);
    coveredDistance = findViewById(R.id.coveredDistance);
    //-------------------------------------------------------------------------
    stepCounter.setText("1");

  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
  }

  public void BmiCalculate(View view) {
    Intent intent = new Intent(this, SexActivity.class);
    startActivity(intent);
  }

  public void SpeakTest(View view) {
    Intent intent = new Intent(this, SpeakTestActivity.class);
    startActivity(intent);
  }

  @Override
  protected void onResume() {
    super.onResume();
    running = true;
    Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
    if (countSensor != null) {
      sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_NORMAL);
    } else {
      Toast.makeText(this, "گوشی شما فاقد سنسور گام شمار است!", Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  protected void onPause() {
    super.onPause();
    running = false;
  }

  @Override
  public void onSensorChanged(SensorEvent event) {
    if (running) {
      stepCounter.setText(String.valueOf(event.values[0]));
      coveredDistanceMeter = event.values[0] * 0.0008;
      coveredDistance.setText(String.valueOf(Util.round(coveredDistanceMeter, 2) + "\n متر"));
    }
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {
  }

}

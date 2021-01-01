package com.erfankazemi.drtarmast;

import androidx.appcompat.app.AppCompatActivity;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.erfankazemi.drtarmast.BMI.SexActivity;
import com.erfankazemi.drtarmast.Pedometer.StepDetector;
import com.erfankazemi.drtarmast.Pedometer.StepListener;

public class MainActivity extends AppCompatActivity implements SensorEventListener, StepListener {

  private int numSteps;
  private StepDetector simpleStepDetector;
  private SensorManager sensorManager;
  private Sensor accel;
  TextView stepCounter ;
  TextView coveredDistance;
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
    sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    simpleStepDetector = new StepDetector();
    simpleStepDetector.registerListener(this);
    numSteps = 0;
    sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
    //-------------------------------------------------------------------------
    stepCounter  =findViewById(R.id.stepCounter);
    coveredDistance = findViewById(R.id.coveredDistance);
    //-------------------------------------------------------------------------

  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
  }

  public void BmiCalculate(View view) {
    Intent intent = new Intent(this, SexActivity.class);
    startActivity(intent);
  }

  @Override
  public void onSensorChanged(SensorEvent sensorEvent) {
    if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
      simpleStepDetector.updateAccel(
        sensorEvent.timestamp, sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
    }
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int i) {

  }

  @Override
  public void step(long timeNs) {
    numSteps++;
    stepCounter.setText(numSteps);
    double distance=numSteps*0.00075;
    coveredDistance.setText(""+distance);
  }
}
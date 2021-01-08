package com.erfankazemi.drtarmast;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.erfankazemi.drtarmast.BMI.SexActivity;
import com.erfankazemi.drtarmast.SpeakTest.SpeakTestInfoActivity;
import com.erfankazemi.drtarmast.Util.BmiUtil;
import com.erfankazemi.drtarmast.Util.Util;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

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
    TextView burnedCalories;
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
        //-------------------------------------Permission------------------------------------
        Dexter.withContext(this)
                .withPermission(Manifest.permission.ACTIVITY_RECOGNITION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Toast.makeText(MainActivity.this, "دسترسی لازم داده شد", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(MainActivity.this, "برای محاسبه دقیق قدم شمار و کالری سوزانده شده به این دسترسی نیاز داریم !", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
        //-------------------------------------------------------------------------

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //-------------------------------------------------------------------------
        stepCounter = findViewById(R.id.stepCounter);
        coveredDistance = findViewById(R.id.coveredDistance);
        burnedCalories = findViewById(R.id.txtCalories);
        //-------------------------------------------------------------------------
        stepCounter.setText("0");
        burnedCalories.setText("0");

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
        Intent intent = new Intent(this, SpeakTestInfoActivity.class);
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
            coveredDistance.setText(String.valueOf(Util.round(BmiUtil.getCoverdDistance(event.values[0]), 2)));
            burnedCalories.setText(String.valueOf(Util.round(BmiUtil.getBurnedCaleries(event.values[0]), 2)));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}

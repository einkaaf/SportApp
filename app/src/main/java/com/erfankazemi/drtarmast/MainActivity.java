package com.erfankazemi.drtarmast;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.erfankazemi.drtarmast.BMI.SexActivity;
import com.erfankazemi.drtarmast.BorgTest.BorgTestActivity;
import com.erfankazemi.drtarmast.SpeakTest.SpeakTestInfoActivity;
import com.erfankazemi.drtarmast.StepService.StepService;
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

import static com.erfankazemi.drtarmast.Util.Util.aa;

public class MainActivity extends AppCompatActivity {

  TextView stepCounter;
  TextView coveredDistance;
  TextView burnedCalories;

  final Handler handler = new Handler();

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
    stepCounter = findViewById(R.id.stepCounter);
    coveredDistance = findViewById(R.id.coveredDistance);
    burnedCalories = findViewById(R.id.txtCalories);
    //-------------------------------------------------------------------------
    stepCounter.setText("0");
    burnedCalories.setText("0");
//
//
    Intent serviceintent = new Intent(this, StepService.class);
    startService(serviceintent);

  }

  @Override
  protected void onResume() {
    final int delay = 1500;
    handler.postDelayed(new Runnable() {
      public void run() {
        stepCounter.setText(Util.toPersianNumber(String.valueOf(aa)));
        coveredDistance.setText(Util.toPersianNumber(String.valueOf(BmiUtil.getCoverdDistance(aa))));
        burnedCalories.setText(Util.toPersianNumber(String.valueOf(BmiUtil.getBurnedCaleries(aa))));
        handler.postDelayed(this, delay);
      }
    }, delay);
    super.onResume();
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

  public void BorgTest(View view) {
    Intent intent = new Intent(this, BorgTestActivity.class);
    startActivity(intent);
  }
}

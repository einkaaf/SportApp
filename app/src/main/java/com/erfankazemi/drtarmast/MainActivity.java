package com.erfankazemi.drtarmast;

import android.content.Context;
import android.content.Intent;
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
import com.erfankazemi.drtarmast.Util.DB;
import com.erfankazemi.drtarmast.Util.Util;

import androidx.appcompat.app.AppCompatActivity;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity {

  TextView stepCounter;
  TextView coveredDistance;
  TextView burnedCalories;
  TextView speakStatus;

  int step;

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

    //-------------------------------------------------------------------------
    stepCounter = findViewById(R.id.stepCounter);
    coveredDistance = findViewById(R.id.coveredDistance);
    burnedCalories = findViewById(R.id.txtCalories);

    speakStatus = findViewById(R.id.speakStatus);
    //-------------------------------------------------------------------------
    stepCounter.setText("0");
    burnedCalories.setText("0");

    Intent serviceintent = new Intent(this, StepService.class);
    startService(serviceintent);

  }

  @Override
  protected void onResume() {

    final int delay = 1500;

    handler.postDelayed(new Runnable() {
      public void run() {
        step = DB.getIntData(MainActivity.this, "step");
        if (step > 0) {
          step = step - 1;
        }
        stepCounter.setText(Util.toPersianNumber(String.valueOf(step)));
        coveredDistance.setText(Util.toPersianNumber(String.valueOf(BmiUtil.getCoverdDistance(step))));
        burnedCalories.setText(Util.toPersianNumber(String.valueOf(BmiUtil.getBurnedCaleries(step))));
        handler.postDelayed(this, delay);
      }
    }, delay);
    speakStatus.setText(DB.getStringData(MainActivity.this, "speak"));
    super.onResume();
  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
  }

  public void BmiCalculate(View view) {
    Intent intent = new Intent(this, SexActivity.class);
    startActivity(intent);
    finish();
  }

  public void SpeakTest(View view) {
    Intent intent = new Intent(this, SpeakTestInfoActivity.class);
    startActivity(intent);
    finish();
  }

  public void BorgTest(View view) {
    Intent intent = new Intent(this, BorgTestActivity.class);
    startActivity(intent);
    finish();
  }
  private boolean backPressedToExitOnce;

  @Override
  public void onBackPressed() {
    if (backPressedToExitOnce) {
      super.onBackPressed();
      return;
    }

    this.backPressedToExitOnce = true;
    Toast.makeText(this, "برای خروج دوباره بازگشت را بزنید!", Toast.LENGTH_SHORT).show();

    new Handler().postDelayed(new Runnable() {

      @Override
      public void run() {
        backPressedToExitOnce=false;
      }
    }, 2000);

  }
}

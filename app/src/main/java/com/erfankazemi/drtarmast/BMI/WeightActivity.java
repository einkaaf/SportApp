package com.erfankazemi.drtarmast.BMI;

import androidx.appcompat.app.AppCompatActivity;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import kotlin.Unit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.erfankazemi.drtarmast.R;
import com.erfankazemi.drtarmast.Util.BmiUtil;
import com.ramotion.fluidslider.FluidSlider;

public class WeightActivity extends AppCompatActivity {
  TextView txt;
  FluidSlider slider;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().hide();
    ViewPump.init(ViewPump.builder()
      .addInterceptor(new CalligraphyInterceptor(
        new CalligraphyConfig.Builder()
          .setDefaultFontPath("fonts/vazir.ttf")
          .setFontAttrId(R.attr.fontPath)
          .build()))
      .build());
    setContentView(R.layout.activity_weight);


    txt = findViewById(R.id.txt_height);
    slider = findViewById(R.id.slider);

    txt.setText("0" + "\n" + "کیلوگرم");

    slider.setPositionListener(pos -> {
      final String value = String.valueOf((int) (pos * 250));
      slider.setBubbleText(value);
      txt.setText(value + "\n" + "کیلوگرم");
      BmiUtil.weight = Double.parseDouble(value);
      return Unit.INSTANCE;
    });
  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
  }


  public void ResultClick(View view) {
    Intent intent = new Intent(this, BmiResultActivity.class);
    startActivity(intent);
    finishAffinity();
  }


  public void minHeight(View view) {
    int value = Integer.parseInt(txt.getText().toString().split("\n")[0]) - 1;
    float aa = (float) value / 250;
    if (value >= 0) {
      slider.setPosition(aa);
      txt.setText("" + value + "\n" + "کیلوگرم");
    }
  }

  public void maxHeight(View view) {
    int value = Integer.parseInt(txt.getText().toString().split("\n")[0]) + 1;
    float aa = (float) value / 250;
    if (value <= 250) {
      slider.setPosition(aa);
      txt.setText("" + value + "\n" + "کیلوگرم");

    }
  }
}
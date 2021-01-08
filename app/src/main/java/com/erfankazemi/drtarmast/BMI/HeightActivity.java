package com.erfankazemi.drtarmast.BMI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.erfankazemi.drtarmast.R;
import com.erfankazemi.drtarmast.Util.BmiUtil;
import com.ramotion.fluidslider.FluidSlider;

import androidx.appcompat.app.AppCompatActivity;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import kotlin.Unit;

public class HeightActivity extends AppCompatActivity {
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
    setContentView(R.layout.activity_height);


    txt = findViewById(R.id.txt_height);
    slider = findViewById(R.id.slider);

    txt.setText("0" + "\n" + "سانتیمتر");

    slider.setPositionListener(pos -> {
      final String value = String.valueOf((int) (pos * 300));
      slider.setBubbleText(value);
      txt.setText(value + "\n" + "سانتیمتر");
      BmiUtil.height = Double.parseDouble(value);
      return Unit.INSTANCE;
    });
  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
  }

  public void WeightClick(View view) {
    Intent intent = new Intent(this, WeightActivity.class);
    startActivity(intent);
  }

  public void minHeight(View view) {
    int value = Integer.parseInt(txt.getText().toString().split("\n")[0]) - 1;
    float aa = (float) value / 300;
    if (value >= 0) {
      slider.setPosition(aa);
      txt.setText("" + value + "\n" + "سانتیمتر");
    }
  }

  public void maxHeight(View view) {
    int value = Integer.parseInt(txt.getText().toString().split("\n")[0]) + 1;
    float aa = (float) value / 300;
    if (value <= 300) {
      slider.setPosition(aa);
      txt.setText("" + value + "\n" + "سانتیمتر");
    }
  }
}
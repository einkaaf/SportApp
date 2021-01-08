package com.erfankazemi.drtarmast.BMI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.erfankazemi.drtarmast.R;
import com.erfankazemi.drtarmast.Util.BmiUtil;

import org.jetbrains.annotations.NotNull;

import androidx.appcompat.app.AppCompatActivity;
import io.ghyeok.stickyswitch.widget.StickySwitch;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class SexActivity extends AppCompatActivity {
  ImageView img;

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
    setContentView(R.layout.activity_bmi);

    img = findViewById(R.id.img);
    img.setImageResource(R.drawable.man);
    StickySwitch aSwitch = findViewById(R.id.sticky_switch);

    aSwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
      @Override
      public void onSelectedChange(@NotNull StickySwitch.Direction direction, @NotNull String s) {
        switch (direction) {
          case RIGHT:
            img.setImageResource(R.drawable.woman);
            BmiUtil.isMan = false;
            break;
          case LEFT:
            img.setImageResource(R.drawable.man);
            BmiUtil.isMan = true;
            break;
          default:
            img.setImageResource(R.drawable.man);
            BmiUtil.isMan = true;
            break;
        }
      }
    });


  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
  }

  public void HeightClick(View view) {
    Intent intent = new Intent(this, HeightActivity.class);
    startActivity(intent);
  }
}
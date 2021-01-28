package com.erfankazemi.drtarmast.ContactUs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.erfankazemi.drtarmast.MainActivity;
import com.erfankazemi.drtarmast.R;

import androidx.appcompat.app.AppCompatActivity;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class ContactUsActivity extends AppCompatActivity {

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
    setContentView(R.layout.activity_contact_us);
    //-------------------------------------------------------------------------

  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
  }

  public void goHome(View view) {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    finishAffinity();
  }
}
package com.erfankazemi.drtarmast.BorgTest;

import android.content.Context;
import android.os.Bundle;

import com.erfankazemi.drtarmast.BorgTest.Adapter.BorgTestAdapter;
import com.erfankazemi.drtarmast.R;

import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class BorgTestActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().hide();
    //-------------------------------------------------------------------------
    ViewPump.init(ViewPump.builder()
      .addInterceptor(new CalligraphyInterceptor(
        new CalligraphyConfig.Builder()
          .setDefaultFontPath("fonts/vazir.ttf")
          .setFontAttrId(R.attr.fontPath)
          .build()))
      .build());
    //-------------------------------------------------------------------------
    setContentView(R.layout.activity_borg_test);

    List<String> desc = Arrays.asList("سلام", "1", "2", "33", "44", "4455", "4455", "4455", "4455", "4455");

    RecyclerView borgCat = findViewById(R.id.borgRcv);
    borgCat.setAdapter(new BorgTestAdapter(this, desc));
    borgCat.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
  }
  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
  }
}
package com.erfankazemi.drtarmast.SpeakTest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.erfankazemi.drtarmast.R;
import com.erfankazemi.drtarmast.Util.DB;

import androidx.appcompat.app.AppCompatActivity;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import segmented_control.widget.custom.android.com.segmentedcontrol.SegmentedControl;
import segmented_control.widget.custom.android.com.segmentedcontrol.item_row_column.SegmentViewHolder;
import segmented_control.widget.custom.android.com.segmentedcontrol.listeners.OnSegmentSelectRequestListener;

public class SpeakTestActivity extends AppCompatActivity {
  SegmentedControl control;

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
    //-------------------------------------------------------------------------
    setContentView(R.layout.activity_speak_test);

    Intent intent = new Intent(SpeakTestActivity.this, SpeakTestResultActivity.class);


    control = findViewById(R.id.segmented_control);

    control.setOnSegmentSelectRequestListener(new OnSegmentSelectRequestListener() {
      @Override
      public boolean onSegmentSelectRequest(SegmentViewHolder segmentViewHolder) {
        switch (segmentViewHolder.getAbsolutePosition()) {
          case 0:
            DB.saveData(SpeakTestActivity.this, "SPK", "فشار زیاد تمرین");
            break;
          case 1:
            DB.saveData(SpeakTestActivity.this, "SPK", "فشار نسبتا زیاد");
            break;
          case 2:
            DB.saveData(SpeakTestActivity.this, "SPK", "ترین نرمال");
            break;
          case 3:
            DB.saveData(SpeakTestActivity.this, "SPK", "بدون سختی");
            break;
          default:
            DB.saveData(SpeakTestActivity.this, "SPK", "بدون مقدار");
            break;
        }
        intent.putExtra("activity-hardness", String.valueOf(segmentViewHolder.getAbsolutePosition()));
        startActivity(intent);
        finish();
        return true;
      }
    });


  }

  @Override
  protected void onResume() {
    control.clearSelection();
    super.onResume();
  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
  }

  @Override
  public void onBackPressed() {
    Intent intent = new Intent(this, SpeakTestInfoActivity.class);
    startActivity(intent);
    finishAffinity();
  }
}
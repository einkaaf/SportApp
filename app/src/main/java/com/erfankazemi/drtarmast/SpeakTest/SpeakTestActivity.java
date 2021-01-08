package com.erfankazemi.drtarmast.SpeakTest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.erfankazemi.drtarmast.R;

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
                intent.putExtra("activity-hardness", String.valueOf(segmentViewHolder.getAbsolutePosition()));
                startActivity(intent);
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
}
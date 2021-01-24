package com.erfankazemi.drtarmast.SpeakTest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.erfankazemi.drtarmast.MainActivity;
import com.erfankazemi.drtarmast.R;

import androidx.appcompat.app.AppCompatActivity;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class SpeakTestResultActivity extends AppCompatActivity {

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
    setContentView(R.layout.activity_speak_test_result);

    Intent intent = getIntent();
    String condition = intent.getStringExtra("activity-hardness");

    ScrollView scrollView = findViewById(R.id.speaktestback);
    TextView title = findViewById(R.id.speaktestTitle);
    TextView desc = findViewById(R.id.speaktestDesc);

    switch (condition) {
      case "0":
        title.setText("فشار زیاد تمرین");
        desc.setText("معمولا ورزشکاران در زمان ورزش ضربان قلب خود را اندازه\u200Cگیری می\u200Cکنند. بهتر است در زمان استراحت نیز ضربان قلب خود را اندازه\u200Cگیری کنید. اگر ضربان قلب شما در حالت استراحت به صورت غیر طبیعی بیشتر یا کمتر از حد زمان بود باید به پزشک مراجعه کنید و ممکن است که به دلیل فشار زیاد تمرین این اتفاق افتاده باشد.");
        scrollView.setBackgroundResource(R.drawable.bmi_gradient_extra);
        break;
      case "1":
        title.setText("فشار نسبتا زیاد");
        desc.setText("یک برنامه ورزشی خوب آن است که پس از آن احساس شادابی و پر انرژی بودن کنید و توانایی انجام کارهای روزمره خود را از دست ندهید. پس اگر احساس می کنید که خسته می شوید یا اینکه انرژی تان تمام می شود، بدانید که بیش از اندازه ورزش می کنید.");
        scrollView.setBackgroundResource(R.drawable.bmi_gradient_extra);
        break;
      case "2":
        title.setText("ترین نرمال");
        desc.setText("خیلی جالب است که بدانید برای بیمارانی که قبلا سکته\u200Cی قلبی را تجربه کرده\u200Cاند، ورزش و تحرک فیزیکی حتی از درمان دارویی هم مؤثرتر بود. پس هر چه زودتر با پزشک\u200Cتان صحبت کنید تا با کمک هم یک برنامه\u200Cی ورزشی مناسب را تنظیم کنید");
        scrollView.setBackgroundResource(R.drawable.bmi_gradient_good);
        break;
      case "3":
        title.setText("بدون سختی");
        desc.setText("کم تحرکی باعث افزایش بار کاری در قلب به دلیل افزایش ویسکوزیته ناشی از کم آبی بدن و کاهش بازگشت وریدی می شود. در حالت دراز کش خون در اندام های تحتانی جمع نمی گردد وبازگشت خون به قلب افزایش می یابد که موجب افزایش حجم ضربه ای وضربان قلب در حالت استراحت می گردد . قلب در زمان استراحت بدن به دلیل مقاومت کمتر عروق خونی در این حالت و تغییر در توزیع خون در فرد بی تحرک بیشتر کار می کند.");
        scrollView.setBackgroundResource(R.drawable.bmi_gradient_low);
        break;
      default:
        title.setText("----");
        desc.setText("----");
    }
  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
  }

  public void contactUs(View view) {

  }

  public void goHome(View view) {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    finish();
  }

  @Override
  public void onBackPressed() {
    Intent intent = new Intent(this, SpeakTestActivity.class);
    startActivity(intent);
    finish();
  }
}
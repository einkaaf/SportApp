package com.erfankazemi.drtarmast.BMI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.erfankazemi.drtarmast.MainActivity;
import com.erfankazemi.drtarmast.R;
import com.erfankazemi.drtarmast.Util.BmiUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import androidx.appcompat.app.AppCompatActivity;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class BmiResultActivity extends AppCompatActivity {
  View descView;
  TextView bmiDesc;
  BottomSheetDialog dialog;

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
    setContentView(R.layout.activity_bmi_result);

    Button btnDesc = findViewById(R.id.btnDesc);
    TextView score = findViewById(R.id.result_num);
    TextView shortText = findViewById(R.id.result_short_desc);
    TextView longText = findViewById(R.id.result_long_desc);
    LinearLayout background = findViewById(R.id.backLayout);


    descView = getLayoutInflater().inflate(R.layout.more_info_layout, null);
    bmiDesc = descView.findViewById(R.id.desc_bmi);
    dialog = new BottomSheetDialog(BmiResultActivity.this);
    dialog.setContentView(descView);


    btnDesc.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        dialog.show();
      }
    });

    double bmiResult = BmiUtil.Bmi();

    if (bmiResult < 18.5) {
      shortText.setText("دچار کمبود وزن هستید");
      longText.setText("BMI پایین\u200Cتر از ۱۸.۵ نشان دهنده این است که دچار کمبود وزن هستید، بنابراین شاید نیاز باشد کمی وزن اضافه کنید. توصیه می\u200Cکنیم از پزشک یا مشاور تغذیه خود کمک بگیرید.");
      bmiDesc.setText("چنانچه BMI زیر ۱۸/۵ باشد٬ فرد باید با مراجعه به پزشک در مورد پایین بودن وزن خود صحبت کند و در صورتی که مشکل خاصی وجود ندارد به کمک یک متخصص تغذیه وزن خود را تا حد بازه ایده آل افزایش دهد.");
      background.setBackgroundResource(R.drawable.bmi_gradient_low);
    } else if (bmiResult > 18.5 && bmiResult < 24.9) {
      shortText.setText("جسم سالم دارید");
      longText.setText("BMI بین ۱۸.۵ تا ۲۴٫۹ نشان می\u200Cدهد نسبت به قدتان وزن مناسبی دارید. با تثبیت وزنی سالم، جلوی بالا رفتن خطرهای جدی برای سلامت\u200Cتان را می\u200Cگیرید.");
      bmiDesc.setText(" چنانچه BMI فرد در محدوده ایده آل یعنی عددی بین ۱۸/۵ تا ۲۴/۹ باشد٬ نه تنها جای نگرانی نیست بلکه نقطه ایده آل همینجاست. این فرد میتواند با حفظ رژیم غذایی و سبک زندگی سالم و ورزش٬ وزن خود را در این محدوده حفظ کند و سلامتی نسبی خود را داشته باشد.");
      background.setBackgroundResource(R.drawable.bmi_gradient_good);

    } else if (bmiResult > 25 && bmiResult < 29.9) {
      shortText.setText("کمی اضافه وزن دارید");
      longText.setText("BMI بین ۲۵ تا ۲۹٫۹ به شما می\u200Cگوید کمی دچار اضافه وزن هستید و نیاز دارید مقداری وزن کم کنید. برای کاهش وزن می\u200Cتوانید با پزشک خود مشورتی داشته باشید.");
      bmiDesc.setText("چنانچه BMI بین ۲۵ تا ۲۹/۹ باشد٬ باید به پزشک متخصص مراجعه کرده و در مورد اضافه وزن خود٬\u200C درمانی را به شکل دارویی٬ رژیم و … آغاز کند.");
      background.setBackgroundResource(R.drawable.bmi_gradient_normal);
    } else if (bmiResult > 30) {
      shortText.setText("اضافه وزن شدید دارید");
      longText.setText("BMI بالای ۳۰ به شما اخطار می\u200Cدهد که وزن بالایی دارید. اگر وزن کم نکنید مسلما سلامت\u200Cتان به خطر خواهد افتاد. حتما با پزشک یا مشاور تغذیه خود صحبت و رژیم گرفتن را شروع کنید.");
      bmiDesc.setText("برای اینکه BMI خود را برای مدت\u200Cها پایین نگه دارید، باید تغییراتی ایجاد کنید که اثرات طولانی مدت داشته باشند.\n" +
        "برنامه\u200Cهای غذایی سخت و محدود شاید خیلی سریع وزنتان را پایین بیاورند اما معمولا این کاهش وزن مدت زمان طولانی باقی نمی\u200Cماند.\n" +
        "تغییر عادات غذایی و ورزش بهترین راه برای کاهش BMI است. برای تغییر اساسی در سلامت باید تغییرات زیادی انجام دهید\n" +
        ". BMI یکی از ابزارهای سنجش سلامت است، اما زمانی جواب می\u200Cدهد که با چندین آزمایش و اندازه گیری دیگر همراه شود\n" +
        "بسیاری از متخصصان توصیه می\u200Cکنند عادات خود را به مرور تغییر دهید و به آرامی آنها را تبدیل به تعهدهای طولانی مدت کنید.");
      background.setBackgroundResource(R.drawable.bmi_gradient_extra);
    }
    score.setText("" + bmiResult);
  }

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
  }


  public void goHome(View view) {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    finish();
  }

}
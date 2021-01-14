package com.erfankazemi.drtarmast.SplashScreen;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.erfankazemi.drtarmast.MainActivity;
import com.erfankazemi.drtarmast.R;
import com.erfankazemi.drtarmast.Util.DB;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().hide();
    setContentView(R.layout.activity_splash);
    Intent intent = new Intent(SplashActivity.this, MainActivity.class);

    //-------------------------------------Init MainActivty------------------------------------
    DB.saveData(SplashActivity.this, "speak", "بدون مقدار");
    //-------------------------------------Permission------------------------------------
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
      Dexter.withContext(this)
        .withPermission(Manifest.permission.ACTIVITY_RECOGNITION)
        .withListener(new PermissionListener() {
          @Override
          public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
            Toast.makeText(SplashActivity.this, "دسترسی لازم داده شد", Toast.LENGTH_SHORT).show();
            intent.putExtra("permission", true);
            startActivity(intent);
            finish();
          }

          @Override
          public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
            Toast.makeText(SplashActivity.this, "برای محاسبه دقیق قدم شمار و کالری سوزانده شده به این دسترسی نیاز داریم !", Toast.LENGTH_SHORT).show();
            intent.putExtra("permission", false);
            startActivity(intent);
            finish();
          }

          @Override
          public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
            permissionToken.continuePermissionRequest();
          }
        }).check();
    } else {
      Log.i("android version", "build version < 29");
    }
  }
}
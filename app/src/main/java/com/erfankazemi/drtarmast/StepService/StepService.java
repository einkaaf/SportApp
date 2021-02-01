
package com.erfankazemi.drtarmast.StepService;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.erfankazemi.drtarmast.MainActivity;
import com.erfankazemi.drtarmast.R;
import com.erfankazemi.drtarmast.Util.DB;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class StepService extends Service implements SensorEventListener {

  SensorManager sensorManager;
  int step;
  private boolean hasSensor = false;
  //-----------------------------------------------
  private final static String TAG = "StepDetector";
  private float mLimit = 15f;
  private float mLastValues[] = new float[3 * 2];
  private float mScale[] = new float[2];
  private float mYOffset;

  private float mLastDirections[] = new float[3 * 2];
  private float mLastExtremes[][] = {new float[3 * 2], new float[3 * 2]};
  private float mLastDiff[] = new float[3 * 2];
  private int mLastMatch = -1;


  //-----------------------------------------------
  @Override
  public void onCreate() {
//        Toast.makeText(this, "Service was Created", Toast.LENGTH_LONG).show();
    PackageManager packageManager = getPackageManager();

    if (packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER) || packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR)) {
      hasSensor = true;
      DB.saveData(this, "STEP_SENSOR_STATE", hasSensor);
      Toast.makeText(this, "true sensor", Toast.LENGTH_SHORT).show();
    } else {
      hasSensor = false;
      DB.saveData(this, "STEP_SENSOR_STATE", hasSensor);
      Toast.makeText(this, "false sensor", Toast.LENGTH_SHORT).show();
    }
    if (hasSensor) {

      sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

      Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

      sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_NORMAL);

    } else {
      sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
      Sensor mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
      StepDetector mStepDetector = new StepDetector(this);
      sensorManager.registerListener(mStepDetector, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    super.onCreate();

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      startMyOwnForeground();
    } else {
      startForeground(1, new Notification());
    }
    step = DB.getIntData(this, "step");
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {

    final Handler handler = new Handler();
    final int delay = 10000; // 1000 milliseconds == 1 second

    handler.postDelayed(new Runnable() {
      public void run() {
//        Toast.makeText(StepService.this, "I am running " + SPUtil.getStep(StepService.this), Toast.LENGTH_SHORT).show();
        Log.i("SERVICE", "START-SERVICE-COMMAND");
        handler.postDelayed(this, delay);
      }
    }, delay);

    return START_STICKY;
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  private void startMyOwnForeground() {
    String NOTIFICATION_CHANNEL_ID = "erfankazemi.sport.1";
    String channelName = "Sport App Channel 2";

    NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_HIGH);
    chan.setLightColor(Color.BLUE);
    chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

    NotificationManager manager = getSystemService(NotificationManager.class);
    assert manager != null;
    manager.createNotificationChannel(chan);

    Intent notificationIntent = new Intent(this, MainActivity.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
    Notification notification = notificationBuilder.setOngoing(true)
      .setSmallIcon(R.drawable.climb)
      .setContentTitle("Step Counter is Running!")
      .setContentText("Dr Tarmast Health App")
      .setPriority(NotificationManager.IMPORTANCE_MIN)
      .setCategory(Notification.CATEGORY_SERVICE)
      .setContentIntent(pendingIntent)
      .build();


    startForeground(2, notification);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
  }

  @Override
  public boolean onUnbind(Intent intent) {
    Toast.makeText(this, "Service Unbinded", Toast.LENGTH_LONG).show();
    return super.onUnbind(intent);
  }

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  //--------------------------------------------------------------------------------------------------------------------------------
  @Override
  public void onSensorChanged(SensorEvent event) {
    if (DB.getBooleanData(this, "STEP_SENSOR_STATE")) {
      Sensor sensor = event.sensor;
      if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
        step = step + 1;
        DB.saveData(this, "step", step);
      }
    }
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int i) {

  }

}
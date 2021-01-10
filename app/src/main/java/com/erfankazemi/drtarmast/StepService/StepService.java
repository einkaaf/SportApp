
package com.erfankazemi.drtarmast.StepService;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
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
import com.erfankazemi.drtarmast.Util.SPUtil;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class StepService extends Service implements SensorEventListener {

  SensorManager sensorManager;

  @Override
  public void onCreate() {
    Toast.makeText(this, "Service was Created", Toast.LENGTH_LONG).show();

    sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

    Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

    if (countSensor != null) {
      sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_NORMAL);
    } else {
      Toast.makeText(this, "گوشی شما فاقد سنسور گام شمار است!", Toast.LENGTH_SHORT).show();
    }

    super.onCreate();

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
      startMyOwnForeground();
    else
      startForeground(1, new Notification());

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
    String NOTIFICATION_CHANNEL_ID = "erfankazemi.sport";
    String channelName = "Sport App Channel";

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
      .setSmallIcon(R.drawable.ic_launcher_background)
      .setContentTitle("Step Counter is Running!")
      .setContentText("Dr Tarmast Sport App")
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
    SPUtil.saveStep(event.values[0], this);
    Toast.makeText(this, "Step saved: " + event.values[0], Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int i) {

  }
}
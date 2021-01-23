package com.erfankazemi.drtarmast.Alarm.Service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.erfankazemi.drtarmast.R;

import androidx.core.app.NotificationCompat;

public class AlarmNotificationReciver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {

    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    int notificationId = 1;
    String channelId = "Erfan-app-channel";
    String channelName = "ErfanKazemi";
    int importance = NotificationManager.IMPORTANCE_HIGH;

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
      NotificationChannel mChannel = new NotificationChannel(
        channelId, channelName, importance);
      notificationManager.createNotificationChannel(mChannel);
    }
    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);

    String notificationState = intent.getStringExtra("notificationState");

    switch (notificationState) {
      case "1":
        builder.setSmallIcon(R.drawable.body_scale)
          .setContentTitle("یادآوری")
          .setContentText("شما میتوانید با استفاده از Bmi مقدار اضافه وزن خود را محاسبه کنید  ورزش خود را شروع کنید")
          .setAutoCancel(true);
        break;
      case "2":
        builder.setSmallIcon(R.drawable.body_scale)
          .setContentTitle("یادآوری تمرین ورزشی")
          .setContentText("یادتان نرود که تمرین ورزشی خود را امروز انجام بدهید !")
          .setAutoCancel(true);
        break;
      case "3":
        builder.setSmallIcon(R.drawable.body_scale)
          .setContentTitle("یادآوری کحاسبه اضافه وزن")
          .setContentText("شما میتوانید با استفاده از Bmi مقدار اضافه وزن خود را محاسبه کنید!")
          .setAutoCancel(true);
        break;
      case "4":
        builder.setSmallIcon(R.drawable.body_scale)
          .setContentTitle("فراموش نکنید !")
          .setContentText("یادتان نرود که بدن خود را سالم نگه دارید !")
          .setAutoCancel(true);
        break;
      default:
        builder.setSmallIcon(R.drawable.body_scale)
          .setContentTitle("د !")
          .setContentText("یادتان نردارید !")
          .setAutoCancel(true);
        break;
    }


    TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

    stackBuilder.addNextIntent(intent);

    PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
      0,
      PendingIntent.FLAG_UPDATE_CURRENT
    );

    builder.setContentIntent(resultPendingIntent);

    notificationManager.notify(notificationId, builder.build());
  }
}

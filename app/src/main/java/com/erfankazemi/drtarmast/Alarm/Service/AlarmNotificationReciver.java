package com.erfankazemi.drtarmast.Alarm.Service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.erfankazemi.drtarmast.R;
import com.erfankazemi.drtarmast.Util.DB;

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

    String notificationState = DB.getStringData(context, "NOTIF");

    Toast.makeText(context, "" + notificationState, Toast.LENGTH_SHORT).show();

    Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.body_scale);

    switch (notificationState) {
      case "1":
        builder.setSmallIcon(R.drawable.body_scale)
          .setContentTitle("یادآوری")
          .setContentText("شما میتوانید با استفاده از Bmi مقدار اضافه وزن خود را محاسبه کنید  ورزش خود را شروع کنید")
          .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
          .setLargeIcon(largeIcon)
          .setAutoCancel(true);
        break;
      case "2":
        builder.setSmallIcon(R.drawable.body_scale)
          .setContentTitle("یادآوری تمرین ورزشی")
          .setContentText("یادتان نرود که تمرین ورزشی خود را امروز انجام بدهید !")
          .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
          .setLargeIcon(largeIcon)
          .setAutoCancel(true);
        break;
      case "3":
        builder.setSmallIcon(R.drawable.body_scale)
          .setContentTitle("یادآوری محاسبه اضافه وزن")
          .setContentText("شما میتوانید با استفاده از Bmi مقدار اضافه وزن خود را محاسبه کنید!")
          .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
          .setLargeIcon(largeIcon)
          .setAutoCancel(true);
        break;
      case "4":
        builder.setSmallIcon(R.drawable.body_scale)
          .setContentTitle("فراموش نکنید !")
          .setContentText("یادتان نرود که بدن خود را سالم نگه دارید !")
          .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
          .setLargeIcon(largeIcon)
          .setAutoCancel(true);
        break;
      default:
        builder.setSmallIcon(R.drawable.body_scale)
          .setContentTitle("سلامتی مهم تر از همه چیزه !")
          .setContentText("یادتان نرود که بدن خود را سالم نگه دارید !")
          .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
          .setLargeIcon(largeIcon)
          .setAutoCancel(true);
        ;
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

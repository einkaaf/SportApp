package com.erfankazemi.drtarmast.Alarm.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.erfankazemi.drtarmast.Alarm.Helper.NotificationHelper;

import androidx.core.app.NotificationCompat;

public class AlarmService extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    NotificationHelper notificationHelper = new NotificationHelper(context);
    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
    notificationHelper.getManager().notify(1, nb.build());
  }
}

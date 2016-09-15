package com.example.saikikwok.todolist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;

import com.example.saikikwok.todolist.Models.Todo;
import com.example.saikikwok.todolist.R;
import com.example.saikikwok.todolist.TaskActivity;

/**
 * Created by saikikwok on 9/12/16.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        final int notificationId = 100;

        Todo todo = intent.getParcelableExtra(TaskActivity.KEY_TASK_DETAILS);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_access_alarm_black_24dp)
                .setContentTitle(todo.getTask())
                .setContentText(todo.getTask())
                .setAutoCancel(true);

        Intent returnIntent = new Intent(context, TaskActivity.class);
        returnIntent.putExtra(TaskActivity.KEY_TASK_DETAILS, todo);

        PendingIntent returnPendingIntent = PendingIntent.getActivity(context, 0, returnIntent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(returnPendingIntent);

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(todo.getAlarmId(), builder.build());

        // TODO(Saiki) fix bugs on

    }



}

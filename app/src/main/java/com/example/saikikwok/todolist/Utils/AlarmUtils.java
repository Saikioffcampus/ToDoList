package com.example.saikikwok.todolist.Utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.saikikwok.todolist.AlarmReceiver;
import com.example.saikikwok.todolist.Models.Todo;
import com.example.saikikwok.todolist.TaskActivity;

import java.util.Calendar;

/**
 * Created by saikikwok on 9/13/16.
 */

public class AlarmUtils {

    public static void setAlarm(Context context, Todo todo) {
        Calendar c = Calendar.getInstance();
        if (todo.getInvokedDate() == null || todo.getInvokedDate().compareTo(c.getTime()) < 0) {
            cancelAlarm(context, todo);
            return;
        }
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(TaskActivity.KEY_TASK_DETAILS, todo);
        //intent.setData(Uri.parse("timer:" + id));
        //intent.setFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        PendingIntent sender = PendingIntent.getBroadcast(context, todo.getAlarmId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, todo.getInvokedDate().getTime(), sender);

    }

    public static void cancelAlarm(Context context, Todo todo) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(TaskActivity.KEY_TASK_DETAILS, todo);
        PendingIntent sender = PendingIntent.getBroadcast(context, todo.getAlarmId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(sender);
    }

}

package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.Receiver.AlarmReceiver;

/**
 * Created by Calvin on 5/29/2015.
 */
public class CurrentNextAlarm {
    private static AlarmItem currentNextAlarm;
    private static PendingIntent pendingIntent;

    public static AlarmItem getCurrentNextAlarm() {
        return currentNextAlarm;
    }

    public static void setCurrentNextAlarm(AlarmItem currentNextAlarms, Activity context) {
        if(currentNextAlarms == null)
            return;
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context,0,alarmIntent,0);
        //cancel previous broadcast if it exists
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if(currentNextAlarm != null && manager != null)
            manager.cancel(pendingIntent);
        currentNextAlarm = currentNextAlarms;
        //define the time the alarm is going to go off
        Calendar alarmTime = Calendar.getInstance();
        alarmTime.setTimeInMillis(System.currentTimeMillis());
        if((currentNextAlarms.getHour() < alarmTime.get(Calendar.HOUR_OF_DAY))|| ((currentNextAlarms.getHour() == alarmTime.get(Calendar.HOUR_OF_DAY)) && (currentNextAlarms.getMinute() < alarmTime.get(Calendar.MINUTE)))) {
            alarmTime.add(Calendar.DATE, 1);
        }
        alarmTime.set(Calendar.HOUR_OF_DAY, currentNextAlarm.getHour());
        alarmTime.set(Calendar.MINUTE, currentNextAlarm.getMinute());
        alarmTime.set(Calendar.SECOND, 0);

        manager.setExact(AlarmManager.RTC_WAKEUP, alarmTime.getTimeInMillis(), pendingIntent);
    }

}

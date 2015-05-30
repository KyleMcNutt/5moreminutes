package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.Receiver;

/**
 * Created by Kyle-PC on 5/25/2015.
 */
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.activities.AlarmNotification;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent alarmPage = new Intent(context, AlarmNotification.class);
        alarmPage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(alarmPage);
    }
}
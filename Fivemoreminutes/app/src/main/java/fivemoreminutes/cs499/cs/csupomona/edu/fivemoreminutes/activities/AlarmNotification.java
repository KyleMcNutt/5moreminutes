package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.R;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.Receiver.AlarmReceiver;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model.GetNextAlarmTask;

/**
 * Created by Calvin on 5/28/2015.
 */
public class AlarmNotification extends ActionBarActivity{

    MediaPlayer mediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_notification_layout);
        mediaPlayer = MediaPlayer.create(this, R.raw.oxygen);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        Button dismiss = (Button) findViewById(R.id.dismiss_button);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                AlarmManager manager = (AlarmManager) v.getContext().getSystemService(Context.ALARM_SERVICE);
                Intent alarmIntent = new Intent(v.getContext(), AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(v.getContext(), 0, alarmIntent, 0);
                manager.cancel(pendingIntent);
                Object[] parameters = { v.getContext() };
                new GetNextAlarmTask().execute(parameters);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        mediaPlayer.stop();
        AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        manager.cancel(pendingIntent);
        Object[] parameters = { this };
        new GetNextAlarmTask().execute(parameters);
    }

}

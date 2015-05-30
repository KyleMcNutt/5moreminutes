package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.R;

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
                finish();
            }
        });

    }

}

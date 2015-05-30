package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.adapters.GroupItemAdapter;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.AlarmItem;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.CurrentNextAlarm;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.GroupItem;

/**
 * Created by Calvin on 5/29/2015.
 */
public class GetNextAlarmTask extends AsyncTask {
    private DBHandler dbHandler;

    @Override
    protected AlarmItem doInBackground(Object[] objects) {
        dbHandler = new DBHandler((Activity)objects[0], null, null, 1);
        AlarmItem fromDB = dbHandler.getNextAlarm();
        CurrentNextAlarm.setCurrentNextAlarm(fromDB, (Activity)objects[0]);
        return fromDB;
    }
}

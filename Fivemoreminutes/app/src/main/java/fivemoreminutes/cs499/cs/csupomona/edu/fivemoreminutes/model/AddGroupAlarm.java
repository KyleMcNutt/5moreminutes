package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.adapters.AlarmItemAdapter;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.AlarmItem;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.GroupItem;

/**
 * Created by Kyle-PC on 5/17/2015.
 */
public class AddGroupAlarm extends AsyncTask {
    private DBHandler dbHandler;
    @Override
    protected Object doInBackground(Object[] objects) {
        dbHandler = new DBHandler((Activity)objects[0], null, null, 1);
        // constructor follows format of hour, minute, groupKey
        AlarmItem alarm = new AlarmItem((int)objects[1], (int)objects[2], (int)objects[3]);
        dbHandler.addGroupAlarm(alarm);
        return alarm;
    }
}

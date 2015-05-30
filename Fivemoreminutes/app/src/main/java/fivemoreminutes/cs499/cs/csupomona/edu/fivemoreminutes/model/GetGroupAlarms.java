package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.adapters.AlarmItemAdapter;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.adapters.GroupItemAdapter;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.AlarmItem;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.GroupItem;

/**
 * Created by Kyle-PC on 5/17/2015.
 */
public class GetGroupAlarms extends AsyncTask {
    private DBHandler dbHandler;
    private ArrayList<AlarmItem> alarmItems;
    private AlarmItemAdapter listAdapter;

    @Override
    protected Object doInBackground(Object[] objects) {
        //expected params are activity, alarmItems list, listAdapter, groupKey
        dbHandler = new DBHandler((Activity)objects[0], null, null, 1);
        alarmItems = (ArrayList<AlarmItem>) objects[1];
        listAdapter = (AlarmItemAdapter) objects[2];
        ArrayList<AlarmItem> fromDB = dbHandler.getGroupAlarms((int)objects[3]);
        this.alarmItems.addAll(fromDB);
        ((Activity)objects[0]).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listAdapter.notifyDataSetChanged();
            }
        });
        return alarmItems;
    }
}

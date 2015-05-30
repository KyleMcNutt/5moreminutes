package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.adapters.GroupItemAdapter;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.GroupItem;

/**
 * Created by Kyle-PC on 5/3/2015.
 */
public class GetGroupsTask extends AsyncTask {
    private DBHandler dbHandler;
    private ArrayList<GroupItem> groupItems;
    private GroupItemAdapter listAdapter;

    @Override
    protected ArrayList<GroupItem> doInBackground(Object[] objects) {
        dbHandler = new DBHandler((Activity)objects[0], null, null, 1);
        groupItems = (ArrayList<GroupItem>) objects[1];
        listAdapter = (GroupItemAdapter) objects[2];
        ArrayList<GroupItem> fromDB = dbHandler.getGroups();
        this.groupItems.addAll(fromDB);
        ((Activity)objects[0]).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listAdapter.notifyDataSetChanged();
            }
        });
        return groupItems;
    }
}

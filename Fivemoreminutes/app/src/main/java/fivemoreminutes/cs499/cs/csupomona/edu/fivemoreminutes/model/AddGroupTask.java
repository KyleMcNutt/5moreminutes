package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.adapters.GroupItemAdapter;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.GroupItem;

/**
 * Created by Kyle-PC on 5/3/2015.
 */
public class AddGroupTask extends AsyncTask {
    private DBHandler dbHandler;
    private ArrayList<GroupItem> groupItems;
    private GroupItemAdapter listAdapter;

    @Override
    protected GroupItem doInBackground(Object[] objects) {
        dbHandler = new DBHandler((Activity)objects[0], null, null, 1);
        groupItems = (ArrayList<GroupItem>) objects[1];
        GroupItem group = new GroupItem((String)objects[2], (boolean)objects[3]);
        dbHandler.addGroup(group);
        return group;
    }
}

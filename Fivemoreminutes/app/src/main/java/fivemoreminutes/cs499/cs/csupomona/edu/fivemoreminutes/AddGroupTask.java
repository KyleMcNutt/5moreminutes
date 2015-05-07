package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by Kyle-PC on 5/3/2015.
 */
public class AddGroupTask extends AsyncTask {
    private DBHandler dbHandler;
    private ArrayList<GroupItem> groupItems;
    private GroupItemAdapter listAdapter;

    @Override
    protected GroupModel doInBackground(Object[] objects) {
        dbHandler = new DBHandler((Activity)objects[0], null, null, 1);
        groupItems = (ArrayList<GroupItem>) objects[1];
        listAdapter = (GroupItemAdapter) objects[2];
        GroupModel group = new GroupModel((String)objects[3]);
        dbHandler.addGroup(group);
        return group;
    }
}

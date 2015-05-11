package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.adapters.GroupItemAdapter;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.GroupItem;

/**
 * Created by Kyle-PC on 5/3/2015.
 */
public class SetGroupStateTask extends AsyncTask {
    private DBHandler dbHandler;
    private int groupID;

    @Override
    protected GroupItem doInBackground(Object[] objects) {
        dbHandler = new DBHandler((Activity)objects[0], null, null, 1);
        groupID = (int)objects[1];
        boolean stateToChangeTo = (boolean)objects[2];
        if(stateToChangeTo) {
            dbHandler.setGroupToOn(groupID);
        } else {
            dbHandler.setGroupToOff(groupID);
        }
        // Don't ever think that this GroupItem does anything, it doesn't
        return new GroupItem();
    }
}

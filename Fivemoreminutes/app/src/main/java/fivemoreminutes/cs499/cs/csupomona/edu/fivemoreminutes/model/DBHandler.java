package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.AlarmItem;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.GroupItem;

/**
 * Created by Kyle-PC on 5/3/2015.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "5moreminutes.db";
    private static final String TABLE_GROUPS = "groups";
    private static final String TABLE_GROUPS_ALARMS = "group_alarms";

    public static final String COLUMN_GROUPID = "_id";
    public static final String COLUMN_GROUPNAME = "groupName";
    public static final String COLUMN_CURRENTLYON = "currentlyOn";

    public static final String COLUMN_GROUP_ALARMID = "_id";
    public static final String COLUMN_GROUP_ALARMKEY = "groupKey";
    public static final String COLUMN_HOURS = "hours";
    public static final String COLUMN_MINUTES = "minutes";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GROUPS_TABLE = "CREATE TABLE "+
                TABLE_GROUPS + "("
                + COLUMN_GROUPID + " INTEGER PRIMARY KEY, " +
                COLUMN_GROUPNAME + " TEXT, " +
                COLUMN_CURRENTLYON + " BOOLEAN"
                + ")";
        String CREATE_ALARM_GROUPS_TABLE = "CREATE TABLE "+
                TABLE_GROUPS_ALARMS + "("
                + COLUMN_GROUP_ALARMID + " INTEGER PRIMARY KEY, " +
                COLUMN_GROUP_ALARMKEY + " INTEGER, " +
                COLUMN_HOURS + " INTEGER, " +
                COLUMN_MINUTES + " INTEGER, " +
                "FOREIGN KEY("+COLUMN_GROUP_ALARMKEY+") REFERENCES "+TABLE_GROUPS+"("+COLUMN_GROUPID+")" +
                ")";
        try {
            db.execSQL(CREATE_GROUPS_TABLE);
            db.execSQL(CREATE_ALARM_GROUPS_TABLE);
        } catch (Exception e) {
            Log.e("SQLException", e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS_ALARMS);
        onCreate(db);
    }

    public void addGroupAlarm(AlarmItem alarm) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_GROUP_ALARMKEY, alarm.getGroupKey());
        values.put(COLUMN_HOURS, alarm.getHour());
        values.put(COLUMN_MINUTES, alarm.getMinute());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_GROUPS_ALARMS, null, values);
        db.close();
    }

    public AlarmItem getNextAlarm() {
        String query = "SELECT * FROM " + TABLE_GROUPS_ALARMS + " ORDER BY " + COLUMN_HOURS + ", " + COLUMN_MINUTES + " ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        AlarmItem alarm = null;
        int counter = 0;
        Calendar currentTime = Calendar.getInstance();
        //currentTime.add(Calendar.MINUTE, 1);
        if(cursor.moveToFirst()) {
            do {
                AlarmItem temp = new AlarmItem();
                temp.set_id(Integer.parseInt(cursor.getString(0)));
                temp.setGroupKey(Integer.parseInt(cursor.getString(1)));
                temp.setHour(Integer.parseInt(cursor.getString(2)));
                temp.setMinute(Integer.parseInt(cursor.getString(3)));
                if(getGroupById(temp.getGroupKey()).getCurrentlyOn() == false) {
                    continue;
                }
                if(counter == 0) {
                    alarm = temp;
                    counter++;
                } else {
                    if(currentTime.get(currentTime.HOUR_OF_DAY) > temp.getHour()) {
                        continue;
                    } else if(currentTime.get(currentTime.HOUR_OF_DAY) == temp.getHour() && currentTime.get(currentTime.MINUTE) > temp.getMinute()) {
                        continue;
                    }
                    int hoursDiffBetweenNowAndTemp = Math.abs(currentTime.get(currentTime.HOUR_OF_DAY) - temp.getHour());
                    int minutesDiffBetweenNowAndTemp = Math.abs(currentTime.get(currentTime.MINUTE) - temp.getMinute());
                    int hoursDiffBetweenNowAndCurrent = Math.abs(currentTime.get(currentTime.HOUR_OF_DAY) - alarm.getHour());
                    int minutesDiffBetweenNowAndCurrent = Math.abs(currentTime.get(currentTime.MINUTE) - alarm.getMinute());
                    if(hoursDiffBetweenNowAndTemp < hoursDiffBetweenNowAndCurrent) {
                        alarm = temp;
                    } else if((hoursDiffBetweenNowAndTemp == hoursDiffBetweenNowAndCurrent) && (minutesDiffBetweenNowAndTemp < minutesDiffBetweenNowAndCurrent)) {
                        alarm = temp;
                    }
                }
            } while(cursor.moveToNext());
        }
        return alarm;
    }

    public ArrayList<AlarmItem> getGroupAlarms(int groupKey) {
        String query = "SELECT * FROM " + TABLE_GROUPS_ALARMS + " WHERE " + COLUMN_GROUP_ALARMKEY + " = \"" + groupKey+"\" ORDER BY " + COLUMN_HOURS + ", " +COLUMN_MINUTES + " ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<AlarmItem> groupAlarms = new ArrayList<AlarmItem>();

        if(cursor.moveToFirst()) {
            do {
                AlarmItem alarm = new AlarmItem();
                alarm.set_id(Integer.parseInt(cursor.getString(0)));
                alarm.setGroupKey(Integer.parseInt(cursor.getString(1)));
                alarm.setHour(Integer.parseInt(cursor.getString(2)));
                alarm.setMinute(Integer.parseInt(cursor.getString(3)));
                groupAlarms.add(alarm);
            } while(cursor.moveToNext());
        }

        return groupAlarms;
    }

    public void addGroup(GroupItem group) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_GROUPNAME, group.getName());
        int boolVal = (group.getCurrentlyOn())? 1 : 0;
        values.put(COLUMN_CURRENTLYON, boolVal);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_GROUPS, null, values);
        db.close();
    }

    public GroupItem getGroupById(int groupKey) {
        String query = "SELECT * FROM " + TABLE_GROUPS + " WHERE " + COLUMN_GROUPID + " = \"" + groupKey + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        GroupItem group = new GroupItem();

        if (cursor.moveToFirst()) {
            group.set_id(Integer.parseInt(cursor.getString(0)));
            group.setName(cursor.getString(1));
            boolean bool = (cursor.getInt(2) == 1)? true : false;
            group.setCurrentlyOn(bool);
        }
        return group;
    }

    public ArrayList<GroupItem> getGroups() {
        String query = "SELECT * FROM " + TABLE_GROUPS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        ArrayList<GroupItem> allGroups = new ArrayList<GroupItem>();

        if(cursor.moveToFirst()) {
            do {
                GroupItem group = new GroupItem();
                group.set_id(Integer.parseInt(cursor.getString(0)));
                group.setName(cursor.getString(1));
                boolean bool = (cursor.getInt(2) == 1)? true : false;
                group.setCurrentlyOn(bool);
                allGroups.add(group);
            } while(cursor.moveToNext());
        }
        return allGroups;
    }

    /* Untested method */
    public GroupItem findGroup(String groupName) {
        String query = "Select * FROM " + TABLE_GROUPS + " WHERE " + COLUMN_GROUPNAME + " = \"" + groupName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        GroupItem group = new GroupItem();

        if(cursor.moveToFirst()) {
            cursor.moveToFirst();
            group.set_id(Integer.parseInt(cursor.getString(0)));
            group.setName(cursor.getString(1));
            boolean bool = (cursor.getInt(2) == 1)? true : false;
            group.setCurrentlyOn(bool);
            cursor.close();
        } else  {
            group = null;
        }
        db.close();
        return group;
    }

    public int setGroupToOff(int groupID) {
        int rowsUpdated = 0;
        ContentValues values = new ContentValues();
        values.put(COLUMN_CURRENTLYON, 0);
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            rowsUpdated = db.update(TABLE_GROUPS, values, COLUMN_GROUPID + " = " + groupID, null);
        } catch (Exception e) {
            Log.e("SQLException", e.toString());
        }
        return rowsUpdated;
    }

    public int setGroupToOn(int groupID) {
        int rowsUpdated = 0;
        ContentValues values = new ContentValues();
        values.put(COLUMN_CURRENTLYON, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            rowsUpdated = db.update(TABLE_GROUPS, values, COLUMN_GROUPID + " = " + groupID, null);
        } catch (Exception e) {
            Log.e("SQLException", e.toString());
        }
        return rowsUpdated;
    }

    /* Untested method */
    public boolean deleteGroup(String groupName) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_GROUPS + " WHERE " + COLUMN_GROUPNAME + " = \"" + groupName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        GroupItem group = new GroupItem();

        if(cursor.moveToFirst()) {
            group.set_id(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_GROUPS, COLUMN_GROUPID + " = ?",
                    new String[]{String.valueOf(group.get_id())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}

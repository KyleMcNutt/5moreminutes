package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.GroupItem;

/**
 * Created by Kyle-PC on 5/3/2015.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "5moreminutes.db";
    private static final String TABLE_GROUPS = "groups";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_GROUPNAME = "groupName";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GROUPS_TABLE = "CREATE TABLE "+
                TABLE_GROUPS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_GROUPNAME
                + " TEXT" + ")";
        try {
            db.execSQL(CREATE_GROUPS_TABLE);
        } catch (Exception e) {
            Log.e("SQLException", e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
        onCreate(db);
    }

    public void addGroup(GroupItem group) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_GROUPNAME, group.getName());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_GROUPS, null, values);
        db.close();
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
                allGroups.add(group);
            } while(cursor.moveToNext());
        }
        return allGroups;
    }

    public GroupItem findGroup(String groupName) {
        String query = "Select * FROM " + TABLE_GROUPS + " WHERE " + COLUMN_GROUPNAME + " = \"" + groupName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        GroupItem group = new GroupItem();

        if(cursor.moveToFirst()) {
            cursor.moveToFirst();
            group.set_id(Integer.parseInt(cursor.getString(0)));
            group.setName(cursor.getString(1));
            cursor.close();
        } else  {
            group = null;
        }
        db.close();
        return group;
    }

    public boolean deleteGroup(String groupName) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_GROUPS + " WHERE " + COLUMN_GROUPNAME + " = \"" + groupName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        GroupItem group = new GroupItem();

        if(cursor.moveToFirst()) {
            group.set_id(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_GROUPS, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(group.get_id())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}

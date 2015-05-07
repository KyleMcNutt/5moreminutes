package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GROUPS_TABLE = "CREATE TABLE "+
                TABLE_GROUPS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_GROUPNAME
                + " TEXT" + ")";
        db.execSQL(CREATE_GROUPS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
        onCreate(db);
    }

    public void addGroup(GroupModel group) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_GROUPNAME, group.get_groupName());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_GROUPS, null, values);
        db.close();
    }

    public ArrayList<GroupModel> getGroups() {
        String query = "SELECT * FROM " + TABLE_GROUPS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        ArrayList<GroupModel> allGroups = new ArrayList<GroupModel>();

        if(cursor.moveToFirst()) {
            do {
                GroupModel group = new GroupModel();
                group.set_id(Integer.parseInt(cursor.getString(0)));
                group.set_groupName(cursor.getString(1));
                allGroups.add(group);
            } while(cursor.moveToNext());
        }
        return allGroups;
    }

    public GroupModel findGroup(String groupName) {
        String query = "Select * FROM " + TABLE_GROUPS + " WHERE " + COLUMN_GROUPNAME + " = \"" + groupName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        GroupModel group = new GroupModel();

        if(cursor.moveToFirst()) {
            cursor.moveToFirst();
            group.set_id(Integer.parseInt(cursor.getString(0)));
            group.set_groupName(cursor.getString(1));
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

        GroupModel group = new GroupModel();

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

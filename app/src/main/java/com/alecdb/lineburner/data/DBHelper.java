package com.alecdb.lineburner.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.alecdb.lineburner.data.DBContract.SceneEntries;
import com.alecdb.lineburner.data.DBContract.ScriptEntries;

/**
 * Created by ALec on 1/24/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "masterDatabase.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_SCENESTABLE = "CREATE TABLE " + SceneEntries.TABLE_NAME + " (" + SceneEntries.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY "
                //FINISH
                + " );";

        final String SQL_CREATE_SCRIPTSTABLE = "CREATE TABLE " + ScriptEntries.TABLE_NAME + " (" + ScriptEntries.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ScriptEntries.COLUMN_NAME_TITLE + " TEXT NOT NULL, " + ScriptEntries.COLUMN_NAME_SUBTITLE + " TEXT, " + ScriptEntries.COLUMN_NAME_SCENE_KEY + " INTEGER NOT NULL "

                //Set up foreign key
                + " FOREIGN KEY(" + ScriptEntries.COLUMN_NAME_SCENE_KEY + ") REFERENCES " +
                SceneEntries.TABLE_NAME + " (" + SceneEntries.COLUMN_NAME_ENTRY_ID + ")"
                + " );";

        sqLiteDatabase.execSQL(SQL_CREATE_SCENESTABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_SCRIPTSTABLE);


        // Move somewhere else, or at least make only run the first time
        ContentValues values = new ContentValues();

        values.put(DBContract.ScriptEntries.COLUMN_NAME_TITLE, "Default Script");
        values.put(DBContract.ScriptEntries.COLUMN_NAME_SUBTITLE, "Default Script Description");
        values.put(DBContract.ScriptEntries.COLUMN_NAME_SCENE_KEY, "1");
        sqLiteDatabase.insert(DBContract.ScriptEntries.TABLE_NAME, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        //You need to fix this
        Log.e("Database", "Tried to upgrade. You haven't figured out how to update it yet, remember?");
    }


}

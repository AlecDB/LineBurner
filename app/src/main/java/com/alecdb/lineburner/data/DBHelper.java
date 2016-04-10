package com.alecdb.lineburner.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.alecdb.lineburner.data.DBContract.SceneEntries;
import com.alecdb.lineburner.data.DBContract.ScriptEntries;

/**
 * A helper object that defines the SQLite commands needed to create the database.
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

        final String SQL_CREATE_SCENESTABLE = "CREATE TABLE " + SceneEntries.TABLE_NAME + " (" + SceneEntries.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " + SceneEntries.COLUMN_NAME_TITLE + " TEXT NOT NULL "

                + " );";

        final String SQL_CREATE_SCRIPTSTABLE = "CREATE TABLE " + ScriptEntries.TABLE_NAME + " (" + ScriptEntries.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ScriptEntries.COLUMN_NAME_TITLE + " TEXT NOT NULL, " + ScriptEntries.COLUMN_NAME_SUBTITLE + " TEXT, " + ScriptEntries.COLUMN_NAME_SCENE_KEY + " INTEGER NOT NULL, "

                //Set up foreign key
                + " FOREIGN KEY(" + ScriptEntries.COLUMN_NAME_SCENE_KEY + ") REFERENCES " +
                SceneEntries.TABLE_NAME + " (" + SceneEntries.COLUMN_NAME_ENTRY_ID + ")" + " );";

        sqLiteDatabase.execSQL(SQL_CREATE_SCENESTABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_SCRIPTSTABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        //You need to fix this
        Log.e("Database", "Tried to upgrade. You haven't figured out how to update it yet, remember?");
    }


}

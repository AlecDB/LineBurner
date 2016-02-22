package com.alecdb.lineburner.data;

import android.provider.BaseColumns;

/**
 * The contract/scheme for SQLite Database 1
 * Created by ALec on 1/24/2016.
 */
public final class DBContract {

    public DBContract(){}

    public static abstract class ScriptEntries implements BaseColumns{
        public static final String TABLE_NAME = "ScriptEntries";
        public static final String COLUMN_NAME_ENTRY_ID = "entry_id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
        public static final String COLUMN_NAME_SCENE_KEY = "scene_key";
    }

    public static abstract class SceneEntries implements BaseColumns{

        public static final String TABLE_NAME = "SceneEntries";

    }
}

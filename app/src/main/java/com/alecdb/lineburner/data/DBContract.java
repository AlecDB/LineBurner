package com.alecdb.lineburner.data;

import android.provider.BaseColumns;

/**
 * The contract/scheme for the SQLite Database. Contains a table for Scenes and a table for Scripts
 * that should be linked to it by a primary key.
 * Created by ALec on 1/24/2016.
 */
public final class DBContract {

    public DBContract() {
    }

    public static abstract class ScriptEntries implements BaseColumns {
        public static final String TABLE_NAME = "ScriptEntries";
        public static final String COLUMN_NAME_ENTRY_ID = "entry_id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
        public static final String COLUMN_NAME_SCENE_KEY = "scene_key"; // Should link to the entry_id in SceneEntries
    }

    // A sub table of sorts, linking up to ScriptEntries
    public static abstract class SceneEntries implements BaseColumns {

        public static final String TABLE_NAME = "SceneEntries";
        public static final String COLUMN_NAME_ENTRY_ID = "entry_id";

    }
}

package com.alecdb.lineburner.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Application-wide content provider
 * Created by ALec on 2/17/2016.
 */
public class LineBurnerProvider extends ContentProvider {

    final static String authority = "com.lineburner.app";
    final static String tableName = "ScriptEntries";
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final Uri CONTENT_URI = Uri.parse("content://" + authority + "/" + tableName);

    static {
        uriMatcher.addURI(authority, tableName, 1);
    }

    SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        db = new DBHelper(getContext()).getWritableDatabase();
        DBHelper dbHelper = new DBHelper(getContext());
        return false;
    }


    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        return db.query(tableName, projection, selection, selectionArgs, null, sortOrder, null);

        // switch (uriMatcher.match(uri)){}
    }

    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    public int delete(Uri uri, String string, String[] strings) {
        return 0;
    }

    public int update(Uri uri, ContentValues values, String idk, String[] butTheyWantedIt) {
        return 0;
    }

    public String getType(Uri uri) {
        return "vnd.android.cursor.dir/vnd.com.lineburner.contentprovider.scripts";
    }
}


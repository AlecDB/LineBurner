package com.alecdb.lineburner;


import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.alecdb.lineburner.data.DBContract;
import com.alecdb.lineburner.data.DBHelper;
import com.alecdb.lineburner.data.LineBurnerProvider;
/**
 * A placeholder fragment containing a simple view.
 */
public class ScriptsFragment extends Fragment /* implements LoaderManager.LoaderCallbacks<Cursor> */ {

    private static final int DB_LOADER = 0; // start this loader thing

    private static final String[] DB_COLUMNS = {DBContract.ScriptEntries.TABLE_NAME + "." + DBContract.ScriptEntries._ID, DBContract.ScriptEntries.COLUMN_NAME_TITLE, DBContract.ScriptEntries.COLUMN_NAME_SUBTITLE, DBContract.ScriptEntries.COLUMN_NAME_SCENE_KEY};

//    private ScriptListAdapter scriptAdapter;

    public ScriptsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        DBHelper dbHelper = new DBHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //Is the first time we've run this?
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());


        //   if (!prefs.getBoolean("firstTime",false)){

        //Insert defaults scripts


        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstTime", true);
        editor.apply();
        //    }


        View rootView = inflater.inflate(com.alecdb.lineburner.R.layout.fragment_scripts, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listView1);
        //  scriptAdapter = new ScriptListAdapter(getActivity(), null, 0);
        //  listView.setAdapter(scriptAdapter);

        TextView scriptView = (TextView) rootView.findViewById(R.id.ScriptTitle);
        listView.addView(scriptView);


        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //    getLoaderManager().initLoader(DB_LOADER, null, this);
        //  super.onActivityCreated(savedInstanceState);
    }

    //   @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        return new CursorLoader(getActivity(), LineBurnerProvider.CONTENT_URI, DB_COLUMNS, null, null, null);
    }

    //    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        //  scriptAdapter.swapCursor(cursor);
    }

    //   @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        //    scriptAdapter.swapCursor(null);
    }
}

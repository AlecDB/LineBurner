package com.alecdb.lineburner;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alecdb.lineburner.data.DBContract;
import com.alecdb.lineburner.data.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * The first and (so far) only fragment to run. Should display a simple list of the titles and subtitles of the Scripts entered in
 * the Scripts table.
 */
public class ScriptsFragment extends Fragment/* implements LoaderManager.LoaderCallbacks<Cursor> */ {


    public List<String> results = new ArrayList<>();
    public SQLiteDatabase db;

    public ScriptsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.fragment_scripts, container, false);

        if (!(getFragmentManager().getFragments().isEmpty())) {
            openAndQueryDatabase();
            displayResultList(rootView);
        }

        ListView listView = (ListView) rootView.findViewById(R.id.listView1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.replace(R.id.fragment_container, new ScenesFragment());
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        final FloatingActionButton fab = (FloatingActionButton) this.getActivity().findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addScript();
                openAndQueryDatabase();
                displayResultList(rootView);
            }
        });
        return rootView;
    }

    private void openAndQueryDatabase() {
        try {
            DBHelper dbHelper = new DBHelper(this.getContext());
            db = dbHelper.getWritableDatabase();

            Cursor c = db.rawQuery("SELECT " + DBContract.ScriptEntries.COLUMN_NAME_TITLE + "," + DBContract.ScriptEntries.COLUMN_NAME_SUBTITLE + " FROM " + DBContract.ScriptEntries.TABLE_NAME, null);

            if (c.getCount() <= 0) {
                ContentValues values = new ContentValues();
                values.put(DBContract.ScriptEntries.COLUMN_NAME_TITLE, "Default Script 1");
                values.put(DBContract.ScriptEntries.COLUMN_NAME_SUBTITLE, "Default Script Description 1");
                values.put(DBContract.ScriptEntries.COLUMN_NAME_SCENE_KEY, "1");
                db.insert(DBContract.ScriptEntries.TABLE_NAME, null, values);

                values.put(DBContract.ScriptEntries.COLUMN_NAME_TITLE, "Default Script 2");
                values.put(DBContract.ScriptEntries.COLUMN_NAME_SUBTITLE, "Default Script Description 2");
                values.put(DBContract.ScriptEntries.COLUMN_NAME_SCENE_KEY, "2");
                db.insert(DBContract.ScriptEntries.TABLE_NAME, null, values);
                c = db.rawQuery("SELECT " + DBContract.ScriptEntries.COLUMN_NAME_TITLE + "," + DBContract.ScriptEntries.COLUMN_NAME_SUBTITLE + " FROM " + DBContract.ScriptEntries.TABLE_NAME, null);
            }
            results.clear();
            if (c.moveToFirst()) {
                do {
                    String title = c.getString(c.getColumnIndex(DBContract.ScriptEntries.COLUMN_NAME_TITLE));
                    String subTitle = c.getString(c.getColumnIndex(DBContract.ScriptEntries.COLUMN_NAME_SUBTITLE));
                    results.add(title);
                } while (c.moveToNext());
            }

        } catch (SQLiteException se) {
            Log.e(getClass().getSimpleName(), "Could not create or open the database");
        } finally {
            if (db != null) {
                //  db.close();
            }
        }
    }

    private void displayResultList(View rootView) {


        ListView listView = (ListView) rootView.findViewById(R.id.listView1);
        if (listView.getHeaderViewsCount() == 0) {
            TextView tView = new TextView(this.getContext());
            tView.setText("This data is retrieved from the database and only 4 " + "of the results are displayed");
            listView.addHeaderView(tView);
        }


        listView.setAdapter(new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, results));
        listView.setTextFilterEnabled(true);
    }

    public void addScript() {

        ContentValues values = new ContentValues();
        values.put(DBContract.ScriptEntries.COLUMN_NAME_TITLE, "Added script!");
        values.put(DBContract.ScriptEntries.COLUMN_NAME_SUBTITLE, "Added script description!!!!!!!!!");
        values.put(DBContract.ScriptEntries.COLUMN_NAME_SCENE_KEY, "3");
        db.insert(DBContract.ScriptEntries.TABLE_NAME, null, values);

    }
}

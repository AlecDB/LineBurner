package com.alecdb.lineburner;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alecdb.lineburner.data.DBContract;
import com.alecdb.lineburner.data.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays a list of scenes for the script selected in ScriptsFragment
 * Created by ALec on 4/2/2016.
 */
public class ScenesFragment extends Fragment {
    public List<String> results = new ArrayList<>();
    public SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragement_scenes, container, false);


        openAndQueryDatabase();
        displayResultList(rootView);

        ListView listView = (ListView) rootView.findViewById(R.id.listView2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        final FloatingActionButton fab = (FloatingActionButton) this.getActivity().findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addScene();
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
            Cursor c = db.rawQuery("SELECT " + DBContract.SceneEntries.COLUMN_NAME_TITLE + " FROM " + DBContract.SceneEntries.TABLE_NAME, null);
            Log.d("DEBUG", "openAndQueryDatabase: " + c.getCount());
            if (c.getCount() <= 0) {
                ContentValues values = new ContentValues();

                values.put(DBContract.SceneEntries.COLUMN_NAME_TITLE, "Default SCENE 1");   // FIXME: First entry doesn't display
                //          values.put(DBContract.SceneEntries.COLUMN_NAME_ENTRY_ID, "1");
                db.insert(DBContract.SceneEntries.TABLE_NAME, null, values);

                values.put(DBContract.SceneEntries.COLUMN_NAME_TITLE, "Default SCENE 2");
                db.insert(DBContract.SceneEntries.TABLE_NAME, null, values);

                values.put(DBContract.SceneEntries.COLUMN_NAME_TITLE, "Default SCENE 3");
                db.insert(DBContract.SceneEntries.TABLE_NAME, null, values);

                values.put(DBContract.SceneEntries.COLUMN_NAME_TITLE, "Default SCENE 4");
                db.insert(DBContract.SceneEntries.TABLE_NAME, null, values);

                values.put(DBContract.SceneEntries.COLUMN_NAME_TITLE, "Default SCENE 5");
                db.insert(DBContract.SceneEntries.TABLE_NAME, null, values);

                c = db.rawQuery("SELECT " + DBContract.SceneEntries.COLUMN_NAME_TITLE + " FROM " + DBContract.SceneEntries.TABLE_NAME, null);
            }
            results.clear();
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        String title = c.getString(c.getColumnIndex(DBContract.SceneEntries.COLUMN_NAME_TITLE));
                        results.add(title);
                    } while (c.moveToNext());
                }
            }
        } catch (SQLiteException se) {
            Log.e(getClass().getSimpleName(), "Could not create or open the database (SceneEntries table)");
        } finally {
            if (db != null) {
                //     db.close();
            }
        }
    }

    private void displayResultList(View rootView) {

        ListView listView = (ListView) rootView.findViewById(R.id.listView2);

        listView.setAdapter(new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, results));
        listView.setTextFilterEnabled(true);
    }

    public void addScene() {

        ContentValues values = new ContentValues();
        values.put(DBContract.SceneEntries.COLUMN_NAME_TITLE, "Added scene!");
        db.insert(DBContract.SceneEntries.TABLE_NAME, null, values);
    }


}
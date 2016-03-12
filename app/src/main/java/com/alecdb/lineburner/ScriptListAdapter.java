package com.alecdb.lineburner;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * An adapter for the script list, should allow entries in Script Entries table to be displayed
 * in ScriptsFragment by the loader in that code as a list.
 * Created by ALec on 2/12/2016.
 */
public class ScriptListAdapter extends CursorAdapter {

    public ScriptListAdapter(Context contest, Cursor c, int flags) {
        super(contest, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        int layoutId = R.layout.script_item;
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String title = cursor.getString(2);     // FIXME: THESE HARD CODED INDEXES!
        viewHolder.title.setText(title);
        String subTitle = cursor.getString(3);
        viewHolder.subTitle.setText(subTitle);
    }

    public static class ViewHolder {
        public final TextView title;
        public final TextView subTitle;

        public ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.ScriptTitle);
            subTitle = (TextView) view.findViewById(R.id.ScriptSubtitle);
        }
    }
}

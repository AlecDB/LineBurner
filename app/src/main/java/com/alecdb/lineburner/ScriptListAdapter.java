package com.alecdb.lineburner;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * A custom array adapter for the script list
 * Created by ALec on 2/12/2016.
 */
public class ScriptListAdapter extends CursorAdapter {

    public static class  ViewHolder {
        public final TextView title;
        public final TextView subTitle;

        public ViewHolder(View view){
            title = (TextView) view.findViewById(R.id.ScriptTitle);
            subTitle = (TextView) view.findViewById(R.id.ScriptSubtitle);
        }
    }

    public ScriptListAdapter(Context contest, Cursor c, int flags){
        super(contest, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){

        int layoutId = R.layout.script_item;
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
        }

    @Override
    public void bindView(View view, Context context, Cursor cursor){

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String title = cursor.getString(2);                         // FIX THESE HARD CODED INDEXES!
        viewHolder.title.setText(title);
        String subTitle = cursor.getString(3);
        viewHolder.subTitle.setText(subTitle);
    }
}

package com.example.programminghub;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class HubCursorAdapter extends CursorAdapter {
    public HubCursorAdapter(Context context, Cursor cursor){super(context,cursor,0);}

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        return LayoutInflater.from(context).inflate(R.layout.adapter_layout,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title=(TextView)view.findViewById(R.id.title);
        TextView body=(TextView)view.findViewById(R.id.body);

        String code_title=cursor.getString(cursor.getColumnIndex("Title"));
        String code_body=cursor.getString(cursor.getColumnIndex("Code"));

        title.setText(code_title);
        body.setText(code_body);

    }
}

package com.example.programminghub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class HubCursorAdapter extends CursorAdapter {
    public HubCursorAdapter(Context context, Cursor cursor){super(context,cursor,0);}

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        return LayoutInflater.from(context).inflate(R.layout.adapter_layout,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title=(TextView)view.findViewById(R.id.title);
        TextView body=(TextView)view.findViewById(R.id.des);
        ImageView img = (ImageView)view.findViewById(R.id.view);


        String code_title=cursor.getString(cursor.getColumnIndex("Title"));
        String code_body=cursor.getString(cursor.getColumnIndex("Code"));
        String des=cursor.getString(cursor.getColumnIndex("des"));
        byte[] en_img=cursor.getBlob(cursor.getColumnIndex("img"));



        Bitmap bitmap= BitmapFactory.decodeByteArray(en_img,0,en_img.length);
/*
        if(lan==1){
            img.setImageResource(R.mipmap.ic_cpp);
        }
        else if(lan==2){
            img.setImageResource(R.mipmap.ic_c);
        }else {
            img.setImageResource(R.mipmap.ic_java);
        }*/
        title.setText(code_title);
        body.setText(code_body);
        img.setImageBitmap(bitmap);

        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),ProgramOutput.class);
                intent.putExtra("title",code_title);
                intent.putExtra("img",en_img);
                intent.putExtra("code",code_body);


                view.getContext().startActivity(intent);

            }
        });
    }

}

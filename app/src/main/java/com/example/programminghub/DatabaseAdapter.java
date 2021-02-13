package com.example.programminghub;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseAdapter extends ArrayAdapter<CursorData> {
        public DatabaseAdapter(Activity context, ArrayList<CursorData> data){
            super(context,0,data);
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view= convertView;
            if(view==null){
                view= LayoutInflater.from(getContext()).inflate(R.layout.adapter_layout,parent,false);
            }
            CursorData curWord=getItem(position);

            TextView title=(TextView)view.findViewById(R.id.title);
            TextView body=(TextView)view.findViewById(R.id.des);
            ImageView img = (ImageView)view.findViewById(R.id.view);

            String[] projection={DBSchema.c._ID,DBSchema.c._title,DBSchema.c._img,DBSchema.c._des,DBSchema.c._body};
            Cursor cursor=getContext().getContentResolver().query(DBSchema.C_Content_Uri,projection,null,null,null);



            String code_title=cursor.getString(cursor.getColumnIndex("Title"));
            String code_body=cursor.getString(cursor.getColumnIndex("Code"));
            String des=cursor.getString(cursor.getColumnIndex("des"));
            byte[] en_img=cursor.getBlob(cursor.getColumnIndex("img"));



            Bitmap bitmap= BitmapFactory.decodeByteArray(en_img,0,en_img.length);
            title.setText(code_title);
            body.setText(des);
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

            return view;
        }

}

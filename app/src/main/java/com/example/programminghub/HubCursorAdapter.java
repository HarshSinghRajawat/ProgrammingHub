package com.example.programminghub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.programminghub.ui.C.CFragment;
import com.example.programminghub.ui.CPP.CPPFragment;
import com.example.programminghub.ui.JAVA.JavaFragment;


public class HubCursorAdapter extends CursorAdapter implements LoaderManager.LoaderCallbacks<Cursor> {
    public HubCursorAdapter(Context context, Cursor cursor){super(context,cursor,0);}
    private static final int data_loader=0;
    Context curView = null;
    HubCursorAdapter adapter;
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        return LayoutInflater.from(context).inflate(R.layout.adapter_layout,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title=(TextView)view.findViewById(R.id.title);
        TextView body=(TextView)view.findViewById(R.id.des);
        ImageView img = (ImageView)view.findViewById(R.id.view);
        curView=view.getContext();


        String code_title=cursor.getString(cursor.getColumnIndex("Title"));
        String code_body=cursor.getString(cursor.getColumnIndex("Code"));
        String des=cursor.getString(cursor.getColumnIndex("des"));
        byte[] en_img=cursor.getBlob(cursor.getColumnIndex("img"));



        Bitmap bitmap= BitmapFactory.decodeByteArray(en_img,0,en_img.length);
        Bitmap thumb=generateThumb(bitmap,76800);


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
        img.setImageBitmap(thumb);

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

    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection={DBSchema.c._ID,DBSchema.c._title,DBSchema.c._img,DBSchema.c._des,DBSchema.c._body};
        Uri curUri=null;
        if(curView.equals(CFragment.class)){
            curUri=DBSchema.C_Content_Uri;
        }
        else if(curView.equals(CPPFragment.class)){
            curUri= DBSchema.Cpp_Content_Uri;
        }
        else if(curView.equals(JavaFragment.class)){
            curUri=DBSchema.Java_Content_Uri;
        }else {Toast.makeText(curView,"Problem in OnCreate Mathod of the Loader",Toast.LENGTH_SHORT).show();
            return null;}

        return new CursorLoader(curView, curUri,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    public static Bitmap reduceBitmapSize(Bitmap bitmap,int MAX_SIZE) {
        double ratioSquare;
        int bitmapHeight, bitmapWidth;
        bitmapHeight = bitmap.getHeight();
        bitmapWidth = bitmap.getWidth();
        ratioSquare = (bitmapHeight * bitmapWidth) / MAX_SIZE;
        if (ratioSquare <= 1)
            return bitmap;
        double ratio = Math.sqrt(ratioSquare);
        Log.d("mylog", "Ratio: " + ratio);
        int requiredHeight = (int) Math.round(bitmapHeight / ratio);
        int requiredWidth = (int) Math.round(bitmapWidth / ratio);
        return Bitmap.createScaledBitmap(bitmap, requiredWidth, requiredHeight, true);
    }
    public static Bitmap generateThumb(Bitmap bitmap, int THUMB_SIZE) {
        double ratioSquare;
        int bitmapHeight, bitmapWidth;
        bitmapHeight = bitmap.getHeight();
        bitmapWidth = bitmap.getWidth();
        ratioSquare = (bitmapHeight * bitmapWidth) / THUMB_SIZE;
        if (ratioSquare <= 1)
            return bitmap;
        double ratio = Math.sqrt(ratioSquare);
        Log.d("mylog", "Ratio: " + ratio);
        int requiredHeight = (int) Math.round(bitmapHeight / ratio);
        int requiredWidth = (int) Math.round(bitmapWidth / ratio);
        return Bitmap.createScaledBitmap(bitmap, requiredWidth, requiredHeight, true);
    }
}

package com.example.programminghub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.FileInputStream;
import com.example.programminghub.DBSchema;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class insert extends AppCompatActivity {

    final int REQUEST_CODE_GALLERY=111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Button buttonView= (Button) findViewById(R.id.button);
        Button addButton=(Button)findViewById(R.id.add_img);
        ImageView img=(ImageView)findViewById(R.id.in_img);

        buttonView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                InsertIntoDB();
                Intent intent=new Intent(view.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });/*
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });
        img.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        insert.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });*/
    }
    private void InsertIntoDB(){
        EditText str1=(EditText)findViewById(R.id.text_title);
        EditText str2=(EditText)findViewById(R.id.text_code);
        RadioButton lan_cpp=(RadioButton)findViewById(R.id.lan_cpp);
        RadioButton lan_c=(RadioButton)findViewById(R.id.lan_c);
        RadioButton lan_java=(RadioButton)findViewById(R.id.lan_java);

        Editable EditableStr1=str1.getText();
        Editable EditableStr2=str2.getText();

        String title=EditableStr1.toString().trim();
        String code=EditableStr2.toString().trim();

        if(title.length()<=0||code.length()<=0){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }else{
        ContentValues values=new ContentValues();
        values.put(DBSchema.cpp._title,title);
        values.put(DBSchema.cpp._body,code);
        Uri uri=null;
        long status;

        if(lan_cpp.isChecked()){
            uri = getContentResolver().insert(DBSchema.Cpp_Insert_Uri,values);
        }else if(lan_c.isChecked()){
            uri = getContentResolver().insert(DBSchema.C_Insert_Uri,values);
        }else if(lan_java.isChecked()){
            //InsertRes(values);
            uri = getContentResolver().insert(DBSchema.Java_Insert_Uri,values);

        }

        status=Long.valueOf(uri.getLastPathSegment());
        Toast.makeText(this, "New Activity Added: " + status, Toast.LENGTH_SHORT).show();
        }
    }/*
    private ContentValues InsertImg(ContentValues values, String x){
        try{
            FileInputStream fs=new FileInputStream(x);
            byte[] imgbyte=new byte[fs.available()];
            fs.read(imgbyte);
            values.put(DBSchema.java._img,imgbyte);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }
    private ContentValues InsertRes(ContentValues values){
        try{
            FileInputStream fs=new FileInputStream(String.valueOf(R.drawable.test));
            byte[] imgbyte=new byte[fs.available()];
            fs.read(imgbyte);
            values.put(DBSchema.java._img,imgbyte);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }*/

}
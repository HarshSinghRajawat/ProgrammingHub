package com.example.programminghub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import com.example.programminghub.DBSchema;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class insert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Button buttonView= (Button) findViewById(R.id.button);
        buttonView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                InsertIntoDB();
                Intent intent=new Intent(view.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
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
            uri = getContentResolver().insert(DBSchema.Java_Insert_Uri,values);
        }

        status=Long.valueOf(uri.getLastPathSegment());
        Toast.makeText(this, "New Activity Added: " + status, Toast.LENGTH_SHORT).show();
        }
    }
}
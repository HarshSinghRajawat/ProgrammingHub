package com.example.programminghub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import com.example.programminghub.DBSchema;
import android.widget.EditText;
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
        Editable EditableStr1=str1.getText();
        Editable EditableStr2=str2.getText();

        String title=EditableStr1.toString().trim();
        String code=EditableStr2.toString().trim();

        DBhelper mhelper=new DBhelper(this);
        SQLiteDatabase db=mhelper.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(DBSchema.cpp._title,title);
        values.put(DBSchema.cpp._body,code);

        long newRowID=db.insert(DBSchema.cpp.Table_name,null,values);

        if (newRowID == -1) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "New Activity Added: " + newRowID, Toast.LENGTH_SHORT).show();
        }
    }
}
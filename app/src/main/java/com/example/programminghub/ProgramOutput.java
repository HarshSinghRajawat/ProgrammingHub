package com.example.programminghub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProgramOutput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_output);
        Intent intent=getIntent();
        String title=(String) intent.getSerializableExtra("title");
        String body =(String)intent.getSerializableExtra("body");
        TextView output_Title=(TextView)findViewById(R.id.out_title);
        TextView output_Code=(TextView)findViewById(R.id.out_code);
        output_Title.setText(title);
        output_Code.setText(body);
    }
}
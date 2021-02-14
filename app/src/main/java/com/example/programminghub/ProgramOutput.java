package com.example.programminghub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgramOutput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_output);
        Intent intent=getIntent();
        String title=(String) intent.getSerializableExtra("title");
        String body =(String)intent.getSerializableExtra("code");
        byte[] img=intent.getByteArrayExtra("img");

        TextView output_Title=(TextView)findViewById(R.id.out_title);
        TextView output_Code=(TextView)findViewById(R.id.out_code);
        ImageView imageView=(ImageView)findViewById(R.id.out_img);


        Bitmap bitmap= BitmapFactory.decodeByteArray(img,0,img.length);
        imageView.setImageBitmap(bitmap);


        output_Title.setText(title);
        output_Code.setText(body);

    }
}
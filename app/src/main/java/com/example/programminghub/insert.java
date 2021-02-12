package com.example.programminghub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import com.example.programminghub.DBSchema;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

public class insert extends AppCompatActivity {

    final int REQUEST_CODE_GALLERY=111;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Button buttonView= (Button) findViewById(R.id.button);
        Button addButton=(Button)findViewById(R.id.add_img);
        imageView=(ImageView) findViewById(R.id.in_img);

        buttonView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                InsertIntoDB();
                Intent intent=new Intent(view.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        insert.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
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
        values.put(DBSchema.cpp._img,imageToByte(imageView));
        //values.put(DBSchema.cpp._des,);

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    public static byte[] imageToByte(ImageView image){
        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }

}
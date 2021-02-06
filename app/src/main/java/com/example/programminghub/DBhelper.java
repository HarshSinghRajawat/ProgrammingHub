package com.example.programminghub;

import android.content.Context;
import com.example.programminghub.DBSchema;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    private static final String DatabaseName="Database.db";
    private static final int Version=1;
    public DBhelper(Context context){
        super(context,DatabaseName,null,Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CPP_CREATE_TABLE="CREATE TABLE "+ DBSchema.cpp.Table_name +"(" +
                DBSchema.cpp._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                DBSchema.cpp._title+" TEXT NOT NULL,"+
                DBSchema.cpp._body+" TEXT NOT NULL);";
        String C_CREATE_TABLE="CREATE TABLE "+ DBSchema.c.Table_name +"(" +
                DBSchema.c._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                DBSchema.c._title+" TEXT NOT NULL,"+
                DBSchema.c._body+" TEXT NOT NULL);";

        db.execSQL(CPP_CREATE_TABLE);
        db.execSQL(C_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

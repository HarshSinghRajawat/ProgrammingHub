package com.example.programminghub;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.CancellationSignal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContentProvider extends android.content.ContentProvider {

    DBhelper mhelper;

    public static final int GetData_cpp=100;
    public static final int Insert_cpp=101;
    public static final int GetData_c=102;

    private static final UriMatcher sUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(DBSchema.Content_Authority,"Cpp/GetData",GetData_cpp);
        sUriMatcher.addURI(DBSchema.Content_Authority,"Cpp/Insert",Insert_cpp);
    }

    @Override
    public boolean onCreate() { mhelper=new DBhelper(getContext());return false;}

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection,  String selection, String[] selectionArgs,  String sortOrder) {

        Cursor cursor;
        SQLiteDatabase db=mhelper.getReadableDatabase();

        switch(sUriMatcher.match(uri)){
            case GetData_cpp:
                cursor=db.query(DBSchema.cpp.Table_name,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case GetData_c:
                cursor=db.query(DBSchema.c.Table_name,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new SQLException("Failed To Load Data From Database: "+uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues)
    {
        Uri InsertUri=null;
        if(mhelper==null){
            mhelper=new DBhelper(getContext());
        }
        final SQLiteDatabase db=mhelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)){
            case Insert_cpp:
                long status=db.insert(DBSchema.cpp.Table_name,null,contentValues);
                if(status>0){
                    InsertUri= ContentUris.withAppendedId(DBSchema.Cpp_Insert_Uri,status);
                    getContext().getContentResolver().notifyChange(InsertUri,null);
                }
                break;
            default:throw new SQLException("Failed to Insert Row Into "+uri);
        }
        return InsertUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}

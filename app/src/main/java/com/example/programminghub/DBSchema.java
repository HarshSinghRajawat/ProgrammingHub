package com.example.programminghub;

import android.net.Uri;
import android.provider.BaseColumns;

public final class DBSchema {
    private DBSchema(){}

    public static final String Content_Authority="com.example.programminghub";

    public static final Uri Base_Content_Uri=Uri.parse("content://"+Content_Authority);


    public static final Uri Cpp_Content_Uri=Uri.withAppendedPath(Base_Content_Uri,"Cpp/GetData");
    public static final Uri C_Content_Uri=Uri.withAppendedPath(Base_Content_Uri,"C/GetData");
    public static final Uri Cpp_Insert_Uri=Uri.withAppendedPath(Base_Content_Uri,"Cpp/Insert");
    public static final Uri C_Insert_Uri=Uri.withAppendedPath(Base_Content_Uri,"C/Insert");
    public static final Uri Java_Insert_Uri=Uri.withAppendedPath(Base_Content_Uri,"Java/Insert");
    public static final Uri Java_Content_Uri=Uri.withAppendedPath(Base_Content_Uri,"Java/GetData");
    //public static final Uri GetId=Uri.withAppendedPath(Base_Content_Uri,"GetData/Id");

    public static final class cpp implements BaseColumns{
        public static final String Table_name="cppPrograms";
        public static final String _ID=BaseColumns._ID;
        public static final String _title="Title";
        public static final String _body="Code";
        public static final String _lan="lan";
        public static final String _des="des";
        public static final String _img="img";
    }
    public static final class c implements BaseColumns{
        public static final String Table_name="cPrograms";
        public static final String _ID=BaseColumns._ID;
        public static final String _title="Title";
        public static final String _body="Code";
        public static final String _lan="lan";
        public static final String _des="des";
        public static final String _img="img";
    }
    public static final class java implements BaseColumns{
        public static final String Table_name="javaPrograms";
        public static final String _ID=BaseColumns._ID;
        public static final String _title="Title";
        public static final String _body="Code";
        public static final String _lan="lan";
        public static final String _des="des";
        public static final String _img="img";
    }
}

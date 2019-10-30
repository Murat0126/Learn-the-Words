package com.example.learnthewords.dataBase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {
 
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dictionaryDb";
    public static final String TABLE_DICTIONARY = "dictionary";
 
    public static final String KEY_ID = "_id";
    public static final String KEY_ORIGIN_WORD = "origin";
    public static final String KEY_TRANSLATE_OF_WORD = "translate";
 
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_DICTIONARY + "(" + KEY_ID
                + " integer primary key," + KEY_ORIGIN_WORD + " text," + KEY_TRANSLATE_OF_WORD + " text" + ")");
 
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_DICTIONARY);
 
        onCreate(db);
 
    }
}
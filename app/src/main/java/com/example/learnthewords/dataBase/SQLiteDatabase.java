package com.example.learnthewords.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.learnthewords.model.Words;

import java.util.ArrayList;

public class SQLiteDatabase extends SQLiteOpenHelper {

    private	static final int DATABASE_VERSION =	5;
    private	static final String	DATABASE_NAME = "word";
    private	static final String TABLE_WORDS = "words";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_ORIGIN = "origin";
    private static final String COLUMN_TRANSLATE = "translate";

    public SQLiteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        String	CREATE_WORDS_TABLE = "CREATE	TABLE " + TABLE_WORDS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_ORIGIN + " TEXT," + COLUMN_TRANSLATE + " TEXT" + ")";
        db.execSQL(CREATE_WORDS_TABLE);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        onCreate(db);
    }

    public ArrayList<Words> listWords(){
        String sql = "select * from " + TABLE_WORDS;
        android.database.sqlite.SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Words> storeWords = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String word = cursor.getString(1);
                String translate = cursor.getString(2);
                storeWords.add(new Words(id, word, translate));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeWords;
    }

    public void addWords(Words words){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORIGIN, words.getOriginWord());
        values.put(COLUMN_TRANSLATE, words.getTranslateWord());
        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_WORDS, null, values);
    }

    public void updateWords(Words words){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORIGIN, words.getOriginWord());
        values.put(COLUMN_TRANSLATE, words.getTranslateWord());
        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_WORDS, values, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(words.getId())});
    }

    public Words findWords(String originword){
        String query = "Select * FROM "	+ TABLE_WORDS + " WHERE " + COLUMN_ORIGIN + " = " + "originword";
        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        Words words = null;
        Cursor cursor = db.rawQuery(query,	null);
        if	(cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            String word = cursor.getString(1);
            String translate = cursor.getString(2);
            words = new Words(id, word, translate);
        }
        cursor.close();
        return words;
    }

    public void deleteWord(int id){
        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORDS, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(id)});
    }
}
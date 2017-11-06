package com.example.haams.myapplication.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by haams on 2017-11-01.
 */

public class Database {
    public static final String TAG = "Database";
    public static int DATABASE_VERSION = 1;


    private static Database database = null;
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    private Context context;


    public Database(Context context) {
        this.context = context;
    }

    public static Database getDBInstance(Context context) {
        if (database == null) {
            database = new Database(context);
        }
        return database;
    }

    public boolean open(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return true;
    }
    // open --> 쓰기모드
    // open2 --> 읽기모드
    public boolean open2(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getReadableDatabase();
        return true;
    }

    public void close(){
        db.close();
        database = null;
    }
    public Cursor rawQuery(String SQL){
        Cursor cursor = null;
        try{
            cursor = db.rawQuery(SQL,null);
        }catch(Exception exception){
            Log.e(TAG, exception.toString());
        }
        return cursor;
    }

    public boolean execSQL(String SQL){
        try{
            Log.d(TAG,"SQL : "+SQL);
            db.execSQL(SQL);
        }catch(Exception exception){
            Log.e(TAG,exception.toString());
            return false;
        }
        return true;
    }
}

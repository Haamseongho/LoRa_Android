package com.example.haams.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by haams on 2017-11-02.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE medTable4 (_id INTEGER PRIMARY KEY AUTOINCREMENT, medName TEXT, startDate VARCHAR, endDate VARCHAR , medTimes VARCHAR);";

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table medTable4;";
        db.execSQL(sql);
        onCreate(db);
    }
}

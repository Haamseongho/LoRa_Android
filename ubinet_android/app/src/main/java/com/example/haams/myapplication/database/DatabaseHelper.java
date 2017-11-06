package com.example.haams.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.StringBuilderPrinter;

/**
 * Created by haams on 2017-11-01.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;
    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, "medDBs", null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE medTable3 (_id INTEGER PRIMARY KEY AUTOINCREMENT, medName TEXT, startDate VARCHAR, endDate VARCHAR , medTimes VARCHAR);";
  /*  private static final String CREATE_TABLE_SQL = "CREATE TABLE medTables "
            + "(id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,"
            + "medName VARCHAR,"
            + "startDate VARCHAR,"
            + "endDate VARCHAR,"
            + "medTimes VARCHAR);";*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_SQL);
            Log.i(TAG, "medTables 테이블 생성됨");
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}

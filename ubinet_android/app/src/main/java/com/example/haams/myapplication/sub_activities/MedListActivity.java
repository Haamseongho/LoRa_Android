package com.example.haams.myapplication.sub_activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.example.haams.myapplication.MainActivity;
import com.example.haams.myapplication.R;
import com.example.haams.myapplication.adapters.MedListAdapter;
import com.example.haams.myapplication.data.MedList;
import com.example.haams.myapplication.database.Database;
import com.example.haams.myapplication.database.MySQLiteOpenHelper;

import java.util.ArrayList;

public class MedListActivity extends AppCompatActivity {

    private Database database;
    private RecyclerView medListView;
    private MedListAdapter medListAdapter;

    private final String TAG = "MedListActivity";
    private MySQLiteOpenHelper helper;
    String dbName = "mydb.db";
    int dbVersion = 1;
    private SQLiteDatabase db;
    private ArrayList<Integer> medNumList;
    private ArrayList<MedList> medLists;
    private ArrayList<String> medNameList;
    private ArrayList<String> medSrtList;
    private ArrayList<String> medEndList;
    private ArrayList<String> medTimeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_list);

        initViews();
    }

    private void initViews() {
        medNumList = new ArrayList<>();
        medNameList = new ArrayList<>();
        medSrtList = new ArrayList<>();
        medEndList = new ArrayList<>();
        medTimeList = new ArrayList<>();

        try {
            helper = new MySQLiteOpenHelper(
                    this,
                    dbName,
                    null,
                    dbVersion
            );
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        try {
            db = helper.getReadableDatabase();
        } catch (SQLiteException ex) {
            ex.printStackTrace();
            Log.e(TAG, "DB 받을 수 없음");
        }

        int i = 0;
        String SQL2 = "SELECT * FROM medTable4";
        Cursor dbCursor = db.rawQuery(SQL2, null);
        medListView = (RecyclerView) findViewById(R.id.medListView);

        Log.d(TAG, dbCursor.toString());
        Log.d(TAG, dbCursor.getColumnName(1) + "//" + dbCursor.getColumnName(2) + "//" + dbCursor.getColumnName(3) + "//" + dbCursor.getColumnName(4));
        while (dbCursor.moveToNext()) {
            String medName, medSrtDate, medEndDate, medTimes;
            int medNum;

            medNum = dbCursor.getInt(0);
            medName = dbCursor.getString(1);
            medSrtDate = dbCursor.getString(2);
            medEndDate = dbCursor.getString(3);
            medTimes = dbCursor.getString(4);
            Log.d(TAG,medNum+"//"+medName+"//"+medSrtDate+"//"+medEndDate+"//"+medTimes);

            medNumList.add(i,medNum);
            medNameList.add(i,medName);
            medSrtList.add(i,medSrtDate.substring(0,11));
            medEndList.add(i,medEndDate.substring(0,11));
            medTimeList.add(i,medTimes);
            i++;
        }
        medLists = new ArrayList<>();
        for(int j=0;j<medNameList.size();j++){
            medLists.add(new MedList(medNumList.get(j),medNameList.get(j),medSrtList.get(j),medEndList.get(j),medTimeList.get(j)));
        }


        // medLists 저장
        medListAdapter = new MedListAdapter(medLists,this);
       // medListAdapter.addItem(medName, medSrtDate, medEndDate, medTimes);
        medListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        medListView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        medListView.setAdapter(medListAdapter);

        // 드래그로 지우기
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                medListAdapter.move(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                medListAdapter.removeAtPosition(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(medListView);

        db.close();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MedListActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
        super.onBackPressed();
    }
}

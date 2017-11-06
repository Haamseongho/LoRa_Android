package com.example.haams.myapplication.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.haams.myapplication.R;
import com.example.haams.myapplication.data.MedList;
import com.example.haams.myapplication.database.MySQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by haams on 2017-11-01.
 */

public class MedListAdapter extends RecyclerView.Adapter<MedListAdapter.ViewHolder> {

    private ArrayList<MedList> medLists;
    private Context context;
    private MySQLiteOpenHelper helper;
    String dbName = "mydb.db";
    int dbVersion = 1;
    private SQLiteDatabase db;
    private static final String TAG = "MedListAdapter";

    public MedListAdapter(ArrayList<MedList> medLists, Context context) {
        this.medLists = medLists;
        this.context = context;
    }

    public MedListAdapter(Context context) {
        this.context = context;
        medLists = new ArrayList<>();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView medNum;
        private TextView medName;
        private TextView medSrtDate;
        private TextView medEndDate;
        private TextView medTimes;

        public ViewHolder(View itemView) {
            super(itemView);
            medNum = (TextView)itemView.findViewById(R.id.medNum);
            medName = (TextView) itemView.findViewById(R.id.medName);
            medSrtDate = (TextView) itemView.findViewById(R.id.medSrtDate);
            medEndDate = (TextView) itemView.findViewById(R.id.medEndDate);
            medTimes = (TextView) itemView.findViewById(R.id.medTime);
        }
    }

    @Override
    public MedListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_med_list_items, parent, false);
        ViewHolder vh = new ViewHolder(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(MedListAdapter.ViewHolder holder, int position) {
        /*
        여기서 SQLite 접근해서 내용을 넣어야 됨
         */
        holder.medNum.setText(String.valueOf(medLists.get(position).getMedNum()));
        holder.medName.setText(medLists.get(position).getMedName());
        holder.medSrtDate.setText(medLists.get(position).getMedSrtDate());
        holder.medEndDate.setText(medLists.get(position).getMedEndDate());
        holder.medTimes.setText(medLists.get(position).getMedTimes());

    }


    @Override
    public int getItemCount() {
        return medLists.size();
    }

    public void addAtPosition(int position,String medName, String medSrtDate, String medEndDate, String medTimes) {
        if (position > medLists.size()) {
            // 전체 사이즈보다 클 경우 .. 맨 뒤로!!
            position = medLists.size();
        }
        medLists.add(position, new MedList(medName, medSrtDate, medEndDate, medTimes));
        notifyItemInserted(position);
    }

    public void removeAtPosition(int position) {
        try {
            helper = new MySQLiteOpenHelper(
                    context,
                    dbName,
                    null,
                    dbVersion
            );
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex) {
            ex.printStackTrace();
            Log.e(TAG, "DB 받을 수 없음");
        }
        if (position < medLists.size()) {
            medLists.remove(position);
            db.execSQL("DELETE FROM medTable4 WHERE _id = " + (position+1));
            notifyItemRemoved(position);
            db.close();
        }
    }

    public void move(int fromPosition, int toPosition) {
        final String medName = medLists.get(fromPosition).getMedName();
        final String startDate = medLists.get(fromPosition).getMedSrtDate();
        final String endDate = medLists.get(fromPosition).getMedEndDate();
        final String times = medLists.get(fromPosition).getMedTimes();
        medLists.remove(fromPosition);
        medLists.add(toPosition, new MedList(medName, startDate, endDate, times));
        notifyItemMoved(fromPosition, toPosition);
    }
}

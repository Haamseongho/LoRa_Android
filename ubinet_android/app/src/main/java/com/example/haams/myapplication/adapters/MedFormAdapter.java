package com.example.haams.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.haams.myapplication.R;
import com.example.haams.myapplication.data.MedForm;
import com.example.haams.myapplication.listener.ButtonClickListener;

import java.util.ArrayList;

/**
 * Created by haams on 2017-08-06.
 */

public class MedFormAdapter extends BaseAdapter {
    private ArrayList<MedForm> medFormList;
    private Context context;

    public MedFormAdapter(ArrayList<MedForm> medFormList, Context context) {
        this.medFormList = medFormList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return medFormList.size();
    }

    @Override
    public Object getItem(int position) {
        return medFormList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class Holder{
        EditText medListEdt;
        Button btnSetAlarm;
        Button btnSetDate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = null;
        if (convertView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.activity_med_form_list_items, parent, false);
        }
        Holder holder = new Holder();
        holder.medListEdt = (EditText)itemView.findViewById(R.id.medListEdt);
        holder.btnSetAlarm = (Button)itemView.findViewById(R.id.btnSetAlarm);
        holder.btnSetAlarm = (Button)itemView.findViewById(R.id.btnSetDate);

        /*
        뷰가지고 와서 이걸 서버에 넘겨야 한다.
        여기서 작업을 해야함
         */

        holder.btnSetAlarm.setOnClickListener(new ButtonClickListener(context,medFormList,position));
        holder.btnSetDate.setOnClickListener(new ButtonClickListener(context,medFormList,position));


        return itemView;
    }
}

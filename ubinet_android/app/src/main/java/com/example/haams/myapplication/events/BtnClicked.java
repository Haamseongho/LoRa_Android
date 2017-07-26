package com.example.haams.myapplication.events;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.haams.myapplication.IntroActivity;
import com.example.haams.myapplication.R;
import com.example.haams.myapplication.sub_activities.HealthActivity;
import com.example.haams.myapplication.sub_activities.MapActivity;
import com.example.haams.myapplication.sub_activities.MapTrackActivity;

/**
 * Created by haams on 2017-06-29.
 */
public class BtnClicked implements View.OnClickListener {

    private String Ltid;
    private static final String TAG = "BtnClicked";
    private Context context;
    private IntroActivity iActivity;

    public BtnClicked(Context context) {
        this.context = context;
        iActivity = new IntroActivity();
    }

    public BtnClicked(Context context, String sLtid) {
        this.context = context;
        this.Ltid = sLtid;
        Log.i(TAG, sLtid);
        Log.i(TAG, Ltid);
    }

    @Override
    public void onClick(View v) {
        Intent s2Intent = null; // send to sub (s,s)
        switch (v.getId()) {
            case R.id.btnTrack:
                s2Intent = new Intent(context, MapTrackActivity.class);
                s2Intent.putExtra("Ltid",Ltid);
                context.startActivity(s2Intent);
                break;

            case R.id.btnHealth:
                s2Intent = new Intent(context, HealthActivity.class);
                s2Intent.putExtra("Ltid",Ltid);
                context.startActivity(s2Intent);
                break;

            case R.id.btnLocation:
                s2Intent = new Intent(context, MapActivity.class);
                s2Intent.putExtra("Ltid",Ltid);
                context.startActivity(s2Intent);
                break;

        }
    }
}

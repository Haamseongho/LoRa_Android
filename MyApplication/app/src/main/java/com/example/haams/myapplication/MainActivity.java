package com.example.haams.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.haams.myapplication.events.BtnClicked;

public class MainActivity extends AppCompatActivity {
    private Intent gIntent;
    private String sLtid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBtnClick();
    }

    private void checkBtnClick() {
        gIntent = getIntent();
        sLtid = gIntent.getStringExtra("Ltid");
        findViewById(R.id.btnTrack).setOnClickListener(new BtnClicked(this,sLtid));
        findViewById(R.id.btnHealth).setOnClickListener(new BtnClicked(this,sLtid));
        findViewById(R.id.btnLocation).setOnClickListener(new BtnClicked(this,sLtid));
    }
}

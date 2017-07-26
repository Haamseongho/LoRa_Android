package com.example.haams.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haams.myapplication.events.BtnClicked;
import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

public class MainActivity extends AppCompatActivity {
    private Intent gIntent;
    private String sLtid;
    private static final String TAG = "MainActivity";
    private TextView profile_id,nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        checkBtnClick();
    }

    private void initViews() {
        profile_id = (TextView)findViewById(R.id.profile_id);
        nickname = (TextView)findViewById(R.id.nickname);
        gIntent = getIntent();

        Glide.with(this)
                .load(gIntent.getStringExtra("image"))
                .skipMemoryCache(true)
                .fitCenter().into((ImageView)findViewById(R.id.profile_image));

        profile_id.setText(gIntent.getStringExtra("id"));
        nickname.setText(gIntent.getStringExtra("nickname"));
    }


    private void checkBtnClick() {
        gIntent = getIntent();
        sLtid = gIntent.getStringExtra("Ltid");
        findViewById(R.id.btnTrack).setOnClickListener(new BtnClicked(this));
        findViewById(R.id.btnHealth).setOnClickListener(new BtnClicked(this));
        findViewById(R.id.btnLocation).setOnClickListener(new BtnClicked(this));
    }
}

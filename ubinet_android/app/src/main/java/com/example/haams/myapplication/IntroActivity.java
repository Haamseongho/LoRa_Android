package com.example.haams.myapplication;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.haams.myapplication.data.Admin;
import com.example.haams.myapplication.events.BtnClicked;
import com.example.haams.myapplication.server.Network;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.auth.ISessionCallback;
import com.kakao.util.exception.KakaoException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignUp;
    private static final String TAG = "IntroActivity";
    /*
    Session 관리
     */
    private SessionCallback callback; // -- KAkao ISession


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        findViewById(R.id.btn_kakao_login).setOnClickListener(this);
        getSessionCallback();
    }

    private void getSessionCallback() {
        Log.i(TAG, "세션시작");
        /*
        각 SNS 세션 받아오기
         */
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    this.getPackageName(),
                    PackageManager.GET_SIGNATURES
            );
            for (Signature signature : info.signatures) {
                // 인증 후 서명 --> 암호화
                MessageDigest md = null;
                try {
                    md = MessageDigest.getInstance("SHA");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                md.update(signature.toByteArray());
                Log.i("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "??");
            e.printStackTrace();
        }
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            Log.i(TAG, "세션 성공");
            redirectSignUp();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Toast.makeText(IntroActivity.this, exception + "///", Toast.LENGTH_LONG).show();
            Log.e(TAG, "세션 실패");
        }
    }

    private void redirectSignUp() {
        startActivity(new Intent(IntroActivity.this, SignUpActivity.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            Log.i(TAG + "111", Session.getCurrentSession() + "/" + requestCode + "/" + data + "입니다.");
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_kakao_login:
                Log.d("BtnClicked", "클릭은되냐?");
                startActivity(new Intent(IntroActivity.this, SignUpActivity.class));
                break;
        }
    }
}

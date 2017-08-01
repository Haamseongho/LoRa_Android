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
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignUp;
    private static final String TAG = "IntroActivity";
    private FirebaseInstanceIdService firebaseInstanceIdService;
    private LoginButton btnFacebookLogin;
    private Intent fIntent;
    /*
    Session 관리
     */
    private SessionCallback callback; // -- KAkao ISession
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        getSessionCallback();
        initViews();
    }

    private void initViews() {
        firebaseInstanceIdService = new FirebaseInstanceIdService();
        findViewById(R.id.btn_kakao_login).setOnClickListener(this);
        btnFacebookLogin = (LoginButton)findViewById(R.id.btn_facebook_login);

        callbackManager = CallbackManager.Factory.create();
        // Facebook callbackManager
        btnFacebookLogin.setReadPermissions(Arrays.asList("public_profile","email"));
        // 로그인 할 경우에 보여줄 퍼미션 설정 (public_profile : 기본 프로필 전부 ,  email : 이메일
        btnFacebookLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("facebook_login",object.toString());
                        try {
                            String name = object.getString("name");
                            String email = object.getString("email");
                            String gender = object.getString("gender");
                            fIntent = new Intent(IntroActivity.this,MainActivity.class);
                            fIntent.putExtra("name",name);
                            fIntent.putExtra("email",email);
                            fIntent.putExtra("gender",gender);
                            startActivity(fIntent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                Bundle bundle = new Bundle();
                bundle.putString("fields","id,name,email,gender,birthday");
                graphRequest.setParameters(bundle);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e("facebook_login_error",error.toString());
            }
        });
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
        callbackManager.onActivityResult(requestCode,resultCode,data);
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
                firebaseInstanceIdService.onTokenRefresh();
                startActivity(new Intent(IntroActivity.this, SignUpActivity.class));
                break;
        }
    }
}

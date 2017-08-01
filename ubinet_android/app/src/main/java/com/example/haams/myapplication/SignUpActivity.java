package com.example.haams.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        requestMe();
    }

    public void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                ErrorCode error = ErrorCode.valueOf(errorResult.getErrorCode());
                Log.d(TAG, String.valueOf(error));
                if (error == ErrorCode.CLIENT_ERROR_CODE) {
                    finish(); // 클라이언트 에러일 경우 종료
                } else {
                    Log.i(TAG, "사용자 인증실패");
                    redirectHere(); // 사용자 인증 실패가 아닌 경우 다시 현재 페이지
                }
            }

            @Override
            public void onNotSignedUp() {
                // 카카오톡 회원이 아님
            }

            @Override
            public void onSuccess(UserProfile result) {
                Log.i(TAG, "성공!!");
                redirectMainActivity(result);
            }
        });
    }


    private void redirectHere() {
        final Intent intent = new Intent(this, IntroActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    private void redirectMainActivity(UserProfile result) {
        final Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        Log.i(TAG,result.toString());
        intent.putExtra("nickname", result.getNickname());
        intent.putExtra("id", result.getId());
        intent.putExtra("image", result.getProfileImagePath());
        startActivity(intent);
        finish();
    }
}

package com.example.haams.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.haams.myapplication.s_data.Admin;
import com.example.haams.myapplication.server.Network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntroActivity extends AppCompatActivity {

    private EditText edtLtid;
    private EditText edtPassword;
    private Button btnSignUp;
    private Network network;
    private static final String TAG = "IntroActivity";
    private Intent sIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        initViews();
    }

    private void initViews() {
        edtLtid = (EditText)findViewById(R.id.edtLtid);
        edtPassword = (EditText)findViewById(R.id.edtPassWord);
        btnSignUp = (Button)findViewById(R.id.btnLogin);

        Glide.with(this).load(R.drawable.ubinet_img3)
                .skipMemoryCache(true)
                .fitCenter().into((ImageView)findViewById(R.id.mBanner));


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chkSignUp();
                finish();
            }
        });
    }

    private void chkSignUp() {
        network = Network.getNetworkInstance();
        network.getAdminProxy().signUpWithLtid(edtLtid.getText().toString(), edtPassword.getText().toString()
                , new Callback<Admin>() {
                    @Override
                    public void onResponse(Call<Admin> call, Response<Admin> response) {
                        if(response.isSuccessful()){
                            Log.d(TAG,response.body().toString());
                            sIntent = new Intent(IntroActivity.this,MainActivity.class);
                            sIntent.putExtra("Ltid",response.body().getLtid());
                            sIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(sIntent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Admin> call, Throwable t) {
                        Log.e(TAG,t.toString());
                    }
                });
    }
}

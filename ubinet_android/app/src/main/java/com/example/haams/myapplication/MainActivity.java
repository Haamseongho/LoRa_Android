package com.example.haams.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.haams.myapplication.listener.ButtonClickListener;
import com.example.haams.myapplication.listener.IndexSendMsg;
import com.example.haams.myapplication.sign_up.TokenStorage;
import com.example.haams.myapplication.sub_activities.MapActivity;
import com.example.haams.myapplication.sub_activities.MapTrackActivity;
import com.example.haams.myapplication.sub_activities.MedFormActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private Intent gIntent;
    private String sLtid;
    private static final String TAG = "MainActivity";
    private IndexSendMsg indexingMsg;
    private Intent mIntent = null;
    private AlertDialog.Builder mDlg;
    private EditText edtLTID;

    @BindView(R.id.btn_spot_track)
    Button btnSpots;
    @BindView(R.id.btn_medicate_form)
    Button btnMedicate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (existIDInServer()) {
            // MainActivity 보여주기
        } else {
            checkLTID();
        }

    }

    private boolean existIDInServer() {
        TokenStorage tokenStorage = new TokenStorage(this);
        if ((tokenStorage.getPreferences("K_token") != null) || (tokenStorage.getPreferences("F_token")) != null) {
            return true;
        }
        return false;
    }

    private void checkLTID() {
        mDlg = new AlertDialog.Builder(this);
        final View edtView = LayoutInflater.from(this).inflate(R.layout.activity_edt_ltid, null);
        edtLTID = (EditText) edtView.findViewById(R.id.edtLTID);
        mDlg.setTitle("LTID 정보 입력");
        mDlg.setMessage("확인할 LTID를 입력해주세요");
        mDlg.setView(edtView);
        mDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                matchIDwithLTID(edtLTID.getText().toString());
            }
        });
    }

    private void matchIDwithLTID(String Ltid) {
        getIntent().getStringExtra("id");
        /*
         id값을 가지고 먼저 서버의 데이터베이스에 저장이 되어 있는지 확인을 한다.
         확인을 한 다음에 해당 아이디가 없을 경우 Ltid를 서버에 넣어 두기
         확인을 한 다음에 해당 아이디가 있을 경우 LTID를 뽑아오고 이 LTID를 Ltid와 비교한다.
         같을 경우 Toast메시지 띄우기 (환영메세지)
         다를 경우 LTID를 다시 설정해주세요 메세지와 함께 checkLTID() 메서드를 다시 수행 (-> LTID 입력 후 아이디에 맞게 Ltid 서버에 넣어 지게 됨 해당 id를 식별자로 하여
         put하기
        */

        /*
        서버 코드 --> SNS로 로그인 할 경우 SNS에서 가져올 정보
        - ID
        - Name
         */

    }

    @OnClick({R.id.btn_spot_track, R.id.btn_medicate_form})
    public void mainButtonClick(View v) {
        switch (v.getId()) {
            case R.id.btn_spot_track:
                spotInfoChecked();
                break;

            case R.id.btn_medicate_form:
                medicateInfoChecked();
                break;
        }
    }


    private void spotInfoChecked() {
        final AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        final View spotView = LayoutInflater.from(this).inflate(R.layout.activity_spot_info_check, null);
        final Button btnSpotNow, btnSpotTrack;
        btnSpotNow = (Button) spotView.findViewById(R.id.btn_spot_now);
        btnSpotTrack = (Button) spotView.findViewById(R.id.btn_spot_track);

        btnSpotNow.setOnClickListener(new ButtonClickListener(spotView.getContext()));
        btnSpotTrack.setOnClickListener(new ButtonClickListener(spotView.getContext()));

        indexingMsg = new IndexSendMsg(spotView.getContext());


        dlg.setView(spotView);
        dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });
            }
        });

        /*
        대화상자 --> 인덱스 값 넘기기
        // 위치 정보 or 투약 폼 정리 ( 1 -> 현재 위치 알려주기 / 2 -> 하루 전체 경로 트랙킹)
         */

        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkSpotInfo(indexingMsg.getPreferences("index"));
            }
        });
        dlg.show();
    }

    private void checkSpotInfo(int index) {
        /*
        edtLTID를 같이 넘기고 지도를 보여주는 액티비티에서 해당 LTID를 가지고
        정보를 식별하여 위치 정보를 불러온다.
         */
        if (index == 1) {
            mIntent = new Intent(MainActivity.this, MapActivity.class);
            Log.i(TAG, "현재 위치");
        } else if (index == 2) {
            mIntent = new Intent(MainActivity.this, MapTrackActivity.class);
            Log.i(TAG, "오늘 하루 경로");
        } else {
            Log.e(TAG, "인덱싱 에러(맵)");
        }
        startActivity(mIntent);
    }

    private void medicateInfoChecked() {

        mIntent = new Intent(MainActivity.this, MedFormActivity.class);
        startActivity(mIntent);
    }
}

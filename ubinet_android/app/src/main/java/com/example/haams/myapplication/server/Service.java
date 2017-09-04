package com.example.haams.myapplication.server;

import com.example.haams.myapplication.data.Admin;
import com.example.haams.myapplication.data.Guard;
import com.example.haams.myapplication.data.MedForm;
import com.example.haams.myapplication.data.User;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by haams on 2017-06-29.
 */

public interface Service {
    /*
    부양자녀 --> LTID == 디바이스 사용자(노인들) LTID
    Password는 제공해줄 예정정     */
    @FormUrlEncoded
    @POST("/admin/signup")
    public Call<Admin> signUpWithLtid(
            @Field("Ltid") String Ltid,
            @Field("Password") String Password
    );

    /*
    보호자(guard) - 로그인 시 (sns 계정 로그인 -> 이름 뽑아오기 ) /
     */
    @FormUrlEncoded
    @POST("/guard/insertinfo")
    public Call<Guard> saveGuardInfoToServer(
            @Field("LTID") String LTID,
            @Field("name") String name
    );

    @GET("/user/getLTID")
    public Call<User> getUserPhoneNumber(
            @Field("tel") String tel
    );

    @FormUrlEncoded
    @POST("/medform/insert")
    public Call<MedForm> setMedFormDataToServer(
            @Field("medName") String medName,
            @Field("alarmHour") ArrayList<Integer> alarmHour,
            @Field("alarmMin") ArrayList<Integer> alarmMin,
            @Field("startDate") Date startDate,
            @Field("endDate") Date endDate
    );
}

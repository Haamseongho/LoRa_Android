package com.example.haams.myapplication.server;

import com.example.haams.myapplication.data.Admin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
}

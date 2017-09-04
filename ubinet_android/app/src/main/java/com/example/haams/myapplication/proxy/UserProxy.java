package com.example.haams.myapplication.proxy;

import com.example.haams.myapplication.data.User;
import com.example.haams.myapplication.server.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by haams on 2017-09-04.
 */

public class UserProxy {
    private Service service;

    public UserProxy(Retrofit retrofit) {
        service = retrofit.create(Service.class);
    }

    // 전화번호로 LTID 가지고오기.
    public void getLTIDByTelNum(String tel, Callback<User> callback) {
        Call<User> call = service.getUserPhoneNumber(tel);
        call.enqueue(callback);
    }
}

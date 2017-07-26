package com.example.haams.myapplication.server;

import com.example.haams.myapplication.proxy.AdminProxy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by haams on 2017-06-29.
 */

public class Network {
    public static Network network;
    private Retrofit retrofit;
    private AdminProxy adminProxy;

    public static Network getNetworkInstance(){
        if(network == null){
            network = new Network();
        }
        return network;
    }

    public Network() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder().baseUrl("http://52.79.83.51:2721/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build()).build();

        adminProxy = new AdminProxy(retrofit);
    }

    public AdminProxy getAdminProxy() {
        return adminProxy;
    }
}

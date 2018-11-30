package com.example.lenovo.yourgym1;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    public static final String BASE_URL = "http://172.24.41.183:8000/";   //使用了目录形式的url
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static String thisname = "";
    public static String password = "";

}

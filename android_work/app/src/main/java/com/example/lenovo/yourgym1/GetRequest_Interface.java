package com.example.lenovo.yourgym1;
import java.util.List;

import retrofit2.Call;    //Call是retrofit的一个接口
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

//创建用于描述网络请求的接口
public interface GetRequest_Interface {
   //get注解的作用：采用get方法发送网络请求
    //getCall() 接受网络请求数据的方法
    @POST("register")
    @FormUrlEncoded
    Call<Reception> getCall(@Field("name") String name, @Field("password1") String password1, @Field("password2") String password2);  //调用这个方法会返回一个类
    @POST("login")
    @FormUrlEncoded
    Call<Reception> login(@Field("name") String name, @Field("password") String password);

    @GET("myname")
    Call<Reception> myname();
}

package com.example.shuvo.weatherapp.network;


import com.example.shuvo.weatherapp.modelclass.Weatherapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

  @GET("/data/2.5/box/city?bbox=12,32,15,37,10&appid=b6907d289e10d714a6e88b30761fae22")
    Call<Weatherapp> getweather();
}

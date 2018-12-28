package com.example.shuvo.weatherapp.adapter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.shuvo.weatherapp.R;
import com.example.shuvo.weatherapp.database.WeatherContent;
import com.example.shuvo.weatherapp.database.WeatherDBHelper;
import com.example.shuvo.weatherapp.modelclass.Weather;
import com.example.shuvo.weatherapp.modelclass.Weatherapp;
import com.example.shuvo.weatherapp.network.GetDataService;
import com.example.shuvo.weatherapp.network.RetrofitBase_Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteDatabase mDatabase;
    private WeatherDBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new WeatherDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        if(getAllItems().getCount() != 0 && getAllItems() != null){
            Toast.makeText(this, "Getting data from database", Toast.LENGTH_SHORT).show();
            gettingDataFromDatabase();

        }else{
            Toast.makeText(this, "Getting data from server", Toast.LENGTH_SHORT).show();
            gettingDataFromServer();
        }


    }

    private void gettingDataFromServer(){

        GetDataService service = RetrofitBase_Url.getRetrofitInstance().create(GetDataService.class);
        Call<Weatherapp> call = service.getweather();
        call.enqueue(new Callback<Weatherapp>() {
            @Override
            public void onResponse(Call<Weatherapp> call, Response<Weatherapp> response) {
                getDataList(response.body().getList());
            }

            @Override
            public void onFailure(Call<Weatherapp> call, Throwable t) {

            }
        });
    }

    private void gettingDataFromDatabase(){


        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        CustomAdapter customAdapter = new CustomAdapter(this,getAllItems());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(customAdapter);

    }

    private void getDataList(List<com.example.shuvo.weatherapp.modelclass.List> position){

        for(int i = 0; i<position.size(); i++){

            ContentValues cv = new ContentValues();
            cv.put(WeatherContent.WeatherEntry.CITY_NAME, position.get(i).getName());
            cv.put(WeatherContent.WeatherEntry.TEMPERATURE, position.get(i).getMain().getTemp().toString());
            cv.put(WeatherContent.WeatherEntry.HUMIDITY, position.get(i).getMain().getHumidity().toString());
            cv.put(WeatherContent.WeatherEntry.PREASSURE,position.get(i).getMain().getPressure().toString());
            cv.put(WeatherContent.WeatherEntry.WIND, position.get(i).getWind().getSpeed().toString());
            cv.put(WeatherContent.WeatherEntry.CLOUD, position.get(i).getClouds().getAll().toString());

            List<Weather> weathers = position.get(i).getWeather();

            for(int j = 0; j<weathers.size();j++) {
                cv.put(WeatherContent.WeatherEntry.MAIN, position.get(i).getWeather().get(j).getMain());
                cv.put(WeatherContent.WeatherEntry.DESCRIPTION, position.get(i).getWeather().get(j).getDescription());
            }


            mDatabase.insert(WeatherContent.WeatherEntry.TABLE_NAME, null, cv);

        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        CustomAdapter customAdapter = new CustomAdapter(this,getAllItems());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(customAdapter);
    }

    private Cursor getAllItems() {
        return mDatabase.query(
                WeatherContent.WeatherEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                WeatherContent.WeatherEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }

}


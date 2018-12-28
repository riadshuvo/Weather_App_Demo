package com.example.shuvo.weatherapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WeatherDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "news.db";
    public static final int DATABASE_VERSION = 1;

    public WeatherDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_WEATHER_TABLE = "CREATE TABLE " +
                WeatherContent.WeatherEntry.TABLE_NAME + " (" +
                WeatherContent.WeatherEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WeatherContent.WeatherEntry.CITY_NAME + " TEXT, " +
                WeatherContent.WeatherEntry.TEMPERATURE + " TEXT, " +
                WeatherContent.WeatherEntry.HUMIDITY + " TEXT," +
                WeatherContent.WeatherEntry.PREASSURE + " TEXT, " +
                WeatherContent.WeatherEntry.WIND + " TEXT, " +
                WeatherContent.WeatherEntry.CLOUD + " TEXT," +
                WeatherContent.WeatherEntry.MAIN + " TEXT, " +
                WeatherContent.WeatherEntry.DESCRIPTION + " TEXT," +
                WeatherContent.WeatherEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_WEATHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + WeatherContent.WeatherEntry.TABLE_NAME);
        onCreate(db);

    }
}

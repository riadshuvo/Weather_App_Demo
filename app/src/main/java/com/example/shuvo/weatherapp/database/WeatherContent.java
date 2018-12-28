package com.example.shuvo.weatherapp.database;

import android.provider.BaseColumns;

public class WeatherContent {

    public WeatherContent(){

    }

    public static final class WeatherEntry implements BaseColumns {

        public static final String TABLE_NAME = "weather";
        public static final String CITY_NAME = "cityname";
        public static final String TEMPERATURE = "temperature";
        public static final String HUMIDITY = "humidity";
        public static final String PREASSURE = "preassure";
        public static final String WIND = "wind";
        public static final String CLOUD = "cloud";
        public static final String MAIN = "main";
        public static final String DESCRIPTION = "decription";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}

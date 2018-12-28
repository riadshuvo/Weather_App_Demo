package com.example.shuvo.weatherapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shuvo.weatherapp.R;
import com.example.shuvo.weatherapp.database.WeatherContent;
import com.example.shuvo.weatherapp.modelclass.Weather;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    List<com.example.shuvo.weatherapp.modelclass.List> datalist;
    Context mContext;
    Cursor mCursor;

    public CustomAdapter(Context context, Cursor cursor){
        this.datalist = datalist;
        this.mContext = context;
        this.mCursor = cursor;

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weather_list, viewGroup, false);
        CustomAdapter.CustomViewHolder hold = new CustomAdapter.CustomViewHolder(view);
        return(hold);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int item) {

        if (!mCursor.moveToPosition(item)) {
            return;
        }

            holder.nameTextView.setText(mCursor.getString(mCursor.getColumnIndex(WeatherContent.WeatherEntry.CITY_NAME)));
            holder.tempTextView.setText(mCursor.getString(mCursor.getColumnIndex(WeatherContent.WeatherEntry.TEMPERATURE)));
            holder.pressureTextView.setText(mCursor.getString(mCursor.getColumnIndex(WeatherContent.WeatherEntry.PREASSURE)));
            holder.humadityTextView.setText(mCursor.getString(mCursor.getColumnIndex(WeatherContent.WeatherEntry.HUMIDITY)));
            holder.windTextView.setText(mCursor.getString(mCursor.getColumnIndex(WeatherContent.WeatherEntry.WIND)));
            holder.cloudTextView.setText(mCursor.getString(mCursor.getColumnIndex(WeatherContent.WeatherEntry.CLOUD)));

            holder.mainTextView.setText(mCursor.getString(mCursor.getColumnIndex(WeatherContent.WeatherEntry.MAIN)));
            holder.descriptionTextView.setText(mCursor.getString(mCursor.getColumnIndex(WeatherContent.WeatherEntry.DESCRIPTION)));

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, tempTextView, windTextView, cloudTextView, pressureTextView, humadityTextView, mainTextView, descriptionTextView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.cityText);
            tempTextView = itemView.findViewById(R.id.tempText);
            windTextView = itemView.findViewById(R.id.windTextId);
            cloudTextView = itemView.findViewById(R.id.cloudTextId);
            pressureTextView = itemView.findViewById(R.id.pressureTextId);
            humadityTextView = itemView.findViewById(R.id.humidTextId);
            mainTextView = itemView.findViewById(R.id.mainTextId);
            descriptionTextView = itemView.findViewById(R.id.decreptionTextId);
        }
    }


}

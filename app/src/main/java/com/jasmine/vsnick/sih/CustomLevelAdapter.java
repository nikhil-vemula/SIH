package com.jasmine.vsnick.sih;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.net.ConnectException;
import java.util.ArrayList;

/**
 * Created by vsnick on 31-03-2018.
 */

class CustomLevelAdapter extends ArrayAdapter<WaterLevel> {
    private ArrayList<WaterLevel> data;
    Context mContext;
    public CustomLevelAdapter(ArrayList<WaterLevel> data,Context context) {
        super(context,R.layout.waterlevel_item,data);
        this.data = data;
        this.mContext = context;
    }
    private static class ViewHolder {
        TextView levelTextview;
        TextView timeTextview;
    }

    private int lastPosition = -1;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        WaterLevel waterLevel = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.waterlevel_item, parent, false);
            viewHolder.levelTextview = convertView.findViewById(R.id.water_level);
            viewHolder.timeTextview = convertView.findViewById(R.id.water_level_time);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CustomLevelAdapter.ViewHolder) convertView.getTag();
        }


        viewHolder.levelTextview.setText(waterLevel.getLevel().toString());
        viewHolder.timeTextview.setText(waterLevel.getTime());

        // Return the completed view to render on screen
        return convertView;
    }
}

package com.jasmine.vsnick.sih;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by vsnick on 31-03-2018.
 */

class CustomAdapter extends ArrayAdapter<Structure>{

    private ArrayList<Structure> data;
    private static class ViewHolder {
        TextView deptTextview;
        TextView stateTextview;
        TextView statusTextview;
        ImageView typeImage;
    }
    Context mContext;
    public CustomAdapter(ArrayList<Structure> data, Context context) {
        super(context,R.layout.structure_item,data);
        this.data = data;
        this.mContext = context;
    }

    private int lastPosition = -1;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Structure structure = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.structure_item, parent, false);
            viewHolder.deptTextview = (TextView) convertView.findViewById(R.id.dept_text_view);
            viewHolder.statusTextview = (TextView) convertView.findViewById(R.id.status_text_view);
            viewHolder.stateTextview = (TextView) convertView.findViewById(R.id.state_text_view);
            viewHolder.typeImage = (ImageView) convertView.findViewById(R.id.structure_image_view);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.deptTextview.setText("Type: "+ structure.getType());
        viewHolder.stateTextview.setText("State: "+ structure.getState());
        viewHolder.statusTextview.setText("Status: "+structure.getStatus());
        if(structure.getType().equals("tube-well")){
            viewHolder.typeImage.setImageResource(mContext.getResources().getIdentifier("tubewell","drawable",mContext.getPackageName()));
        }else if(structure.getType().equals("check-dam")){
            viewHolder.typeImage.setImageResource(mContext.getResources().getIdentifier("checkdam","drawable",mContext.getPackageName()));
        }else if(structure.getType().equals("percolation")){
            viewHolder.typeImage.setImageResource(mContext.getResources().getIdentifier("percolation","drawable",mContext.getPackageName()));
        }
        else {
            Glide.with(mContext).load("https://api.androidhive.info/images/glide/medium/deadpool.jpg")
                    .override(200, 200)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(viewHolder.typeImage);
        }
        String status = structure.getStatus();
        Log.d("My Fragment",status);
        if(status.equals("on-going")){
            viewHolder.statusTextview.setTextColor(Color.MAGENTA);
        }else if(status.equals("completed")){
            viewHolder.statusTextview.setTextColor(Color.GREEN);
        }else if(status.equals("proposed")){
            viewHolder.statusTextview.setTextColor(Color.BLUE);
        }else{
            viewHolder.statusTextview.setTextColor(Color.RED);
        }
        // Return the completed view to render on screen
        return convertView;
    }
}

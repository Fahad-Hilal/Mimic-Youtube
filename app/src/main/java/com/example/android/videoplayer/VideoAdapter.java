package com.example.android.videoplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hp on 30/12/2016.
 */

public class VideoAdapter extends ArrayAdapter<Video> {
    //Context context;
    public VideoAdapter( Context context, List<Video> videos)
    {
        super(context,0,videos);
    }



    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.video_list_item,parent,false);
        }
        Video currentVideo=getItem(position);
        TextView titleView=(TextView) listItemView.findViewById(R.id.title);
        titleView.setText(currentVideo.getmTitle());
        ImageView thumbnail=(ImageView)listItemView.findViewById(R.id.thumbnail);
        //thumbnail.setImageBitmap(currentVideo.getmThumbnail());
        //Picasso.with(getContext()).load(currentVideo.getmThumbnail()).into(thumbnail);
        Picasso.with(getContext())
                .load(currentVideo.getmThumbnail())
                .resize(320, 180)
                .centerCrop()
                .into(thumbnail);
//        TextView channel=(TextView)listItemView.findViewById(R.id.channel);
//        channel.setText(currentVideo.getmChannel());






        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }



}

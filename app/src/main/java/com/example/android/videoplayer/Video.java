package com.example.android.videoplayer;

import android.graphics.Bitmap;

/**
 * Created by hp on 30/12/2016.
 */

public class Video {
    String mVideoId;
    String mTitle;
    String mThumbnail;
    String mDescription;
    String mChannel;


    Video(String videoId,String title,String thumbnail,String description,String channel){
        mVideoId=videoId;
        mTitle=title;
        mThumbnail=thumbnail;
        mDescription=description;
        mChannel=channel;
    }
    public String getmVideoId()
    {
        return mVideoId;
    }
    public String getmTitle()
    {
        return mTitle;
    }
    public String getmDescription()
    {
        return mDescription;
    }
    public String getmThumbnail()
    {return mThumbnail;}
    public String getmChannel()
    {
        return mChannel;
    }
}

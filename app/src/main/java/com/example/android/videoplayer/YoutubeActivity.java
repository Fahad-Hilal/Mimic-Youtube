package com.example.android.videoplayer;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class YoutubeActivity extends YouTubeBaseActivity {
    static YouTubePlayerView youTubePlayerView;
    //Button play;

    static YouTubePlayer.OnInitializedListener onInitializedListener;
    //public static final String LOG_TAG = YoutubeActivity.class.getName();
     String videoId;
     String title;
     String description;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        youTubePlayerView=(YouTubePlayerView) findViewById(R.id.youtube_view);
       // play=(Button)findViewById(R.id.button);

         videoId = getIntent().getStringExtra("Value");
        title=getIntent().getStringExtra("title");
        description=getIntent().getStringExtra("description");




       onInitializedListener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoId);
                TextView titleView=(TextView)findViewById(R.id.title);
                TextView descriptionView=(TextView)findViewById(R.id.description);
                titleView.setText(title);
                descriptionView.setText(description);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
//        play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                youTubePlayerView.initialize(PlayerConfig.API_KEY,onInitializedListener);
//            }
//        });


        youTubePlayerView.initialize(PlayerConfig.API_KEY,onInitializedListener);











    }








}


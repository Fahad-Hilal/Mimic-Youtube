package com.example.android.videoplayer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by hp on 05/01/2017.
 */

public class HomeActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button videos=(Button)findViewById(R.id.videos_ac);
        videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);

            }
        });
        Button recent=(Button)findViewById(R.id.recent);
        recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, NewActivity.class);
                startActivity(i);

            }
        });

        ImageView imageView=(ImageView)findViewById(R.id.image);

//        Picasso.with(this).load("http://cdn.digitalsport.co/wp-content/uploads/2014/08/Full-Time-Devils.png").into(imageView);
//        Picasso.with(this)
//                .load("http://cdn.digitalsport.co/wp-content/uploads/2014/08/Full-Time-Devils.png")
//               .resize(1100, 500)
////                .centerCrop()
//                .into(imageView);
        final TextView website=(TextView)findViewById(R.id.website);
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = website.getText().toString();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });



    }
}

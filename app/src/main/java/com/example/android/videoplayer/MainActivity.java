package com.example.android.videoplayer;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.videoplayer.QueryUtils;
import com.example.android.videoplayer.Video;
import com.example.android.videoplayer.VideoAdapter;
import com.example.android.videoplayer.YoutubeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.android.videoplayer.QueryUtils.nextPageToken;
import static com.example.android.videoplayer.QueryUtils.prevPageToken;
import static com.example.android.videoplayer.R.layout.search;

/**
 * Created by hp on 30/12/2016.
 */

public class MainActivity extends AppCompatActivity {

    private  String REQUEST_URL="https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=50&orderby=uploads&channelId=UC7w8GnTF2Sp3wldDMtCCtVw&key=AIzaSyCXSbyQL9CHDnBMKgsfnVNvdHl1cPz8kns";



   public VideoAdapter mAdapter;


    //public static final String LOG_TAG = com.example.android.videoplayer.Main_Display.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView listView = (ListView) findViewById(R.id.list);
        mAdapter = new VideoAdapter(this,new ArrayList<Video>());
        listView.setAdapter(mAdapter);
        listView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        final VideoAsyncTask task=new VideoAsyncTask();
        task.execute(REQUEST_URL);


        //going back to home
        Button home=(Button)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });
        Button recent=(Button)findViewById(R.id.recent);
        recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewActivity.class);
                startActivity(i);

            }
        });

        //inserting edit text into action bar

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setIcon(R.drawable.ic_search_white_18dp);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(search, null);
        actionBar.setCustomView(v);
        final EditText search=(EditText)findViewById(R.id.search_query);
        search.setVisibility(View.INVISIBLE);
        final ImageView searchIcon=(ImageView)findViewById(R.id.action_search);
        searchIcon.setImageResource(R.drawable.ic_search_white_18dp );
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setVisibility(View.VISIBLE);


            }
        });



        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String q=search.getText().toString();
                    String query=q.replace(" ","+");
                    if(query!=null||query.length()!=0)

                        new VideoAsyncTask().execute(REQUEST_URL+"&q="+query);
                    query=null;

                    return true;
                }
                return false;
            }
        });



//Alternative method
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String q=search.getText().toString();
//                 String query=q.replace(" ","+");
//                if(query!=null)
//
//                    new VideoAsyncTask().execute(REQUEST_URL+"&q="+query);
//            query=null;
//            }
//
//        });












        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Video currentVideo = mAdapter.getItem(position);
                Intent i = new Intent(MainActivity.this, YoutubeActivity.class);
                try {


                    //getJson(json);
                    i.putExtra("Value", currentVideo.getmVideoId());
                    i.putExtra("title",currentVideo.getmTitle());
                    i.putExtra("description",currentVideo.getmDescription());
                    startActivity(i);
                } catch (NullPointerException ne) {

                }

            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if((firstVisibleItem + visibleItemCount) ==  (totalItemCount)&&firstVisibleItem!=0)
                {
                    int a=firstVisibleItem;
                    int b=visibleItemCount;
                    int c=totalItemCount;
                    VideoAsyncTask task1=new VideoAsyncTask();

                    task1.execute(REQUEST_URL+"&pageToken="+ QueryUtils.nextPageToken);
                }

//                else if(firstVisibleItem==0&&prevPageToken!=null){
//                    VideoAsyncTask task1=new VideoAsyncTask();
//
//                    task1.execute(REQUEST_URL+"&pageToken="+prevPageToken);
//                }


            }
        });

        // Create a new {@link ArrayAdapter} of earthquakes


    }
//    private ArrayList<Video> extractVideo()
//    {
//        ArrayList<Video>videos=new ArrayList<Video>();
//
//        try
//        {
//            JSONObject baseJsonObject=new JSONObject(json);
//            JSONArray items=baseJsonObject.getJSONArray("items");
//            for(int i=0;i<items.length();i++)
//            {
//                JSONObject item=items.getJSONObject(i);
//                JSONObject id=item.getJSONObject("id");
//                String videoId=id.getString("videoId");
//                JSONObject snippet=item.getJSONObject("snippet");
//                //String description=snippet.getString("description");
//                String title=snippet.getString("title");
//                videos.add(new Video(videoId,title));
//
//
//
//
//            }
//        }
//        catch(JSONException je)
//        {
//            Log.e(LOG_TAG,"Problem parsing JSON",je);
//
//        }
//        return videos;
//    }


//    public void getJson(String videoJson) {
//        //ArrayList<Video> videos = new ArrayList<Video>();
//
//        // Create an empty ArrayList that we can start adding earthquakes to
//
//        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
//        // is formatted, a JSONException exception object will be thrown.
//        // Catch the exception so the app doesn't crash, and print the error message to the logs.
//        try {
//
//            JSONObject baseJasonObject = new JSONObject(videoJson);
//            JSONArray VideoArray = baseJasonObject.optJSONArray("items");
//            for (int i = 0; i < VideoArray.length(); i++) {
//                JSONObject currentItem = VideoArray.optJSONObject(i);
//                JSONObject properties = currentItem.getJSONObject("id");
//                String id = properties.getString("videoId");
//                JSONObject snippet = currentItem.getJSONObject("snippet");
//                JSONObject thumbnails = snippet.getJSONObject("thumbnails");
//                JSONObject def = thumbnails.getJSONObject("default");
//                String url = def.getString("url");
//                String title = (snippet.getString("title"));
//                videos.add(new Video(id,title,url));
//
//                //videos.add(new Video(id,title,url));
//               // DownloadImageTask task = new DownloadImageTask(id, title);
//                //task.execute(url);
//            }
//
//            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
//            // build up a list of Earthquake objects with the corresponding data.
//
//        } catch (JSONException e) {
//            // If an error is thrown when executing any of the above statements in the "try" block,
//            // catch the exception here, so the app doesn't crash. Print a log message
//            // with the message from the exception.
//            Log.e("QueryUtils", "Problem parsing theVideo JSON results", e);
//        }

    // Return the list of earthquakes
    //mAdapter.addAll(videos);


    //  }
    public class VideoAsyncTask extends AsyncTask<String, Void, ArrayList<Video>> {
        @Override
        protected ArrayList<Video> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            ArrayList<Video> videos = QueryUtils.fetchEarthquakeData(urls[0]);


            return videos;

        }

        @Override
        protected void onPostExecute(final ArrayList<Video> data) {
            //Clear the adapter of any data
            mAdapter.clear();
            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);

            }
        }
    }


}







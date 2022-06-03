package com.example.flixter;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixter.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import okhttp3.Headers;

public class MovieTrailerActivity extends YouTubeBaseActivity {

//    public static String getVideoId() {
//        return videoId;
//    }
//
//    public static String videoId;
    final String GOOGLE_API_KEY = BuildConfig.GOOGLE_APIKEY;
    public static final String MOVIE_API_KEY = BuildConfig.MOVIEDB_APIKEY;

//    public static final String VIDEOS_PLAYING = String.format("https://api.themoviedb.org/3/movie/{movie_id}/videos?api_key=%s&language=en-US", API_KEY);
    Movie movie ;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailer);

//
//        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
//        Log.d("MovieTrailerActivity", String.format("showing details for '%s'", movie.getTitle()));
//        id = movie.getId();
//
//        String VIDEOS_PLAYING = String.format("https://api.themoviedb.org/3/movie/%f/videos?api_key=%s&language=en-US", id, API_KEY);
//        Log.d("MovieTrailerActivity", String.format("videos playing url is '%s'", VIDEOS_PLAYING));

//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get(VIDEOS_PLAYING, new JsonHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Headers headers, JSON json) {
//                Log.d("MovieTrailerActivity", "onSucess");
//                JSONObject jsonObject = json.jsonObject;
//                try {// key may not exist ! so u need to try catch :DD
//                    JSONArray results = jsonObject.getJSONArray("results");
//                    Log.i("MovieTrailerActivity", "Results:" + results);
//                    JSONObject firstVid = results.getJSONObject(0);
//                    videoId = firstVid.getString("key");
//
//                } catch (JSONException e) {
//                    Log.e("MovieTrailerActivity", "Hit json exception", e);
////                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
//
//            }
//        });


        //temp test video id ------
        //REPLACE W MOVIE TRAILER VIDEO ID!!!!
        final String videoId = "tKodtNFpzBA";

        // resolve the player view from the layout
        YouTubePlayerView playerView = (YouTubePlayerView) findViewById(R.id.ytVideo);

        //initialize with API key
        playerView.initialize(GOOGLE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                //cue video play, play video, etec
                youTubePlayer.cueVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.e("MovieTrailerActivity", youTubeInitializationResult.toString());
            }
        });
    }

}

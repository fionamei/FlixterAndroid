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


    public static final String TAG = "movietraileractivity";
    public static final String YOUTUBEKEY = BuildConfig.GOOGLE_APIKEY;
    public static final String API_KEY = BuildConfig.MOVIEDB_APIKEY;
    public static final String MOVIE_API = String.format("https://api.themoviedb.org/3/movie/338953/videos?api_key=%s", API_KEY);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailer);

        YouTubePlayerView playerView = (YouTubePlayerView) findViewById(R.id.player);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(MOVIE_API, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSucess");
                JSONObject jsonObject = json.jsonObject;

                JSONArray results = null;
                try {
                    results = jsonObject.getJSONArray("results"); // need trycatch bc it might not be a jsonarray
                    JSONObject firstObject = (JSONObject) results.get(0);
                    String movieID = firstObject.getString("key");
                    initializeYoutubePlayer(movieID, playerView);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });
    }

    private void initializeYoutubePlayer(String videoId, YouTubePlayerView playerView) {
        playerView.initialize(YOUTUBEKEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer youTubePlayer, boolean b) {
                // do any work here to cue video, play video, etc.
                youTubePlayer.cueVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult youTubeInitializationResult) {
                // log the error
                Log.e("MovieTrailerActivity", "Error initializing YouTube player");
            }
        });
    }

}
package com.example.flixter;

import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MovieTrailerActivity extends YouTubeBaseActivity {


    public static final String TAG = "movietraileractivity";
    public static final String YOUTUBEKEY = BuildConfig.GOOGLE_APIKEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailer);

        YouTubePlayerView playerView = (YouTubePlayerView) findViewById(R.id.player);
        String videoID = getIntent().getStringExtra("videoID");
        initializeYoutubePlayer(videoID, playerView);
    }

    private void initializeYoutubePlayer(String videoId, YouTubePlayerView playerView) {
        playerView.initialize(YOUTUBEKEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer youTubePlayer, boolean b) {
                // do any work here to cue video, play video, etc.
                youTubePlayer.cueVideo(videoId);
                youTubePlayer.play();
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
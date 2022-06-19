package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixter.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import okhttp3.Headers;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie;
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    ImageView ivPoster;
    MovieClient client;
    Context context;
    String videoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        //resolve the view objects

        client = new MovieClient();
        context = this;
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));

        initViews();
        populateViews();
        listenerSetup();
    }

    private void initViews() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
        ivPoster = (ImageView) findViewById(R.id.ivPoster);
    }

    private void populateViews() {
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        String imageUrl = movie.getBackdropPath();
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.flicks_movie_placeholder)
//                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(radius)))
                .centerCrop()
                .transform(new RoundedCorners(30))
                .into(ivPoster);

        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage / 2.0f);
    }

    private void listenerSetup() {
        ivPoster.setClickable(true);
        ivPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                client.getMovieID(movie.getId().toString(), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        JSONArray results; // need trycatch bc it might not be a jsonarray
                        try {
                            results = json.jsonObject.getJSONArray("results");
                            JSONObject firstObject = (JSONObject) results.get(0);
                            videoID = firstObject.getString("key");
                            Intent intent = new Intent(context, MovieTrailerActivity.class);
                            intent.putExtra("videoID", videoID);
                            context.startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                        Log.e("moviedetail", throwable.toString());
                    }
                });
            }
        });
    }

}
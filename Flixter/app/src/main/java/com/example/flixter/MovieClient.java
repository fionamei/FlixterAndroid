package com.example.flixter;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

public class MovieClient extends AsyncHttpClient {

    public static final String API_KEY = BuildConfig.MOVIEDB_APIKEY;
    public static final String NOW_PLAYING_URL = String.format("https://api.themoviedb.org/3/movie/now_playing?api_key=%s", API_KEY);
//    public static final String MOVIE_API = String.format("https://api.themoviedb.org/3/movie/338953/videos?api_key=%s", API_KEY);

    public MovieClient() {
        super();
    }

    public void getMovies(JsonHttpResponseHandler handler) {
        get(NOW_PLAYING_URL, handler);
    }

    public void getMovieID(String id, JsonHttpResponseHandler handler) {
        String MOVIE_API = String.format("https://api.themoviedb.org/3/movie/%s/videos?api_key=%s", id, API_KEY);
        get(MOVIE_API, handler);

    }

}

package com.example.h071211071_finalmobile.networking;

import com.example.h071211071_finalmobile.DataResponse.MovieDataResponse;
import com.example.h071211071_finalmobile.DataResponse.TvDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;

//www.finalproject.com
//https://www.themoviedb.org/settings/api/details

public interface TMDBApiService {
    @GET("movie/now_playing?api_key=10197d5e4dd0d3a0907210b4ad524097")
    Call<MovieDataResponse> getNowPlaying();

    @GET("tv/airing_today?api_key=10197d5e4dd0d3a0907210b4ad524097")
    Call<TvDataResponse> getAiringToday();
}

package com.example.h071211071_finalmobile.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import com.example.h071211071_finalmobile.R;
import com.example.h071211071_finalmobile.adapter.MovieAdapter;
import com.example.h071211071_finalmobile.model.ModelMovie;
import com.example.h071211071_finalmobile.networking.ApiResponse;
import com.example.h071211071_finalmobile.networking.TMDBApiService;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MovieFragment extends Fragment {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    String tag = MovieFragment.class.getSimpleName();
    MovieAdapter adapter;
    private Retrofit retrofit;
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            progressBar.setVisibility(View.INVISIBLE);
        }, 1000);

        recyclerView = view.findViewById(R.id.rvMovie);
        connectAndGetApiData();
    }

    public void connectAndGetApiData() {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        TMDBApiService tmdbApiService = retrofit.create(TMDBApiService.class);
        Call<ApiResponse> call = tmdbApiService.getNowPlaying();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: CEK");
                    if (response.body().getModelMovie() != null) {
                        List<ModelMovie> movies = response.body().getModelMovie();
                        for (int i = 0; i <movies.size() ; i++) {
                            System.out.println(movies.get(i).getOriginalTitle());
                        }
                            adapter = new MovieAdapter(movies);
                        recyclerView.setAdapter(adapter);
                    }
                } else {
                    Log.d(TAG, "onResponse: Failed To Fetch Data1");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d(TAG, "onResponse: Failed To Fetch Data2");
            }
        });
    }
}
//        call.enqueue(new Callback<ApiResponse>() {
//            @Override
//            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                List<ModelMovie> modelMovie = response.body().getModelMovie();
//                recyclerView.setAdapter(new MovieAdapter(modelMovie, R.layout.list_item_movie, getActivity().getApplicationContext()));
//                Log.d(TAG, "Jumlah film: " + modelMovie.size());
//            }

//            @Override
//            public void onFailure(Call<ApiResponse> call, Throwable t) {
//                Log.e(TAG, t.toString());
//            }
//        });

//        adapter = new MovieAdapter();
//        recyclerView.setAdapter(adapter);

//        Retrofit retrofit = new Retrofit
//                .Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        return retrofit.create(TMDBApiService.class);

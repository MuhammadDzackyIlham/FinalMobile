package com.example.h071211071_finalmobile.fragment;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import com.example.h071211071_finalmobile.R;
import com.example.h071211071_finalmobile.activity.DetailMovieActivity;
import com.example.h071211071_finalmobile.adapter.MovieAdapter;
import com.example.h071211071_finalmobile.model.ModelMovie;
import com.example.h071211071_finalmobile.DataResponse.MovieDataResponse;
import com.example.h071211071_finalmobile.networking.TMDBApiService;

import org.jetbrains.annotations.Nullable;

import java.util.List;


public class MovieFragment extends Fragment implements MovieAdapter.OnMovieListener {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    String tag = MovieFragment.class.getSimpleName();
    MovieAdapter adapter;
    private Retrofit retrofit;
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.rvMovie);

        connectAndGetApiData();
    }

    public void connectAndGetApiData() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TMDBApiService tmdbApiService = retrofit.create(TMDBApiService.class);
        Call<MovieDataResponse> call = tmdbApiService.getNowPlaying();
        call.enqueue(new Callback<MovieDataResponse>() {
            @Override
            public void onResponse(Call<MovieDataResponse> call, Response<MovieDataResponse> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.d(TAG, "onResponse: CEK");
                    if (response.body().getModelMovie() != null) {
                        List<ModelMovie> movies = response.body().getModelMovie();
                        for (int i = 0; i < movies.size(); i++) {
                            System.out.println("Movie Star: " + movies.get(i).getVoteAverage());
                        }
                        adapter = new MovieAdapter(movies, MovieFragment.this);
                        recyclerView.setAdapter(adapter);
                    }
                } else {
                    Log.d(TAG, "onResponse: Failed To Fetch Data1");
                }
            }

            @Override
            public void onFailure(Call<MovieDataResponse> call, Throwable t) {
                Log.d(TAG, "onResponse: Failed To Fetch Data2");
            }
        });
    }

    @Override
    public void onMovieClick(int position) {
        ModelMovie clickedMovie = adapter.getSelectedMovie(position);

        Intent intent = new Intent(getActivity(), DetailMovieActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailMovieActivity.EXTRA_MOVIE, clickedMovie);
        intent.putExtras(bundle);
        startActivity(intent);

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

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

import com.example.h071211071_finalmobile.R;
import com.example.h071211071_finalmobile.adapter.TvAdapter;
import com.example.h071211071_finalmobile.model.ModelTv;
import com.example.h071211071_finalmobile.networking.ApiResponse;
import com.example.h071211071_finalmobile.networking.TMDBApiService;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TvFragment extends Fragment {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    private Retrofit retrofit;
    TvAdapter adapter;
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar);

        Handler handler = new Handler();
        handler.postDelayed(() ->{
            progressBar.setVisibility(View.INVISIBLE);
        },1000);

        recyclerView = view.findViewById(R.id.rvTv);
        connectAndGetApiData();
    }

    private void connectAndGetApiData() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TMDBApiService tmdbApiService = retrofit.create(TMDBApiService.class);
        Call<ApiResponse> call = tmdbApiService.getAiringToday();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getModelTv() != null) {
                        List<ModelTv> tvs = response.body().getModelTv();
                        adapter = new TvAdapter(tvs);
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
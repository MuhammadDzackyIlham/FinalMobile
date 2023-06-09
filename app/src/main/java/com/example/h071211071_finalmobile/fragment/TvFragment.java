package com.example.h071211071_finalmobile.fragment;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.h071211071_finalmobile.DataResponse.TvDataResponse;
import com.example.h071211071_finalmobile.R;
import com.example.h071211071_finalmobile.activity.DetailMovieActivity;
import com.example.h071211071_finalmobile.activity.DetailTvActivity;
import com.example.h071211071_finalmobile.adapter.TvAdapter;
import com.example.h071211071_finalmobile.model.ModelMovie;
import com.example.h071211071_finalmobile.model.ModelTv;
import com.example.h071211071_finalmobile.networking.TMDBApiService;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TvFragment extends Fragment implements TvAdapter.OnMovieListener{

    ProgressBar progressBar;
    RecyclerView recyclerView;
    private Retrofit retrofit;
    TvAdapter adapter;
    ModelTv modelTv;

    ConstraintLayout constraintLayout;
    List<TvDataResponse> dataResponses;
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar);
        constraintLayout =  view.findViewById(R.id.layoutTv);
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
        Call<TvDataResponse> call = tmdbApiService.getAiringToday();
        System.out.println("2");

        call.enqueue(new Callback<TvDataResponse>() {
            @Override
            public void onResponse(Call<TvDataResponse> call, Response<TvDataResponse> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.d(TAG, "onResponse: CEK" + response.body().getModelTv());
                    if (response.body().getModelTv() != null) {
                        List<ModelTv> tvList = response.body().getModelTv();
                        for (int i = 0; i <tvList.size() ; i++) {
                            System.out.println(tvList.get(i).getOriginalTitle());
                        }
                        System.out.println("1");
                        adapter = new TvAdapter(tvList, TvFragment.this);
                        System.out.println(tvList);
                        recyclerView.setAdapter(adapter);
                    }
                } else {
                    Log.d(TAG, "onResponse: Failed To Fetch Data1");
                }
            }

            @Override
            public void onFailure(Call<TvDataResponse> call, Throwable t) {
                Log.d(TAG, "onResponse: Failed To Fetch Data2");
            }
        });
    }

    @Override
    public void onMovieClick(int position) {
        ModelTv clickedMovie = adapter.getSelectedTv(position);

        Intent intent = new Intent(getActivity(), DetailTvActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailTvActivity.EXTRA_TV, clickedMovie);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}
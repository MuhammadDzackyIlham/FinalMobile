package com.example.h071211071_finalmobile.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.h071211071_finalmobile.R;
import com.example.h071211071_finalmobile.activity.DetailMovieActivity;
import com.example.h071211071_finalmobile.activity.DetailTvActivity;
import com.example.h071211071_finalmobile.adapter.FavoritMovieAdapter;
import com.example.h071211071_finalmobile.adapter.FavoritTvAdapter;
import com.example.h071211071_finalmobile.adapter.MovieAdapter;
import com.example.h071211071_finalmobile.adapter.OnMovieListener;
import com.example.h071211071_finalmobile.adapter.TvAdapter;
import com.example.h071211071_finalmobile.database.DatabaseHelperMovie;
import com.example.h071211071_finalmobile.database.DatabaseHelperTv;
import com.example.h071211071_finalmobile.model.ModelMovie;
import com.example.h071211071_finalmobile.model.ModelTv;

import java.util.List;

public class FavoriteFragment extends Fragment {
    RecyclerView rvFavoriteMovie, rvFavoritTv;
    FavoritMovieAdapter movieAdapter;
    FavoritTvAdapter tvAdapter;
    DatabaseHelperMovie databaseHelperMovie;
    DatabaseHelperTv databaseHelperTv;



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("FavoriteFragment", "onViewCreated: " + databaseHelperMovie.getFavoriteMovies().size());
        Log.d("FavoriteFragment", "onViewCreated: " + databaseHelperTv.getFavoriteTvs().size());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        // Inisialisasi databaseHelperMovie dan databaseHelperTv
        databaseHelperMovie = new DatabaseHelperMovie(getActivity());
        databaseHelperTv = new DatabaseHelperTv(getActivity());

        rvFavoriteMovie = view.findViewById(R.id.rvFavourite);
        rvFavoritTv = view.findViewById(R.id.rvFavourite2);

        // Set layout manager untuk rvFavoriteMovie
        LinearLayoutManager movieLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvFavoriteMovie.setLayoutManager(movieLayoutManager);

        // Inisialisasi dan set adapter untuk rvFavoriteMovie
        List<ModelMovie> favoriteMovies = databaseHelperMovie.getFavoriteMovies();
        movieAdapter = new FavoritMovieAdapter(favoriteMovies, this);
        rvFavoriteMovie.setAdapter(movieAdapter);

        // Set layout manager untuk rvFavoritTv
        LinearLayoutManager tvLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvFavoritTv.setLayoutManager(tvLayoutManager);

        // Inisialisasi dan set adapter untuk rvFavoritTv
        List<ModelTv> favoriteTvs = databaseHelperTv.getFavoriteTvs();
        tvAdapter = new FavoritTvAdapter(favoriteTvs, this);
        rvFavoritTv.setAdapter(tvAdapter);

        return view;
    }
}


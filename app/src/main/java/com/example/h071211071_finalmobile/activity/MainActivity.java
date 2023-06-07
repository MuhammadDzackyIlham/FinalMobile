package com.example.h071211071_finalmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.h071211071_finalmobile.R;
import com.example.h071211071_finalmobile.fragment.FavoriteFragment;
import com.example.h071211071_finalmobile.fragment.MovieFragment;
import com.example.h071211071_finalmobile.fragment.TvFragment;

public class MainActivity extends AppCompatActivity {

    ImageView movie, tv, favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movie = findViewById(R.id.btn_movie);
        tv = findViewById(R.id.btn_tv);
        favorite = findViewById(R.id.btn_favorite);

        FragmentManager fragmentManager = getSupportFragmentManager();
        MovieFragment movieFragment = new MovieFragment();
        TvFragment tvFragment = new TvFragment();
        FavoriteFragment favoriteFragment = new FavoriteFragment();

        Fragment fragment1 = fragmentManager.findFragmentByTag(MovieFragment.class.getSimpleName());
        Fragment fragment2 = fragmentManager.findFragmentByTag(TvFragment.class.getSimpleName());
        Fragment fragment3 = fragmentManager.findFragmentByTag(FavoriteFragment.class.getSimpleName());

        if (!(fragment1 instanceof MovieFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_layout, movieFragment,
                            MovieFragment.class.getSimpleName())
                    .commit();
        }

        movie.setOnClickListener(v -> {
            if (!(fragment1 instanceof MovieFragment)) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, movieFragment,
                                MovieFragment.class.getSimpleName())
                        .commit();
            }
        });

        tv.setOnClickListener(v -> {
            if (!(fragment2 instanceof TvFragment)) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, tvFragment,
                                TvFragment.class.getSimpleName())
                        .commit();
            }

        });

        favorite.setOnClickListener(v -> {
            if (!(fragment3 instanceof FavoriteFragment)) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, favoriteFragment,
                                FavoriteFragment.class.getSimpleName())
                        .commit();
            }
        });

    }
}
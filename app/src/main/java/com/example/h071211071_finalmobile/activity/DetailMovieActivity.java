package com.example.h071211071_finalmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.h071211071_finalmobile.R;
import com.example.h071211071_finalmobile.fragment.MovieFragment;
import com.example.h071211071_finalmobile.fragment.TvFragment;

public class DetailMovieActivity extends AppCompatActivity {

    private ImageView back, posterPathMovie, backdroPathMovie;
    private String title, overview, releaseDate, posterPath, backdropPath, voteAverage;
    int id;

    private TextView titleMovie, overviewMovie, releaseDateMovie, ratingMovie;

    public static final String IMAGE_URL_BASE_PATH = "https://image.tmdb.org/t/p/original";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        titleMovie = findViewById(R.id.tittleDetailMovie);
        releaseDateMovie = findViewById(R.id.releaseDateDetailMovie);
        overviewMovie = findViewById(R.id.tvOverviewDetail);
        posterPathMovie = findViewById(R.id.imgDetailMovie);
        backdroPathMovie = findViewById(R.id.imageView);
        back = findViewById(R.id.btn_backMovie);
        ratingMovie = findViewById(R.id.textViewRating);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            title = bundle.getString("title");
            overview = bundle.getString("overview");
            releaseDate = bundle.getString("release_date");
            posterPath = bundle.getString("poster_path");
            backdropPath = bundle.getString("backdrop_path");
            voteAverage = bundle.getString("vote_average");
            id = bundle.getInt("id");

            Log.d("MovieFragment", "onMovieClick: " + posterPath);
            Log.d("MovieFragment", "VOTE AVERAGE  " + voteAverage);

            titleMovie.setText(title);
            releaseDateMovie.setText(releaseDate);
            ratingMovie.setText(voteAverage);
            overviewMovie.setText(overview);

            Glide.with(this)
                    .load(IMAGE_URL_BASE_PATH + posterPath)
                    .into(posterPathMovie);
            Glide.with(this)
                    .load(IMAGE_URL_BASE_PATH + backdropPath)
                    .into(backdroPathMovie);
        }

    }
}

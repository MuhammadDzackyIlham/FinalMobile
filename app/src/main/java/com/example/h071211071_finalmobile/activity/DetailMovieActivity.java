package com.example.h071211071_finalmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.h071211071_finalmobile.R;
import com.example.h071211071_finalmobile.database.DatabaseHelperMovie;
import com.example.h071211071_finalmobile.model.ModelMovie;
import com.example.h071211071_finalmobile.model.ModelTv;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra-movie";

    private DatabaseHelperMovie databaseHelperMovie;
    private ModelMovie modelMovie;
    private boolean isFavorite;

    private ImageView back, posterPathMovie, backdroPathMovie, addFavorite;
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
        addFavorite = findViewById(R.id.addfav);
        back = findViewById(R.id.btn_backMovie);

        databaseHelperMovie = new DatabaseHelperMovie(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(DetailMovieActivity.EXTRA_MOVIE)) {
            modelMovie = bundle.getParcelable(DetailMovieActivity.EXTRA_MOVIE);
        }

        ratingMovie = findViewById(R.id.textViewRating);

        back.setOnClickListener(view -> {
            setResult(RESULT_OK, getIntent());
            finish();
        });

        if (modelMovie != null) {
            title = modelMovie.getOriginalTitle();
            overview = modelMovie.getOverview();
            releaseDate = modelMovie.getReleaseDate();
            posterPath = modelMovie.getPosterPath();
            backdropPath = modelMovie.getBackdropPath();
            voteAverage = String.valueOf(modelMovie.getVoteAverage());
            id = Integer.parseInt(modelMovie.getId());


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

            isFavorite = databaseHelperMovie.isMovieFavorite(modelMovie.getId());
            updateFavoriteButton();

            addFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toggleFavorite();
                }
            });
        }
    }

    private void toggleFavorite() {
        if (isFavorite) {
            databaseHelperMovie.removeMovieFromFavorites(modelMovie.getId());
            Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
        } else {
            databaseHelperMovie.addMovieToFavorites(modelMovie);
            Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
        }

        isFavorite = !isFavorite;

        if (!isFavorite) {
            deleteMovie();
        }
        updateFavoriteButton();
    }

    private void deleteMovie() {
        boolean deleted = databaseHelperMovie.deleteMovie(modelMovie.getId());
        if (deleted) {
            Toast.makeText(this, "Movie deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to delete movie", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateFavoriteButton() {
        if (isFavorite) {
            addFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            addFavorite.setImageResource(R.drawable.ic_favorite_border);
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, getIntent());
        finish();
        super.onBackPressed();
    }
}

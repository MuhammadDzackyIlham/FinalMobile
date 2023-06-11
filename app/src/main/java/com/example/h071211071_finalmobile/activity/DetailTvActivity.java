package com.example.h071211071_finalmobile.activity;

import static com.example.h071211071_finalmobile.adapter.TvAdapter.IMAGE_URL_BASE_PATH;

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
import com.example.h071211071_finalmobile.database.DatabaseHelperTv;
import com.example.h071211071_finalmobile.model.ModelMovie;
import com.example.h071211071_finalmobile.model.ModelTv;

import java.util.List;

public class DetailTvActivity extends AppCompatActivity {

    public static final String EXTRA_TV = "extra-tv";

    private DatabaseHelperTv databaseHelperTv;
    private ModelTv modelTv;
    private boolean isFavorite;

    private ImageView back, addFavorite, backdropPathTv, posterPathTv;
    private String tittle, overview, releaseDate, posterPath, backdropPath, voteAverage;
    private int id;

    private TextView tittleTv, releaseDateTv, ratingTv, overviewTv;

    public static final String IMAGE_URL_BASE_PATH = "https://image.tmdb.org/t/p/original";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);

        back = findViewById(R.id.btn_backTv);
        addFavorite = findViewById(R.id.addfavTv);
        backdropPathTv = findViewById(R.id.imageView);
        posterPathTv = findViewById(R.id.imgDetailTv);
        tittleTv = findViewById(R.id.tittleDetailTv);
        releaseDateTv = findViewById(R.id.releaseDateDetailTv);
        ratingTv = findViewById(R.id.textViewRating);
        overviewTv = findViewById(R.id.tvOverviewDetail);

        databaseHelperTv = new DatabaseHelperTv(this);
        List<ModelTv> modelTvList = databaseHelperTv.getFavoriteTvs();
        Log.d("modelTvList", String.valueOf(modelTvList.size()));

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey(DetailTvActivity.EXTRA_TV)){
            modelTv = bundle.getParcelable(DetailTvActivity.EXTRA_TV);
        }

        ratingTv = findViewById(R.id.textViewRating);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if(modelTv != null){
            tittle = modelTv.getOriginalTitle();
            overview = modelTv.getOverview();
            releaseDate = modelTv.getReleaseDate();
            posterPath = modelTv.getPosterPath();
            backdropPath = modelTv.getBackdropPath();
            voteAverage = String.valueOf(modelTv.getVoteAverage());
            id = Integer.parseInt(modelTv.getId());
            //Log.d("id", String.valueOf(id));

            tittleTv.setText(tittle);
            releaseDateTv.setText(releaseDate);
            ratingTv.setText(voteAverage);
            overviewTv.setText(overview);

            Glide.with(this)
                    .load(IMAGE_URL_BASE_PATH + posterPath)
                    .into(posterPathTv);
            Glide.with(this)
                    .load(IMAGE_URL_BASE_PATH + backdropPath)
                    .into(backdropPathTv);

            isFavorite = databaseHelperTv.isTvFavorite(modelTv.getId());
            updateFavoriteButton();

            addFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleFavorite();
                }
            });
        }
    }

    private void toggleFavorite() {
        if (isFavorite) {
            databaseHelperTv.removeTvFromFavorites(modelTv.getId());
            Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
        } else {
            databaseHelperTv.addMovieToFavorites(modelTv);
            Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
        }

        isFavorite = !isFavorite;

        updateFavoriteButton();
    }

    private void deleteTv() {
        boolean deleted = databaseHelperTv.deleteTv(modelTv.getId());
        if (deleted) {
            Toast.makeText(this, "TV show deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to delete TV show", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateFavoriteButton() {
        if (isFavorite) {
            addFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            addFavorite.setImageResource(R.drawable.ic_favorite_border);
        }
    }
}

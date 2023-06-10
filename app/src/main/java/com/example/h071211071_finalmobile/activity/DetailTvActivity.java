package com.example.h071211071_finalmobile.activity;

import static com.example.h071211071_finalmobile.adapter.TvAdapter.IMAGE_URL_BASE_PATH;

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
import com.example.h071211071_finalmobile.model.ModelTv;

public class DetailTvActivity extends AppCompatActivity {

    ImageView back,addFav, backdropPathTv, posterPathTv ;
    String tittle,overview,releaseDate,posterPath,backdropPath,voteAverage;
    int id;
    TextView tittleTv,releaseDateTv,ratingTv,overviewTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);

        back = findViewById(R.id.btn_backTv);
//        addFav = findViewById(R.id.btn_addFavorite);
        backdropPathTv = findViewById(R.id.imgDetailTv);
        posterPathTv = findViewById(R.id.imgDetailTv);
        tittleTv =  findViewById(R.id.tittleDetailTv);
        releaseDateTv =  findViewById(R.id.releaseDateDetailTv);
        ratingTv = findViewById(R.id.textViewRating);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            tittle = bundle.getString("title");
            overview = bundle.getString("overview");
            releaseDate = bundle.getString("release_date");
            posterPath = bundle.getString("poster_path");
            backdropPath = bundle.getString("backdrop_path");
            voteAverage = bundle.getString("vote_average");
            id = bundle.getInt("id");

            Log.d("MovieFragment", "onMovieClick: " + posterPath);
            Log.d("MovieFragment", "onMovieClick: " + backdropPath);

            tittleTv.setText(tittle);
            releaseDateTv.setText(releaseDate);
            ratingTv.setText(voteAverage);
            overviewTv.setText(overview);

            Glide.with(this)
                    .load(IMAGE_URL_BASE_PATH + posterPath)
                    .into(posterPathTv);
            Glide.with(this)
                    .load(IMAGE_URL_BASE_PATH + backdropPath)
                    .into(posterPathTv);
        }

    }
}
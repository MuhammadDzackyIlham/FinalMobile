package com.example.h071211071_finalmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.h071211071_finalmobile.R;
import com.example.h071211071_finalmobile.fragment.TvFragment;
import com.example.h071211071_finalmobile.model.ModelTv;

public class DetailTvActivity extends AppCompatActivity {

    ImageView back, addFav, backdropPath, posterPath ;
//    ImageView iconMovie, iconRating;
    TextView tittle,releaseDate,rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);

        back = findViewById(R.id.btn_back);
        addFav = findViewById(R.id.btn_addFavorite);
        backdropPath = findViewById(R.id.photoAlbumDetail);
        posterPath = findViewById(R.id.imgDetailTv);
        tittle =  findViewById(R.id.tittleDetailTv);
        releaseDate =  findViewById(R.id.releaseDateDetailTv);
        rating = findViewById(R.id.textViewRating);
//
//        ModelTv modelTv = getIntent().getParcelableExtra("data");
//        String Backdrop = "https://image.tmdb.org/t/p/w500" + modelTv.getBackdropPath() ;
//        String poster = "https://image.tmdb.org/t/p/w500" + modelTv.getPosterPath() ;
//
//        tittle.setText(modelTv.getOriginalTitle());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailTvActivity.this, TvFragment.class));
                finish();
            }
        });

    }
}
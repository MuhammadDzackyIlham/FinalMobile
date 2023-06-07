package com.example.h071211071_finalmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.h071211071_finalmobile.R;
import com.example.h071211071_finalmobile.fragment.MovieFragment;
import com.example.h071211071_finalmobile.fragment.TvFragment;

public class DetailMovieActivity extends AppCompatActivity {

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        back = findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailMovieActivity.this, MovieFragment.class));
                finish();
            }
        });
    }
}
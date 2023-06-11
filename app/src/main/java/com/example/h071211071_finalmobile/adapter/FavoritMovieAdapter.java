package com.example.h071211071_finalmobile.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h071211071_finalmobile.R;
import com.example.h071211071_finalmobile.activity.DetailMovieActivity;
import com.example.h071211071_finalmobile.fragment.FavoriteFragment;
import com.example.h071211071_finalmobile.model.ModelMovie;
import com.squareup.picasso.Picasso;

import java.util.List;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.h071211071_finalmobile.R;
import com.example.h071211071_finalmobile.activity.DetailMovieActivity;
import com.example.h071211071_finalmobile.model.ModelMovie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoritMovieAdapter extends RecyclerView.Adapter<FavoritMovieAdapter.ViewHolder> {

    private List<ModelMovie> modelMovieList;
    private FavoriteFragment favoriteFragment;

    public FavoritMovieAdapter(List<ModelMovie> modelMovieList, FavoriteFragment favoriteFragment) {
        this.modelMovieList = modelMovieList;
        this.favoriteFragment = favoriteFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ModelMovie modelMovie = modelMovieList.get(position);
        String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500" + modelMovie.getPosterPath();
        Picasso.get().load(IMAGE_BASE_URL).into(holder.ivMovie);
        holder.tvTitle.setText(modelMovie.getOriginalTitle());
        holder.tvReleaseDate.setText(modelMovie.getReleaseDate());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailMovieActivity.class);
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, modelMovie);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return modelMovieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMovie;
        TextView tvTitle, tvReleaseDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ivMovie = itemView.findViewById(R.id.imgPhotoMovie);
            tvTitle = itemView.findViewById(R.id.tittleMovie);
            tvReleaseDate = itemView.findViewById(R.id.realeseDateMovie);
        }
    }
}

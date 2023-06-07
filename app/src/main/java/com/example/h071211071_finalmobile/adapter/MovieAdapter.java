package com.example.h071211071_finalmobile.adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211071_finalmobile.R;
import com.example.h071211071_finalmobile.model.ModelMovie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<ModelMovie> movies;
    public static final String IMAGE_URL_BASE_PATH="https://image.tmdb.org/t/p/original";
    public MovieAdapter (List<ModelMovie> movies) {
        this.movies = movies;
    }


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        FrameLayout moviesLayout;
        TextView movieTitle,releaseDate;
        ImageView movieImage;
        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (FrameLayout) v.findViewById(R.id.frame_layout_movie);
            movieImage = (ImageView) v.findViewById(R.id.imgPhotoMovie);
            movieTitle = (TextView) v.findViewById(R.id.tittleMovie);
            releaseDate = (TextView) v.findViewById(R.id.realeseDateMovie);
        }
    }
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie,parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        String image_url = IMAGE_URL_BASE_PATH + movies.get(position).getPosterPath();
        Log.d(TAG, "onResponse: CEK" + image_url);
        Glide.with(holder.itemView.getContext()).load(image_url).into(holder.movieImage);
        holder.movieTitle.setText(movies.get(position).getOriginalTitle());
        holder.releaseDate.setText(movies.get(position).getReleaseDate());
//
//       Picasso.get()
//                .load(image_url)
//               .placeholder(android.R.drawable.sym_def_app_icon)
//                .error(android.R.drawable.sym_def_app_icon)
//               .into(holder.movieImage);
    }
    @Override
    public int getItemCount() {
        return movies.size();
    }
}

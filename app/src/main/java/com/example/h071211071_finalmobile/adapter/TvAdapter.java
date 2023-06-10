package com.example.h071211071_finalmobile.adapter;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211071_finalmobile.R;
import com.example.h071211071_finalmobile.activity.DetailTvActivity;
import com.example.h071211071_finalmobile.activity.MainActivity;
import com.example.h071211071_finalmobile.model.ModelMovie;
import com.example.h071211071_finalmobile.model.ModelTv;

import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {

    private List<ModelTv> tvs;
    private OnMovieListener onMovieListener;

    public static final String IMAGE_URL_BASE_PATH="https://image.tmdb.org/t/p/original";
    public TvAdapter (List  <ModelTv> tvs, OnMovieListener onMovieListener) {
        this.tvs = tvs;
        this.onMovieListener = onMovieListener;
    }

    public static class TvViewHolder extends RecyclerView.ViewHolder {
        FrameLayout TvLayout;
        TextView TvTitle,releaseDate;
        ImageView TvImage;
        OnMovieListener onMovieListener;

        public TvViewHolder(View itemView, final OnMovieListener onMovieListener) {
            super(itemView);
            TvLayout = (FrameLayout) itemView.findViewById(R.id.frame_layout_tv);
            TvImage = (ImageView) itemView.findViewById(R.id.imgPhotoTv);
            TvTitle = (TextView) itemView.findViewById(R.id.tittleTv);
            releaseDate = (TextView) itemView.findViewById(R.id.realeseDateTv);
            this.onMovieListener = onMovieListener;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMovieListener.onMovieClick(getAdapterPosition());
                }
            });
        }
    }

    @Override
    public TvAdapter.TvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_tv,parent, false);
        return new TvAdapter.TvViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder( TvViewHolder holder, final int position) {
        String image_url = IMAGE_URL_BASE_PATH + tvs.get(position).getPosterPath();
        Log.d(TAG, "onResponse: CEK" + image_url);
        Glide.with(holder.itemView.getContext()).load(image_url).into(holder.TvImage);
        holder.TvTitle.setText(tvs.get(position).getOriginalTitle());
        holder.releaseDate.setText(tvs.get(position).getReleaseDate());
        System.out.println(position);
    }

    @Override
    public int getItemCount() {
        return tvs.size();
    }
    public interface OnTvListener {
        void onTvClick(int position);
    }
    public interface OnMovieListener {
        void onMovieClick(int position);
    }

    public void setmTvs(List<ModelTv> tvs) {
        this.tvs = tvs;
        notifyDataSetChanged();
    }

    public ModelTv getSelectedTv(int position) {
        if (tvs != null) {
            if (tvs.size() > 0) {
                return tvs.get(position);
            }
        }
        return null;
        }


}


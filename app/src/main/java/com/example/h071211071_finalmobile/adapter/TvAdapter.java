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


    public static final String IMAGE_URL_BASE_PATH="https://image.tmdb.org/t/p/original";
    public TvAdapter (List<ModelTv> tvs) {
        this.tvs = tvs;
    }

    public static class TvViewHolder extends RecyclerView.ViewHolder {
        FrameLayout TvLayout;
        TextView TvTitle,releaseDate;
        ImageView TvImage;
        ConstraintLayout constraintLayout;
        public TvViewHolder(View itemView) {
            super(itemView);
            TvLayout = (FrameLayout) itemView.findViewById(R.id.frame_layout_tv);
            TvImage = (ImageView) itemView.findViewById(R.id.imgPhotoTv);
            TvTitle = (TextView) itemView.findViewById(R.id.tittleTv);
            releaseDate = (TextView) itemView.findViewById(R.id.realeseDateTv);
//            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.layoutTv);
        }
    }

    @Override
    public TvAdapter.TvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_tv,parent, false);
        return new TvAdapter.TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder( TvAdapter.TvViewHolder holder,final int position) {
//        ModelTv modelTv = tvs.get(position);
        String image_url = IMAGE_URL_BASE_PATH + tvs.get(position).getPosterPath();
        Log.d(TAG, "onResponse: CEK" + image_url);
        Glide.with(holder.itemView.getContext()).load(image_url).into(holder.TvImage);
        holder.TvTitle.setText(tvs.get(position).getOriginalTitle());
        holder.releaseDate.setText(tvs.get(position).getReleaseDate());

        System.out.println(position);

//        holder.constraintLayout.setOnClickListener(view -> {
//            Intent i = new Intent(view.getContext(), DetailTvActivity.class);
//            i.putExtra("dataTv", modelTv);
//            view.getContext().startActivity(i);
//        });
    }
//    public ModelMovie getSelectedMovie (int position){
//
//    }

    @Override
    public int getItemCount() {
        return tvs.size();
        }
    }


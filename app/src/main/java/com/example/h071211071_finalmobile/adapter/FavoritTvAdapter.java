package com.example.h071211071_finalmobile.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h071211071_finalmobile.R;
import com.example.h071211071_finalmobile.activity.DetailMovieActivity;
import com.example.h071211071_finalmobile.activity.DetailTvActivity;
import com.example.h071211071_finalmobile.fragment.FavoriteFragment;
import com.example.h071211071_finalmobile.model.ModelMovie;
import com.example.h071211071_finalmobile.model.ModelTv;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoritTvAdapter extends RecyclerView.Adapter<FavoritTvAdapter.ViewHolder> {

    private List<ModelTv> modelTvList;

    public FavoritTvAdapter(List<ModelTv> modelTvList, FavoriteFragment favoriteFragment) {
        this.modelTvList = modelTvList;
    }

    @Override
    public FavoritTvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_tv, parent, false);
        return new FavoritTvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoritTvAdapter.ViewHolder holder, int position) {
        ModelTv modelTv = modelTvList.get(position);
        String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500" + modelTvList.get(position).getPosterPath();
        Picasso.get().load(IMAGE_BASE_URL).into(holder.ivMovie);
        holder.tvTitle.setText(modelTv.getOriginalTitle());
        holder.tvReleaseDate.setText(modelTv.getReleaseDate());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailTvActivity.class);
            intent.putExtra(DetailTvActivity.EXTRA_TV, modelTv);
            view.getContext().startActivity(intent);
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(view.getContext(), DetailTvActivity.class);
                intent.putExtra(DetailTvActivity.EXTRA_TV, modelTv);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelTvList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout frameLayoutMovie;
        ImageView ivMovie;
        TextView tvTitle, tvReleaseDate;

        public ViewHolder(View itemView) {
            super(itemView);
            frameLayoutMovie = itemView.findViewById(R.id.frame_layout_movie);
            ivMovie = itemView.findViewById(R.id.imgPhotoTv);
            tvTitle = itemView.findViewById(R.id.tittleTv);
            tvReleaseDate = itemView.findViewById(R.id.realeseDateTv);
        }
    }

}

package com.example.h071211071_finalmobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class ModelTv implements Parcelable {
    @SerializedName("id")
    private String id;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("overview")
    private String overview;

    @SerializedName("name")
    private String originalTitle;

    @SerializedName("first_air_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private double voteAverage;

    public ModelTv(String id, String backdropPath, String posterPath, String overview, String originalTitle, String releaseDate, double voteAverage) {
        this.id = id;
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
        this.overview = overview;
        this.originalTitle = originalTitle;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
    }

    protected ModelTv(Parcel in) {
        id = in.readString();
        backdropPath = in.readString();
        posterPath = in.readString();
        overview = in.readString();
        originalTitle = in.readString();
        releaseDate = in.readString();
        voteAverage = in.readDouble();
    }

    public static final Creator<ModelTv> CREATOR = new Creator<ModelTv>() {
        @Override
        public ModelTv createFromParcel(Parcel in) {
            return new ModelTv(in);
        }

        @Override
        public ModelTv[] newArray(int size) {
            return new ModelTv[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        parcel.writeString(id);
        parcel.writeString(backdropPath);
        parcel.writeString(posterPath);
        parcel.writeString(overview);
        parcel.writeString(originalTitle);
        parcel.writeString(releaseDate);
        parcel.writeDouble(voteAverage);
    }
}

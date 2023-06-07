package com.example.h071211071_finalmobile.networking;

import android.graphics.Movie;

import com.example.h071211071_finalmobile.model.ModelMovie;
import com.example.h071211071_finalmobile.model.ModelTv;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    @SerializedName("results")
    private List<ModelMovie> modelMovie;

    @SerializedName("result")
    private List<ModelTv> modelTv;

    public List<ModelTv> getModelTv() {
        return modelTv;
    }

    public void setModelTv(List<ModelTv> modelTv) {
        this.modelTv = modelTv;
    }


    public List<ModelMovie> getModelMovie() {
        return modelMovie;
    }

    public void setModelMovie(List<ModelMovie> modelMovie) {
        this.modelMovie = modelMovie;
    }



//    public List<ModelTv> getModelTv() {
//        return modelTv;
//    }
//
//    public void setModelTv(List<ModelTv> modelTv) {
//        this.modelTv = modelTv;
//    }
}

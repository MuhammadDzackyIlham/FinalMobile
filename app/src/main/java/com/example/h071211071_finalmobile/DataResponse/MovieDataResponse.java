package com.example.h071211071_finalmobile.DataResponse;

import com.example.h071211071_finalmobile.model.ModelMovie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDataResponse {
    @SerializedName("results")
    private List<ModelMovie> modelMovie;


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

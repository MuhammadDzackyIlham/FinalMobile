package com.example.h071211071_finalmobile.DataResponse;

import com.example.h071211071_finalmobile.model.ModelTv;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvDataResponse {
    @SerializedName("results")
    public List<ModelTv> modelTv;

    public List<ModelTv> getModelTv() {
        return modelTv;
    }

    public void setModelTv(List<ModelTv> modelTv) {
        this.modelTv = modelTv;
    }

}

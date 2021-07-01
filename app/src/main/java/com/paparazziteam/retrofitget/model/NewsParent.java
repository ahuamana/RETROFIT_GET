package com.paparazziteam.retrofitget.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsParent {

    @SerializedName("data")
    List<News> sources;

    public NewsParent(List<News> sources) {
        this.sources = sources;
    }

    public List<News> getSources() {
        return sources;
    }

    public void setSources(List<News> sources) {
        this.sources = sources;
    }
}

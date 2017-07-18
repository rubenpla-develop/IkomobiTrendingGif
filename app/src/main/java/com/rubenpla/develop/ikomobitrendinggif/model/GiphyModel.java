package com.rubenpla.develop.ikomobitrendinggif.model;

import java.util.ArrayList;
import java.util.List;

public class GiphyModel {

    private String url;
    private ArrayList<String> gifList;

    public GiphyModel() {
        gifList = new ArrayList<>();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getGifList() {
        return gifList;
    }

    public void setGifList(ArrayList<String> gifList) {
        this.gifList = gifList;
    }
}

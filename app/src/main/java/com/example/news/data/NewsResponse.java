package com.example.news.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {

    @SerializedName("articles")
    @Expose
    private List<NewsArticle> listOfNews;

    //Constructor

    public NewsResponse() {
    }

    //Getter
    public List<NewsArticle> getListOfNews() {
        return listOfNews;
    }


}

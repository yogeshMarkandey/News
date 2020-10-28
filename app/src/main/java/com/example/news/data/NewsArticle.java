package com.example.news.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsArticle {
    @SerializedName("source")
    @Expose
    private NewsSource source;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("urlToImage")
    @Expose
    private String urlToImage;
    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;
    @SerializedName("content")
    @Expose
    private String content;

    public NewsArticle() {
        // Empty Constructor
    }


    //Getters And Setter

    public NewsSource getSource() {
        return source;
    }


    public String getAuthor() {
        return author;
    }


    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrlToImage() {
        return urlToImage;
    }
    public String getPublishedAt() {
        return publishedAt;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}

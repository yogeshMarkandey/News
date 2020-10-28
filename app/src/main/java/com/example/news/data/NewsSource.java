package com.example.news.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsSource {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;


    // constructor
    public NewsSource() {
    }

    //Getter and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.example.news.network;



import com.example.news.data.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
// bb1eafcd12f54b9c8c5d2962ed2bd700    Key -1
// b1eabf9d2a064982b74957d8f427dd00    Key -2
// e5b1569d8b7f4011985549860e8210d6    Key -3


    // for getting bbc news
    @GET("top-headlines?apiKey=bb1eafcd12f54b9c8c5d2962ed2bd700")
    Call<NewsResponse> getNews(
            @Query("sources") String sourceId
    );

    // for getting news from category
    @GET("top-headlines?country=in&apiKey=bb1eafcd12f54b9c8c5d2962ed2bd700")
    Call<NewsResponse> getNewsFrom(
            @Query("category") String category
    );

    //for getting news from a particular country
    @GET("top-headlines?q=news&apiKey=e5b1569d8b7f4011985549860e8210d6")
    Call<NewsResponse> getNewsFromCountry(
            @Query("country") String country
    );
    //for getting top news
    @GET("top-headlines?q=india&sortBy=popularity&apiKey=e5b1569d8b7f4011985549860e8210d6")
    Call<NewsResponse> getTopNews();
}

package com.example.news.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.news.data.NewsArticle;
import com.example.news.data.NewsResponse;
import com.example.news.network.NewsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private static final String TAG = "Repository";
    private NewsApi newsApi;
    public MutableLiveData<List<NewsArticle>>  topNewsLiveData = new MutableLiveData<>() ;
    public MutableLiveData<List<NewsArticle>> sportsNewsLD = new MutableLiveData<>();
    private MutableLiveData<List<NewsArticle>>  technologyNews = new MutableLiveData<>();
    private MutableLiveData<List<NewsArticle>>  scienceNews = new MutableLiveData<>();
    private MutableLiveData<List<NewsArticle>>  healthNews = new MutableLiveData<>();
    private MutableLiveData<List<NewsArticle>>  generalNews = new MutableLiveData<>();
    private MutableLiveData<List<NewsArticle>>  businessNews = new MutableLiveData<>();
    private MutableLiveData<List<NewsArticle>>  entertainmentNews = new MutableLiveData<>();
    private MutableLiveData<List<NewsArticle>> indiaNews = new MutableLiveData<>();
    private MutableLiveData<List<NewsArticle>> usNews = new MutableLiveData<>();
    private MutableLiveData<List<NewsArticle>> bbcNews = new MutableLiveData<>();
    private MutableLiveData<List<NewsArticle>> topNews = new MutableLiveData<>();

    private MutableLiveData<Integer> responseLD = new MutableLiveData<>();


    public Repository( NewsApi newsApi){
        Log.d(TAG, "Repository: called");
        this.newsApi = newsApi;


    }

    // for getting BBC news (From source)
    public void getNews(){
        Log.d(TAG, "getNews: called");
        Call<NewsResponse> call = newsApi.getNews("bbc-news");
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                Log.d(TAG, "onResponse: response : " +response.code());
                responseLD.postValue(response.code());
                Log.d(TAG, "onResponse: response code : " + response.code());
                if(response.isSuccessful()){
                    List<NewsArticle> list = response.body().getListOfNews();
                    if(!list.isEmpty()){
                       bbcNews.postValue(list);
                    }else {
                        Log.d(TAG, "onResponse: list is null");
                    }
                }
            }
            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: resposnse failed : " + t.getMessage());
            }
        });
    }


    // for getting top highlights(From Query)
    public void getTopNewsFromApi(){
        Call<NewsResponse> call = newsApi.getTopNews();
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if(response.isSuccessful()){
                   List<NewsArticle> list = response.body().getListOfNews();
                   topNews.postValue(list);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed : " + t.getMessage());
            }
        });
    }

    public void getNewsFromCountry(String country){
        Log.d(TAG, "getNewsFromCountry: Called XX");
        Call<NewsResponse> call = newsApi.getNewsFromCountry(country);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                Log.d(TAG, "onResponse: resposne XX : " + response.code());

                responseLD.postValue(response.code());

                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: success XX");
                    List<NewsArticle> list = response.body().getListOfNews();
                    Log.d(TAG, "onResponse: ssss XX : " + list.size());
                    postOnLiveData(country, list);
                }
            }
            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: resposnse failed XX : " + t.getMessage());
            }
        });
    }

    public void getNewsOfCategory(String category){
        Call<NewsResponse> call =newsApi.getNewsFrom(category);

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                responseLD.postValue(response.code());
                if(response.isSuccessful()){
                    List<NewsArticle> list = response.body().getListOfNews();
                    postOnLiveData(category, list);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: resposnse failed : " + t.getMessage());

            }
        });
    }

    // for posting in correct livedata
    private void postOnLiveData(String category, List<NewsArticle> list){
        switch (category){
            case "sports":
                sportsNewsLD.postValue(list);
                break;
            case "technology":
                technologyNews.postValue(list);
                break;
            case "science":
                scienceNews.postValue(list);
                break;
            case "health":
                healthNews.postValue(list);
                break;
            case "general":
                generalNews.postValue(list);
                break;
            case "business":
                businessNews.postValue(list);
                break;
            case "entertainment":
                entertainmentNews.postValue(list);
            case "in":
                Log.d(TAG, "postOnLiveData: post in XX");
                indiaNews.postValue(list);
                break;
            case "us" :
                Log.d(TAG, "postOnLiveData: post us XX");
                usNews.postValue(list);
                break;
        }
    }


    public MutableLiveData<List<NewsArticle>> getTopNews() {
        return topNews;
    }

    public MutableLiveData<Integer> getResponseLD() {
        return responseLD;
    }

    public MutableLiveData<List<NewsArticle>> getBbcNews() {
        return bbcNews;
    }

    public MutableLiveData<List<NewsArticle>> getIndiaNews() {
        return indiaNews;
    }

    public MutableLiveData<List<NewsArticle>> getUsNews() {
        return usNews;
    }

    public MutableLiveData<List<NewsArticle>> getTechnologyNews() {
        return technologyNews;
    }

    public MutableLiveData<List<NewsArticle>> getScienceNews() {
        return scienceNews;
    }

    public MutableLiveData<List<NewsArticle>> getHealthNews() {
        return healthNews;
    }

    public MutableLiveData<List<NewsArticle>> getGeneralNews() {
        return generalNews;
    }

    public MutableLiveData<List<NewsArticle>> getBusinessNews() {
        return businessNews;
    }

    public MutableLiveData<List<NewsArticle>> getEntertainmentNews() {
        return entertainmentNews;
    }

    public MutableLiveData<List<NewsArticle>> getTopNewsLiveData() {
        return topNewsLiveData;
    }

    public MutableLiveData<List<NewsArticle>> getSportsNewsLD() {
        return sportsNewsLD;
    }
}

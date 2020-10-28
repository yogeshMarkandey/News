package com.example.news.viewmodel;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.news.data.NewsArticle;
import com.example.news.network.NewsApi;
import com.example.news.network.RetrofitClient;
import com.example.news.repository.Repository;

import java.util.List;

import retrofit2.Retrofit;

public class MainViewModel extends ViewModel{
    private static final String TAG = "MainViewModel";
    private NewsApi newsApi;
    public Repository repository;
    private RetrofitClient client;

    private MutableLiveData<List<NewsArticle>> topNews = new MutableLiveData<>();

    private MutableLiveData<Boolean> networkState = new MutableLiveData<>();

    public MainViewModel() {
        client = RetrofitClient.getInstance();
        newsApi = client.retrofit.create(NewsApi.class);
        repository = new Repository(newsApi);
        topNews = repository.getTopNewsLiveData();
    }

    public void getNews(){
        Log.d(TAG, "getNews: called");
        repository.getNews();
    }

    public MutableLiveData<List<NewsArticle>> getTopNews() {
        return topNews;
    }

    public void getNewsOfCategory(String category){
        repository.getNewsOfCategory(category);
    }

    public void getNewsCountry(String country){
        repository.getNewsFromCountry(country);
    }

    public boolean checkNetworkState(Context context){
        final ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            // notify user you are online
           // networkState.postValue(true);

            return true;
        } else {
            // notify user you are not online
            //networkState.postValue(false);

            return false;
        }
    }

    public void showDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = null;
        builder.setTitle("You are offline!")
                .setMessage("No internet connection.\nPlease check Wifi configuration or Try again later.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });


        dialog = builder.create();
        dialog.show();
    }
}

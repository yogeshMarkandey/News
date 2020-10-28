package com.example.news;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.news.viewmodel.MainViewModel;

public class App extends Application {

    MainViewModel viewModel;
    @Override
    public void onCreate() {
        super.onCreate();
       // viewModel = new ViewModelProvider().get(MainViewModel.class);
    }
}

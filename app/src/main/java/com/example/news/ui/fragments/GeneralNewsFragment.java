package com.example.news.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.ui.activites.DetailActivity;
import com.example.news.R;
import com.example.news.adapters.NewsRVAdapter;
import com.example.news.adapters.NewsRVAdapter2;
import com.example.news.data.NewsArticle;
import com.example.news.viewmodel.MainViewModel;

import java.util.List;


public class GeneralNewsFragment extends Fragment implements NewsRVAdapter.OnTapListener {

    private static final String TAG = "GeneralNewsFragment";

    //vars
    private NewsRVAdapter2 adapter;
    private MainViewModel mainViewModel;

    // widgets
    private RecyclerView recyclerView;
    private CardView cardViewNoInternet;
    private Button retry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: called");
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        adapter = new NewsRVAdapter2(getContext(), this);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_news, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_top_news);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cardViewNoInternet = view.findViewById(R.id.card_no_internet);
        retry = view.findViewById(R.id.button_retry);
        retry.setOnClickListener(view1 -> initialize());
        initialize();
        return view;
    }

    //5th
    public void initialize(){
        if(mainViewModel.checkNetworkState(getContext())){
            recyclerView.setVisibility(View.VISIBLE);
            cardViewNoInternet.setVisibility(View.GONE);


                mainViewModel.getNewsOfCategory("general");

        }else {
            mainViewModel.showDialog(getContext());
            cardViewNoInternet.setVisibility(View.VISIBLE);
            recyclerView .setVisibility(View.GONE);
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel.repository.getGeneralNews().observe(getViewLifecycleOwner(),  new Observer<List<NewsArticle>>() {
            @Override
            public void onChanged(List<NewsArticle> newsArticles) {

                adapter.submitList(newsArticles);

            }
        });
    }

    public GeneralNewsFragment() {
        // Required empty public constructor
    }


    public static GeneralNewsFragment newInstance(String param1, String param2) {
        GeneralNewsFragment fragment = new GeneralNewsFragment();
        return fragment;
    }


    @Override
    public void OpenUrl(String url) {

        Toast.makeText(getContext(), "Opening", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("news_url", url);
        intent.putExtra("tab_name", "General");
        startActivity(intent);
    }
}
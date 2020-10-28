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
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.news.ui.activites.DetailActivity;
import com.example.news.R;
import com.example.news.adapters.NewsRVAdapter;
import com.example.news.adapters.NewsRVAdapter2;
import com.example.news.data.NewsArticle;
import com.example.news.viewmodel.MainViewModel;

import java.util.List;


public class HomeNewsFragment extends Fragment implements NewsRVAdapter.OnTapListener {
    private static final String TAG = "IndiaNewsFragment";

    private MainViewModel mainViewModel;
    private RecyclerView recyclerView, recyclerView2;
    private CardView cardViewNoInternet;
    private Button retry;
    private NewsRVAdapter adapter2;
    private NewsRVAdapter2 adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        adapter = new NewsRVAdapter2(getContext(), this);
        adapter2 = new NewsRVAdapter(getContext(), this);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_news, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_top_news);
        recyclerView2 = view.findViewById(R.id.recycler_view_new);
        cardViewNoInternet = view.findViewById(R.id.card_no_internet);
        retry = view.findViewById(R.id.button_retry);
        retry.setOnClickListener(view1 -> initialize());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView2.setAdapter(adapter2);
        SnapHelper helper = new PagerSnapHelper();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        helper.attachToRecyclerView(recyclerView2);
        recyclerView2.setLayoutManager(manager);
        recyclerView2.setHasFixedSize(true);

        initialize();
        return view;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel.repository.getIndiaNews().observe(getViewLifecycleOwner(),  new Observer<List<NewsArticle>>() {
            @Override
            public void onChanged(List<NewsArticle> newsArticles) {
                Log.d(TAG, "onChanged: observer in XX ");
                adapter.submitList(newsArticles);

            }
        });

        mainViewModel.repository.getTopNews().observe(getViewLifecycleOwner(), new Observer<List<NewsArticle>>() {
            @Override
            public void onChanged(List<NewsArticle> newsArticles) {
                adapter2.submitList(newsArticles);
            }
        });
    }

    public HomeNewsFragment() {
        // Required empty public constructor
    }


    public static HomeNewsFragment newInstance(String param1, String param2) {
        HomeNewsFragment fragment = new HomeNewsFragment();
        return fragment;
    }


    @Override
    public void OpenUrl(String url) {

        Toast.makeText(getContext(), "Opening", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("news_url", url);
        intent.putExtra("tab_name", "Home");
        startActivity(intent);
    }

    public void initialize(){
        if(mainViewModel.checkNetworkState(getContext())){
            recyclerView.setVisibility(View.VISIBLE);
            cardViewNoInternet.setVisibility(View.GONE);


                mainViewModel.getNewsCountry("in");
                mainViewModel.repository.getTopNewsFromApi();

        }else {
           // Toast.makeText(getContext() , "No Internet Connection \nTry Again Later.", Toast.LENGTH_SHORT).show();
            mainViewModel.showDialog(getContext());
            cardViewNoInternet.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }
}
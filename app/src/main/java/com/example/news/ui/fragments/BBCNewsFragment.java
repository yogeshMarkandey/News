package com.example.news.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
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


public class BBCNewsFragment extends Fragment implements NewsRVAdapter.OnTapListener {

    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private NewsRVAdapter2 adapter;
    private CardView cardViewNoInternet;
    private Button retry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        adapter = new NewsRVAdapter2(getContext(), this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bbc_news, container, false);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel.repository.getBbcNews().observe(getViewLifecycleOwner(), new Observer<List<NewsArticle>>() {
            @Override
            public void onChanged(List<NewsArticle> newsArticles) {
                adapter.submitList(newsArticles);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public BBCNewsFragment() {
        // Required empty public constructor
    }


    public static BBCNewsFragment newInstance(String param1, String param2) {
        BBCNewsFragment fragment = new BBCNewsFragment();
        return fragment;
    }

    //5th
    public void initialize() {
        if (mainViewModel.checkNetworkState(getContext())) {
            recyclerView.setVisibility(View.VISIBLE);
            cardViewNoInternet.setVisibility(View.GONE);


            mainViewModel.getNews();

        } else {
            mainViewModel.showDialog(getContext());
            cardViewNoInternet.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void OpenUrl(String url) {

        Toast.makeText(getContext(), "Opening", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("tab_name", "BBC");
        intent.putExtra("news_url", url);
        startActivity(intent);
    }
}
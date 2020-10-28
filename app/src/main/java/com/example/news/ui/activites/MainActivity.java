package com.example.news.ui.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.news.R;
import com.example.news.adapters.ViewPagerAdapterMain;
import com.example.news.ui.fragments.BBCNewsFragment;
import com.example.news.ui.fragments.BusinessNewsFragment;
import com.example.news.ui.fragments.EntertainmentNewsFragment;
import com.example.news.ui.fragments.GeneralNewsFragment;
import com.example.news.ui.fragments.HealthNewsFragment;
import com.example.news.ui.fragments.HomeNewsFragment;
import com.example.news.ui.fragments.ScienceNewsFragment;
import com.example.news.ui.fragments.SportsNewsFragment;
import com.example.news.ui.fragments.TechnologyNewsFragment;
import com.example.news.ui.fragments.WorldNewsFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    // widgets
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewPager);

        // adding all fragments to the list
        fragmentList.add(new HomeNewsFragment());
        fragmentList.add(new WorldNewsFragment());
        fragmentList.add(new GeneralNewsFragment());
        fragmentList.add(new BBCNewsFragment());
        fragmentList.add(new SportsNewsFragment());
        fragmentList.add(new ScienceNewsFragment());
        fragmentList.add(new HealthNewsFragment());
        fragmentList.add(new BusinessNewsFragment());
        fragmentList.add(new EntertainmentNewsFragment());
        fragmentList.add(new TechnologyNewsFragment());

        // initialising ViewPager Adapter
       ViewPagerAdapterMain pagerAdapterMain =
               new ViewPagerAdapterMain(getSupportFragmentManager(), getApplicationContext(), fragmentList);

        viewPager.setAdapter(pagerAdapterMain);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabLayout.getTabAt(0).setText("Home");
        tabLayout.getTabAt(1).setText("World");
        tabLayout.getTabAt(2).setText("General");
        tabLayout.getTabAt(3).setText("BBC");
        tabLayout.getTabAt(4).setText("Sports");
        tabLayout.getTabAt(5).setText("Science");
        tabLayout.getTabAt(6).setText("Health");
        tabLayout.getTabAt(7).setText("Business");
        tabLayout.getTabAt(8).setText("Entertainment");
        tabLayout.getTabAt(9).setText("Technology");
    }
}
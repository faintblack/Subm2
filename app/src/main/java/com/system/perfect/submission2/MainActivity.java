package com.system.perfect.submission2;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.system.perfect.submission2.adapter.ViewPagerAdapter;
import com.system.perfect.submission2.fragment.PopularFragment;
import com.system.perfect.submission2.fragment.NowPlayingFragment;
import com.system.perfect.submission2.fragment.UpcomingFragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout tl;
    private Toolbar tb;
    private ViewPager vPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        vPager = findViewById(R.id.container);
        setViewPager(vPager);

        tl = findViewById(R.id.tabs);
        tl.setupWithViewPager(vPager);

    }

    private void setViewPager(ViewPager v){
        ViewPagerAdapter adapt = new ViewPagerAdapter(getSupportFragmentManager());
        adapt.addFrag(new NowPlayingFragment(), "Now Playing");
        adapt.addFrag(new UpcomingFragment(), "Upcoming");
        adapt.addFrag(new PopularFragment(), "All Movies");
        v.setAdapter(adapt);
    }

}

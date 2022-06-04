package com.example.onlyfacts.news_category;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlyfacts.NewsDataAdapter;
import com.example.onlyfacts.NewsDataSet;
import com.example.onlyfacts.R;
import com.example.onlyfacts.Roomtest.RoomClass;
import com.example.onlyfacts.dbconnthread.DBConnect;
import com.example.onlyfacts.dbconnthread.DBJsonToString;
import com.example.onlyfacts.dbconnthread.TestActivity;
import com.example.onlyfacts.headnews.HeadnewsAdapter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class FragHome extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<NewsDataSet> list = new ArrayList<>();

    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 4;
    private CircleIndicator3 mIndicator;

    LinearLayoutManager linearLayoutManager;
    List<NewsDataSet> newsDataSetList;
    Handler settingHandler;
    RoomClass database;
    public static FragHome newInstance() {
        FragHome fragment = new FragHome();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.frag_home, container, false);

        viewPager2 = view.findViewById(R.id.viewpager_mainNews);//headline 뷰페이저2
        mIndicator = view.findViewById(R.id.indicator);         //headline 인디케이터
        recyclerView = view.findViewById(R.id.newslist_main);
        headNews(); //headline 나머지 메서드

        settingHandler = new MainHandler();
        database = RoomClass.getInstance(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity());

        Thread dbConnect = new DBConnect(settingHandler);
        dbConnect.start();

        return view;
    }

    //****슬라이드 카드뉴스***********
    private void headNews() {
        //Adapter
        pagerAdapter = new HeadnewsAdapter(this, num_page);
        viewPager2.setAdapter(pagerAdapter);

        mIndicator.setViewPager(viewPager2);
        mIndicator.createIndicators(num_page, 0);
        //ViewPager Setting
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setCurrentItem(1000);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    viewPager2.setCurrentItem(position);
                }
            }
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator.animatePageSelected(position%num_page);
            }
        });
        final float pageMargin= getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        final float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);
        viewPager2.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float myOffset = position * -(2 * pageOffset + pageMargin);
                if (viewPager2.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                    if (ViewCompat.getLayoutDirection(viewPager2) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.setTranslationX(-myOffset);
                    } else {
                        page.setTranslationX(myOffset);
                    }
                } else {
                    page.setTranslationY(myOffset);
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    class MainHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            String txt = bundle.getString("data");

            if (txt.equals("")){
                Log.d("connect","connection fail");
            }else{
                try {
                    DBJsonToString jts = new DBJsonToString(txt);
                    newsDataSetList = jts.getDatalist();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //여기다가 newsDataSetList 어댑터에 끼워서 리사이클러뷰 만들면 됨.
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                NewsDataAdapter newsDataAdapter = new NewsDataAdapter(newsDataSetList);
                recyclerView.setAdapter(newsDataAdapter);

            }
        }
    }

}
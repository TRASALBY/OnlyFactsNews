package com.example.onlyfacts;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.onlyfacts.dbconnthread.TestActivity;
import com.example.onlyfacts.news_category.FragCulture;
import com.example.onlyfacts.news_category.FragEconomic;
import com.example.onlyfacts.news_category.FragHome;
import com.example.onlyfacts.news_category.FragInfoTech;
import com.example.onlyfacts.news_category.FragInternational;
import com.example.onlyfacts.news_category.FragPolitics;
import com.example.onlyfacts.news_category.FragSociety;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View drawerView;

    private ViewPager2 viewPager2;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;

    private String[] titles = new String[]{"홈","사회","정치","경제", "문화", "IT"};
    private DrawerLayout.DrawerListener listener;
    LinearLayout btn_bookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //좌측 메뉴 슬라이드
        setTabs();
        setDrawer();

        btn_bookmark = findViewById(R.id.box_bookmark);
        btn_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookmarkActivity.class);
                startActivity(intent);

            }
        });

    }
//메뉴탭 - 뷰페이저2 연결 구현 ===============
    private void setTabs() {

        Fragment frag1 = new FragHome();
        Fragment frag2 = new FragSociety();
        Fragment frag3 = new FragPolitics();
        Fragment frag4 = new FragEconomic();
        Fragment frag5 = new FragCulture();
        Fragment frag6 = new FragInfoTech();

        viewPager2 = findViewById(R.id.viewpage);
        tabLayout = findViewById(R.id.tab_layout);

        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPagerAdapter.addFrag(frag1);
        viewPagerAdapter.addFrag(frag2);
        viewPagerAdapter.addFrag(frag3);
        viewPagerAdapter.addFrag(frag4);
        viewPagerAdapter.addFrag(frag5);
        viewPagerAdapter.addFrag(frag6);
        viewPager2.setAdapter(viewPagerAdapter);

        //뷰페이저 스와이프 막기
        viewPager2.setUserInputEnabled(false);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(titles[position])).attach();
    }
//=======================================

//슬라이드 메뉴 구현==========================
    private void setDrawer() {
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        ImageButton btn_menu = (ImageButton) findViewById(R.id.btn_menu);
        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_backarrow);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(drawerView);
            }
        });
        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        //슬라이드 메뉴 리스너
        DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {        }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {        }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {        }

            @Override
            public void onDrawerStateChanged(int newState) {        }
        };
    }
//=======================================

//DB 데이터 받기
    public void dataLoad(View view) {

    }
//

}
package com.example.onlyfacts.news_category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlyfacts.Data;
import com.example.onlyfacts.DataAdapter;
import com.example.onlyfacts.R;
import com.example.onlyfacts.ViewPagerAdapter;
import com.example.onlyfacts.headnews.HeadnewsAdapter;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class FragHome extends Fragment {

    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private ArrayList<Data> list = new ArrayList<>();

    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 4;
    private CircleIndicator3 mIndicator;

    public static FragHome newInstance() {
        FragHome fragment = new FragHome();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.frag_home, container, false);

//****슬라이드 카드뉴스***********
        viewPager2 = view.findViewById(R.id.viewpager_mainNews);

        //Adapter
        pagerAdapter = new HeadnewsAdapter(this, num_page);
        viewPager2.setAdapter(pagerAdapter);

        //indicator
        mIndicator = view.findViewById(R.id.indicator);
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
//*********************


        //리사이클러뷰
        recyclerView = (RecyclerView) view.findViewById(R.id.newslist_main);
        recyclerView.setHasFixedSize(true);
        dataAdapter = new DataAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dataAdapter);

        prepareData();

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        list.clear();
    }

    //데이터 불러오기- 지금 임시
    private void prepareData() {
        list.add(new Data("80",R.drawable.img_dummycookie, "까까뉴스", "1시간 전", "전국 쿠키 체육대회 앵두맛 쿠키 참전 소식! 우승 노리는가?"));
        list.add(new Data("75",R.drawable.img_dummy, "브레이크오븐", "2시간 전", "쿠오븐 신규뽑기 추가, 유저들 무거운 과금 요소에 어려움 호소,"));
        list.add(new Data("88",R.drawable.img_dummy, "쿠비에스", "3시간 전", "쿠키런 새로운 쿠폰코드 공개 Time to play sports : 무큐 300개"));
        list.add(new Data("30",R.drawable.img_dummy, "까까뉴스", "4시간 전", "초코볼맛 쿠키 4년째 체육대회 우승, 이번 대회에서 5연승 도전하는가?"));
        list.add(new Data("45",R.drawable.img_dummy, "오븐쿡", "5시간 전", "라임맛 쿠키 상큼한 신규 슈퍼에픽 스킨 출시! 4월 27일 공개"));
        list.add(new Data("70",R.drawable.img_dummy, "까까뉴스", "6시간 전", "레전더리 옵션 젤리스킨 세트 출시! 일부 유저 부정적 시선 밝혀..."));
        list.add(new Data("62",R.drawable.img_dummy, "5분뉴스", "7시간 전", "라이브 운동회! 시간이 안맞아 참여하지 못하는 쿠키들 속상함 드러내..."));
        list.add(new Data("80",R.drawable.img_dummycookie, "까까뉴스", "1시간 전", "더미 제목 뉴스"));
        list.add(new Data("75",R.drawable.img_dummy, "브레이크오븐", "2시간 전", "쿠오븐 신규뽑기 추가, 유저들 무거운 과금 요소에 어려움 호소,"));
        list.add(new Data("88",R.drawable.img_dummy, "쿠비에스", "3시간 전", "쿠키런 새로운 쿠폰코드 공개 Time to play sports : 무큐 300개"));
        list.add(new Data("30",R.drawable.img_dummy, "까까뉴스", "4시간 전", "초코볼맛 쿠키 4년째 체육대회 우승, 이번 대회에서 5연승 도전하는가"));


    }



}
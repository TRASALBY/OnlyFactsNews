package com.example.onlyfacts.news_category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlyfacts.Data;
import com.example.onlyfacts.DataAdapter;
import com.example.onlyfacts.R;

import java.util.ArrayList;

public class FragInternational extends Fragment {

    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private ArrayList<Data> list = new ArrayList<>();

    public static FragInternational newInstance() {
        FragInternational fragment = new FragInternational();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_international, container, false);

        //리사이클러뷰
        recyclerView = (RecyclerView) view.findViewById(R.id.newslist_international);
        recyclerView.setHasFixedSize(true);
        dataAdapter = new DataAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dataAdapter);

        list.add(new Data("80",R.drawable.img_dummycookie, "까까뉴스", "1시간 전", "[국제]전국 쿠키 체육대회 앵두맛 쿠키 참전 소식! 우승 노리는가?"));

        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
        list.clear();
    }
}
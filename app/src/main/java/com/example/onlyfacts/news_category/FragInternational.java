package com.example.onlyfacts.news_category;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlyfacts.NewsDataSet;
import com.example.onlyfacts.R;
import com.example.onlyfacts.Roomtest.RoomClass;

import java.util.ArrayList;
import java.util.List;

public class FragInternational extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<NewsDataSet> newsDataSetList;
    Handler settingHandler;
    RoomClass database;
    public static FragInternational newInstance() {
        FragInternational fragment = new FragInternational();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_international, container, false);

        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
    }
}
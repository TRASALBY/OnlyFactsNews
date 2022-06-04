package com.example.onlyfacts.news_category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlyfacts.NewsDataSet;
import com.example.onlyfacts.R;

import java.util.ArrayList;

public class FragInternational extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<NewsDataSet> list = new ArrayList<>();

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
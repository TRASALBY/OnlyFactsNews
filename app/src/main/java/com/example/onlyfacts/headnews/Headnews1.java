package com.example.onlyfacts.headnews;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlyfacts.R;

public class Headnews1 extends Fragment {


    public Headnews1() { }
    public static Headnews1 newInstance() {
        Headnews1 fragment = new Headnews1();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.headnews1,container,false);
        return rootView;
    }
}
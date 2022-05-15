package com.example.onlyfacts.headnews;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlyfacts.R;

public class Headnews2 extends Fragment {


    public Headnews2() {    }

    public static Headnews2 newInstance() {
        Headnews2 fragment = new Headnews2();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.headnews2,container,false);
        return rootView;
    }
}
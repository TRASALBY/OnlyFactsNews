package com.example.onlyfacts.headnews;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlyfacts.R;

public class Headnews4 extends Fragment {

    public Headnews4() {   }

    public static Headnews4 newInstance() {
        Headnews4 fragment = new Headnews4();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.headnews4,container,false);
        return rootView;
    }
}
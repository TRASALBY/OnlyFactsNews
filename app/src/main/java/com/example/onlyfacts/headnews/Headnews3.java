package com.example.onlyfacts.headnews;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlyfacts.R;

public class Headnews3 extends Fragment {

    public Headnews3() {    }

    public static Headnews3 newInstance() {
        Headnews3 fragment = new Headnews3();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.headnews3,container,false);
        return rootView;
    }
}
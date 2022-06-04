package com.example.onlyfacts.headnews;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.onlyfacts.NewsActivity;
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

        LinearLayout headNews3 = rootView.findViewById(R.id.box_mainNews3);
        headNews3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}